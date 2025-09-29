package org.spacex.repository;

import java.util.ArrayList;
import java.util.List;

import org.spacex.model.DefaultMission;
import org.spacex.model.DefaultRocket;
import org.spacex.model.Mission;
import org.spacex.model.MissionStatus;
import org.spacex.model.Rocket;
import org.spacex.model.RocketStatus;
import org.spacex.repository.mission.exceptions.DuplicateMissionException;
import org.spacex.repository.rocket.exceptions.DuplicateRocketException;

public class SpaceXRepository {
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
		((DefaultRocket) rocket).setStatus(RocketStatus.IN_SPACE);
		((DefaultRocket) rocket).setMission((DefaultMission) mission);
		((DefaultMission) mission).setStatus(MissionStatus.IN_PROGRESS);
		((DefaultMission) mission).addRocket(rocket);
	}
}
