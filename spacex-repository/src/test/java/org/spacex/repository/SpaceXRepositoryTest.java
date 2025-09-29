package org.spacex.repository;

import org.junit.Before;
import org.junit.Test;

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
	}
}
