package org.spacex.model;

import java.util.Optional;

public class DefaultRocket implements Rocket {
	private final String name;
	private DefaultMission currentMission = null;
	
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

	protected void setMission(final DefaultMission mission) {
		this.currentMission = mission;
	}
	
	@Override
	public Optional<Mission> getCurrentMission() {
		return Optional.ofNullable(currentMission);
	}
}
