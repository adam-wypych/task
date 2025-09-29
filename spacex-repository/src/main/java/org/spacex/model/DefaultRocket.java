package org.spacex.model;

import java.util.Optional;

public class DefaultRocket implements Rocket {
	private final String name;
	private volatile RocketStatus status;
	private DefaultMission currentMission = null;
	
	public DefaultRocket(final String name) {
		this.name = name;
		this.status = RocketStatus.ON_GROUND;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public synchronized RocketStatus getStatus() {
		return status;
	}
	
	public synchronized void setStatus(final RocketStatus newStatus) {
		this.status = newStatus;
	}

	public synchronized void setMission(final DefaultMission mission) {
		this.currentMission = mission;
	}
	
	@Override
	public Optional<Mission> getCurrentMission() {
		return Optional.ofNullable(currentMission);
	}
}
