package org.spacex.model;

import java.util.List;

public class MissionStatusCalculator {
	public MissionStatus status(final MissionStatus innerMissionStatus, final List<Rocket> rockets) {
		if (innerMissionStatus != MissionStatus.ENDED) {
			if (rockets.size() > 0) {
				if (rockets.stream().filter(e -> e.getStatus() == RocketStatus.IN_REPAIR).findAny().isPresent()) {
					return MissionStatus.PENDING;
				} else {
					return MissionStatus.IN_PROGRESS;
				}
			}
		}
		
		return innerMissionStatus;
	}
}
