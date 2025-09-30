package org.spacex.repository;

import org.spacex.model.Rocket;
import org.spacex.model.RocketStatus;

public interface SpaceXRocketRepository {
	Rocket createNewRocket(final String rocketName);
	
	void changeStatus(final Rocket rocket, final RocketStatus status);
}
