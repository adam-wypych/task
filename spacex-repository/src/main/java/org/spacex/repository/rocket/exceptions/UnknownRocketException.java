package org.spacex.repository.rocket.exceptions;

import org.spacex.model.Rocket;

public class UnknownRocketException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1864881341770497730L;

	public UnknownRocketException(final Rocket rocket) {
		super("Rocket " + rocket.getName() + " created outside of repository");
	}
}
