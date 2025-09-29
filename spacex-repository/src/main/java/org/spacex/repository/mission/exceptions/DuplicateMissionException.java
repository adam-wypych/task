package org.spacex.repository.mission.exceptions;

public class DuplicateMissionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3732213328569895315L;

	public DuplicateMissionException(String missionName) {
		super("Mission with name " + missionName + " already exists in repository");
	}
}
