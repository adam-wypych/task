package org.spacex.model;

public class DefaultMission implements Mission {

	private final String name;

	public DefaultMission(final String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
}
