package org.spacex.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;
import org.spacex.model.DefaultMission;
import org.spacex.model.Mission;
import org.spacex.model.MissionStatus;
import org.spacex.model.Rocket;
import org.spacex.model.RocketStatus;
import org.spacex.repository.mission.exceptions.DuplicateMissionException;
import org.spacex.repository.mission.exceptions.RocketAssignmentMissionAlreadyFinishedException;
import org.spacex.repository.rocket.exceptions.DuplicateRocketException;
import org.spacex.repository.rocket.exceptions.RocketAlreadyAssignedToMissionException;

public class SpaceXRepositoryTest {

	private SpaceXRepository sut;

	@Before
	public void setUp() {
		this.sut = new SpaceXRepository();
	}

	@Test
	public void givenMissionDoesNotExist__whenMissionIsCreated__missionObjectShouldBeInCorrectState() {
		// given & when
		Mission mission = sut.createNewMission("MyMission");

		// then
		assertThat(mission).isNotNull();
		assertThat(mission.getName()).isEqualTo("MyMission");
		assertThat(mission.getStatus()).isEqualTo(MissionStatus.SCHEDULED);
	}

	@Test
	public void givenTryToCreateSameMissionTwice__whenMissionIsCreated__thenDuringSecondTimeDuplicateElementShouldBeThrown() {
		// given
		@SuppressWarnings("unused")
		Mission mission = sut.createNewMission("MyMission");
		try {
			// when & then
			@SuppressWarnings("unused")
			Mission missionClone = sut.createNewMission("MyMission");
			fail("expected failue due to same mission exists");
		} catch (DuplicateMissionException e) {
			assertThat(e).hasMessage("Mission with name " + "MyMission" + " already exists in repository");
		}
	}
	
	@Test
	public void givenRocketDoesNotExist__whenRocketIsBeingCreated__thenRocketShouldHaveCorrectState() {
		// given & when
		Rocket rocket = sut.createNewRocket("MyRocket");
		
		// then
		assertThat(rocket).isNotNull();
		assertThat(rocket.getName()).isEqualTo("MyRocket");
		assertThat(rocket.getStatus()).isEqualTo(RocketStatus.ON_GROUND);
	}
	
	@Test
	public void givenTryToCreateSameRocketTwice__whenRocketisCreate__thenDuringSecondTimeDuplicateElementShouldBeThrown() {
		// given
		@SuppressWarnings("unused")
		Rocket rocket = sut.createNewRocket("MyRocket");
		try {
			@SuppressWarnings("unused")
			Rocket rocketClone = sut.createNewRocket("MyRocket");
			fail("expected failure due to rocket exists");
		} catch (DuplicateRocketException e) {
			assertThat(e).hasMessage("Rocket with name " + "MyRocket" + " already exists in repository");
		}
	}
	
	@Test
	public void givenFreeRocket__whenAssignmentToScheduledMission__thenRocketAndMissionStateShouldBeUpdated() {
		// given
		Rocket rocket = sut.createNewRocket("RocketOne");
		Mission mission = sut.createNewMission("MyMission");
		
		// when
		sut.assignRocketForMission(rocket, mission);
		
		// then
		assertThat(rocket.getStatus()).isEqualTo(RocketStatus.IN_SPACE);
		assertThat(rocket.getCurrentMission()).contains(mission);
		assertThat(mission.getRockets()).contains(rocket);
		assertThat(mission.getStatus()).isEqualTo(MissionStatus.IN_PROGRESS);
	}
	
	@Test(expected = RocketAlreadyAssignedToMissionException.class)
	public void givenRocketAssignedToMission__whenAssignmentToScheduledMission__thenRocketInUseExceptionShouldBeThrown() {
		// given 
		Rocket rocket = sut.createNewRocket("My rocket");
		Mission missionOne = sut.createNewMission("old mission");
		sut.assignRocketForMission(rocket, missionOne);
		
		Mission missionTwo = sut.createNewMission("New mission");
		
		//when
		sut.assignRocketForMission(rocket, missionTwo);
		fail("Expected failure due to duplicate mission");
	}
	
	@Test(expected = RocketAssignmentMissionAlreadyFinishedException.class)
	public void givenMissionIsFinished__whenAssignmentRocket__thenRocketAssignmentExceptionShouldBeThrown() {
		// given
		Rocket rocket = sut.createNewRocket("My rocket");
		DefaultMission mission = (DefaultMission) sut.createNewMission("old mission");
		mission.setStatus(MissionStatus.ENDED);
		
		sut.assignRocketForMission(rocket, mission);
		fail("Expected failure due to mission finished.");
	}
}
