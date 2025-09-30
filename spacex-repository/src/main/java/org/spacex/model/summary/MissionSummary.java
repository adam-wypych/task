package org.spacex.model.summary;

import java.util.ArrayList;
import java.util.List;

public class MissionSummary {
	private final String name;
	private final String status;
	private final List<RocketSummary> rocketsSummary = new ArrayList<>();
	
	public MissionSummary(String name, String status) {
		this.name = name;
		this.status = status;
	}
	
	public void addRocketSummary(final RocketSummary rocketSummary) {
		this.rocketsSummary.add(rocketSummary);
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public List<RocketSummary> getRocketsSummary() {
		return rocketsSummary;
	}
}
