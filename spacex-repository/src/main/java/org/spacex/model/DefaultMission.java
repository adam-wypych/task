package org.spacex.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultMission implements Mission {

	private final String name;
	private final List<Rocket> rockets = new ArrayList<>();
	
	public DefaultMission(final String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public MissionStatus getStatus() {
		return MissionStatus.SCHEDULED;
	}

	@Override
	public List<Rocket> getRockets() {
		return Collections.unmodifiableList(rockets);
	}
}
