package org.spacex.repository.rocket.exceptions;

import org.spacex.model.Rocket;

public class RocketAlreadyAssignedToMissionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1679422784975380254L;
	
	public RocketAlreadyAssignedToMissionException(final Rocket rocket) {
		super("Rocket " + rocket.getName() + " already assigned to the mission.");
	}
}
