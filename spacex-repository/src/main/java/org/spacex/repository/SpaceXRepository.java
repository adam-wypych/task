package org.spacex.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.spacex.model.DefaultMission;
import org.spacex.model.DefaultRocket;
import org.spacex.model.Mission;
import org.spacex.model.MissionStatus;
import org.spacex.model.Rocket;
import org.spacex.model.RocketStatus;
import org.spacex.model.summary.MissionSummary;
import org.spacex.model.summary.RocketSummary;
import org.spacex.model.summary.SpaceProgramSummary;
import org.spacex.repository.mission.exceptions.DuplicateMissionException;
import org.spacex.repository.mission.exceptions.RocketAssignmentMissionAlreadyFinishedException;
import org.spacex.repository.mission.exceptions.UnknownMissionException;
import org.spacex.repository.rocket.exceptions.DuplicateRocketException;
import org.spacex.repository.rocket.exceptions.IllegalStatusOfRocket;
import org.spacex.repository.rocket.exceptions.RocketAlreadyAssignedToMissionException;
import org.spacex.repository.rocket.exceptions.UnknownRocketException;

public class SpaceXRepository implements SpaceXMissionRepository, SpaceXRocketRepository, SpaceProgramRepositoryOperations {
	private final List<DefaultMission> missions = new ArrayList<>();
	private final List<DefaultRocket> rockets = new ArrayList<>();

	public Mission createNewMission(final String missionName) {
		if (missions.stream().anyMatch(m -> m.getName().equals(missionName))) {
			throw new DuplicateMissionException(missionName);
		}
		DefaultMission newMission = new DefaultMission(missionName);
		missions.add(newMission);

		return newMission;
	}

	public Rocket createNewRocket(final String rocketName) {
		if (rockets.stream().anyMatch(r -> r.getName().equals(rocketName))) {
			throw new DuplicateRocketException(rocketName);
		}

		DefaultRocket newRocket = new DefaultRocket(rocketName);
		rockets.add(newRocket);

		return newRocket;
	}

	public void assignRocketForMission(final Rocket rocket, final Mission mission) {
		if (rocket.getCurrentMission().isPresent()) {
			throw new RocketAlreadyAssignedToMissionException(rocket);
		}
		if (mission.getStatus() == MissionStatus.ENDED) {
			throw new RocketAssignmentMissionAlreadyFinishedException(rocket, mission);
		}

		DefaultRocket defaultRocket = findConcreteInRepository(rocket)
				.orElseThrow(() -> new UnknownRocketException(rocket));
		DefaultMission defaultMission = findConcreteInRepository(mission)
				.orElseThrow(() -> new UnknownMissionException(mission));
		defaultRocket.setStatus(RocketStatus.IN_SPACE);
		defaultRocket.setMission(defaultMission);
		defaultMission.setStatus(MissionStatus.IN_PROGRESS);
		defaultMission.addRocket(defaultRocket);
	}

	private Optional<DefaultRocket> findConcreteInRepository(final Rocket rocket) {
		return rockets.stream().filter(e -> e.getName().equals(rocket.getName())).findFirst();
	}

	private Optional<DefaultMission> findConcreteInRepository(final Mission mission) {
		return missions.stream().filter(e -> e.getName().equals(mission.getName())).findFirst();
	}

	public void changeStatus(final Rocket rocket, final RocketStatus status) {
		if (status == RocketStatus.IN_REPAIR || status == RocketStatus.IN_SPACE) {
			if (rocket.getCurrentMission().isEmpty()) {
				throw new IllegalStatusOfRocket("Rocket can't be in repair or space status");
			}
		}
		
		findConcreteInRepository(rocket).ifPresent(dr -> dr.setStatus(status));
	}
	
	public void finishMission(final Mission mission) {
		for (Rocket rocket: mission.getRockets()) {
			DefaultRocket defaultRocket = findConcreteInRepository(rocket).orElseThrow(() -> new UnknownRocketException(rocket));
			defaultRocket.setMission(null);
			changeStatus(rocket, RocketStatus.ON_GROUND);
		}
		
		DefaultMission defaultMission = findConcreteInRepository(mission).orElseThrow(() -> new UnknownMissionException(mission));
		defaultMission.removeRockets();
		defaultMission.setStatus(MissionStatus.ENDED);
	}

	@Override
	public SpaceProgramSummary generateSummary() {
		SpaceProgramSummary spaceProgramSummary = new SpaceProgramSummary();
		for (Mission mission: missions) {
			MissionSummary missionSummary = new MissionSummary(mission.getName(), mission.getStatus().getDisplayName());
			spaceProgramSummary.addMissionSummary(missionSummary);
			
			for (Rocket rocket: mission.getRockets()) {
				RocketSummary rocketSummary = new RocketSummary(rocket.getName(), rocket.getStatus().getDisplayName());
				missionSummary.addRocketSummary(rocketSummary);
			}
		}
		
		Collections.sort(spaceProgramSummary.getMissionSummaries(), new Comparator<MissionSummary>() {

			@Override
			public int compare(MissionSummary o1, MissionSummary o2) {
				int result = Integer.compare(o2.getRocketsSummary().size(), o1.getRocketsSummary().size());
				if (result == 0) {
					return (o1.getName().compareTo(o2.getName()) * -1);
				}
				return result;
			}
		});
		return spaceProgramSummary;
	}
}
