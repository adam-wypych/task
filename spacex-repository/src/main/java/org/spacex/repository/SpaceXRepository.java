package org.spacex.repository;

import org.spacex.model.DefaultMission;
import org.spacex.model.Mission;

public class SpaceXRepository {

	public Mission createNewMission(final String missionName) {
		return new DefaultMission(missionName);
	}
}
