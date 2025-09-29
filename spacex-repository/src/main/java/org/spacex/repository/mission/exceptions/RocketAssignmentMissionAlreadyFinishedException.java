package org.spacex.repository.mission.exceptions;

import org.spacex.model.Mission;
import org.spacex.model.Rocket;

public class RocketAssignmentMissionAlreadyFinishedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2080911372291171279L;
	public RocketAssignmentMissionAlreadyFinishedException(final Rocket rocket, final Mission mission) {
		super("Mission " + mission.getName() + " was already finished. Therefore adding rocket " + rocket.getName() + " is impossible.");
	}
}
