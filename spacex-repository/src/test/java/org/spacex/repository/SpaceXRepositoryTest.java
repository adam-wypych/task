package org.spacex.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.spacex.model.Mission;

public class SpaceXRepositoryTest {

	private SpaceXRepository sut;

	@Before
	public void setUp() {
		this.sut = new SpaceXRepository();
	}
	
	@Test
	public void givenMissionDoesNotExist__whenMissionIsCreated__operationShouldSuccess() {
		// given & when
		Mission mission = sut.createNewMission("MyMission");
		
		// then
		assertThat(mission).isNotNull();
		assertThat(mission.getName()).isEqualTo("MyMission");
	}
}
