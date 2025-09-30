package org.spacex.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.spacex.model.Mission;
import org.spacex.model.Rocket;
import org.spacex.model.RocketStatus;
import org.spacex.model.summary.MissionSummary;
import org.spacex.model.summary.RocketSummary;
import org.spacex.model.summary.SpaceProgramSummary;

public class SpaceXRepositorySummaryTest {

	private SpaceXRepository sut;

	@Before
	public void setUp() {
		this.sut = new SpaceXRepository();
	}
	
	@Test
	public void testBasedOnTaskExample() {
		// given
		sut.createNewMission("Mars");
		Mission luna = sut.createNewMission("Luna");
		Rocket dragon1 = sut.createNewRocket("Dragon 1");
		sut.assignRocketForMission(dragon1, luna);
		Rocket dragon2 = sut.createNewRocket("Dragon 2");
		sut.assignRocketForMission(dragon2, luna);
		sut.changeStatus(dragon2, RocketStatus.IN_REPAIR);
		Mission doubleLanding = sut.createNewMission("Double Landing");
		sut.finishMission(doubleLanding);
		Mission transit = sut.createNewMission("Transit");
		Rocket redDragon = sut.createNewRocket("Red Dragon");
		sut.assignRocketForMission(redDragon, transit);
		sut.changeStatus(redDragon, RocketStatus.ON_GROUND);
		Rocket dragonXL = sut.createNewRocket("Dragon XL");
		sut.assignRocketForMission(dragonXL, transit);
		Rocket falconHeavy = sut.createNewRocket("Falcon Heavy");
		sut.assignRocketForMission(falconHeavy, transit);
		Mission luna2 = sut.createNewMission("Luna 2");
		Mission verticalLanding = sut.createNewMission("Vertical Landing");
		sut.finishMission(verticalLanding);
		
		SpaceProgramSummary summary = sut.generateSummary();
	
		StringBuilder strBuilder = new StringBuilder("");
		for (MissionSummary missionSummary: summary.getMissionSummaries()) {
			strBuilder.append("- " + missionSummary.getName() + " - " + missionSummary.getStatus() + " - Dragons: " + missionSummary.getRocketsSummary().size() + "\n");
			for (RocketSummary rocketSummary: missionSummary.getRocketsSummary()) {
				strBuilder.append("  - " + rocketSummary.getName() + " - " + rocketSummary.getStatus() + "\n");
			}
		}
		
		assertThat(strBuilder.toString()).isEqualToIgnoringNewLines("- Transit - In progress - Dragons: 3\r\n"
				+ "  - Red Dragon - On ground\r\n"
				+ "  - Dragon XL - In space\r\n"
				+ "  - Falcon Heavy - In space\r\n"
				+ "- Luna - Pending - Dragons: 2\r\n"
				+ "  - Dragon 1 - In space\r\n"
				+ "  - Dragon 2 - In repair\r\n"
				+ "- Vertical Landing - Ended - Dragons: 0\r\n"
				+ "- Mars - Scheduled - Dragons: 0\r\n"
				+ "- Luna 2 - Scheduled - Dragons: 0\r\n"
				+ "- Double Landing - Ended - Dragons: 0\r\n"
				+ "");
	}
	
	
}
