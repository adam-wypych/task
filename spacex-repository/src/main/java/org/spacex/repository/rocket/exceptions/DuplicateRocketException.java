package org.spacex.repository.rocket.exceptions;

public class DuplicateRocketException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8020014422390811800L;

	public DuplicateRocketException(String rocketName) {
		super("Rocket with name " + rocketName + " already exists in repository");
	}	
}
