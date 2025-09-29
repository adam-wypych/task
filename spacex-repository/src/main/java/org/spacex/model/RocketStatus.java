package org.spacex.model;

public enum RocketStatus {
	ON_GROUND("On ground", "Initial status and it is not assigned to the mission"),
	IN_SPACE("In space", "the rocket is assigned to the mission"),
	IN_REPAIR("In repair", "the rocket is due to repair and its implies 'Pending' status for mission");

	private final String displayName;
	@SuppressWarnings("unused")
	private final String description;

	private RocketStatus(final String displayName, final String description) {
		this.displayName = displayName;
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}
}
