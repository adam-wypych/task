package org.spacex.model;

import java.util.List;

public interface Mission {

	String getName();

	MissionStatus getStatus();

	List<Rocket> getRockets();
}
