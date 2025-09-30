package org.spacex.model.summary;

public class RocketSummary {
	private final String name;
	private final String status;
	
	public RocketSummary(String name, String status) {
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}
}
