package org.spacex.model;

import java.util.Optional;

public interface Rocket {

	String getName();

	RocketStatus getStatus();

	Optional<Mission> getCurrentMission();
}
