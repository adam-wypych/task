package org.spacex.repository;

import org.spacex.model.Mission;

public interface SpaceXMissionRepository {
	Mission createNewMission(final String missionName);
	
	void finishMission(final Mission mission);
}
