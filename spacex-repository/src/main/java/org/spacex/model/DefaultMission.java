package org.spacex.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultMission implements Mission {

	private final String name;
	private final List<Rocket> rockets = new ArrayList<>();
	private MissionStatus status;
	private MissionStatusCalculator statusCalculator;
	
	public DefaultMission(final String name) {
		this.name = name;
		this.status = MissionStatus.SCHEDULED;
		this.statusCalculator =  new MissionStatusCalculator();
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public synchronized MissionStatus getStatus() {
		return statusCalculator.status(status, rockets);
	}

	public synchronized void setStatus(final MissionStatus newStatus) {
		this.status = newStatus;
	}
	
	@Override
	public List<Rocket> getRockets() {
		return Collections.unmodifiableList(rockets);
	}
	
	public synchronized void addRocket(Rocket rocket) {
		this.rockets.add(rocket);
	}
	
	public synchronized void removeRockets() {
		this.rockets.clear();
	}
}
