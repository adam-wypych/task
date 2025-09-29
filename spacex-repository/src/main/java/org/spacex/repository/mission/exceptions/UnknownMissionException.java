package org.spacex.repository.mission.exceptions;

import org.spacex.model.Mission;

public class UnknownMissionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409944687004865799L;
	
	public UnknownMissionException(final Mission mission) {
		super("Mission " + mission.getName() + " created outside of repository");
	}
}
