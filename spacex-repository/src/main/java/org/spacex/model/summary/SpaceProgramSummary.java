package org.spacex.model.summary;

import java.util.ArrayList;
import java.util.List;

public class SpaceProgramSummary {
	private final List<MissionSummary> missionSummaries = new ArrayList<>();
	
	public void addMissionSummary(final MissionSummary missionSummary) {
		this.missionSummaries.add(missionSummary);
	}

	public List<MissionSummary> getMissionSummaries() {
		return missionSummaries;
	}
}
