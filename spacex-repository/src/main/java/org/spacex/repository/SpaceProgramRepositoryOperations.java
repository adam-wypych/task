package org.spacex.repository;

import org.spacex.model.Mission;
import org.spacex.model.Rocket;
import org.spacex.model.summary.SpaceProgramSummary;

public interface SpaceProgramRepositoryOperations {
	void assignRocketForMission(final Rocket rocket, final Mission mission);
	
	SpaceProgramSummary generateSummary();
}
