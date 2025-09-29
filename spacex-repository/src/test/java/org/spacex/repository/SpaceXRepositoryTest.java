package org.spacex.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;
import org.spacex.model.Mission;
import org.spacex.model.MissionStatus;
import org.spacex.repository.mission.exceptions.DuplicateMissionException;

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
	public void givenTryToCreateSameMissionTwic__whenMissionIsCreated__thenDuringSecondTimeDuplicateElementShouldBeThrown() {
		// given
		@SuppressWarnings("unused")
		Mission mission = sut.createNewMission("MyMission");
		try {
			@SuppressWarnings("unused")
			Mission missionClone = sut.createNewMission("MyMission");
			fail("expected failue due to same mission exists");
		} catch (DuplicateMissionException e) {
			assertThat(e).hasMessage("Mission with name " + "MyMission" + " already exists in repository");
		}

	}
}
