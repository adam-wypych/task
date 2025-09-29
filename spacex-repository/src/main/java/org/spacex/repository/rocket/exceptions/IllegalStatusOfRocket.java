package org.spacex.repository.rocket.exceptions;

public class IllegalStatusOfRocket extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 366782571723770906L;

	public IllegalStatusOfRocket(final String errorMessage) {
		super(errorMessage);
	}
}
