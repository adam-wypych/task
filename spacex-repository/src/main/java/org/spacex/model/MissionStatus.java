package org.spacex.model;

public enum MissionStatus {
	SCHEDULED("Scheduled", "initial status, where no rockets are assigned to the mission"),
	PENDING("Pending", "at least one rocket is assigned and any of them has 'in repair' status"),
	IN_PROGRESS("In progress", "at least one rocket is assigned and no one has 'in repair'"),
	ENDED("Ended", "final status of mission no rockets are assigned");
	
	private final String displayName;
	@SuppressWarnings("unused")
	private final String description;
	
	private MissionStatus(final String displayName, final String description) {
		this.displayName = displayName;
		this.description = description;
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
