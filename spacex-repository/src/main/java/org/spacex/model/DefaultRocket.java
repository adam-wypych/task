package org.spacex.model;

public class DefaultRocket implements Rocket {
	private final String name;
	
	public DefaultRocket(final String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public RocketStatus getStatus() {
		return RocketStatus.ON_GROUND;
	}
}
