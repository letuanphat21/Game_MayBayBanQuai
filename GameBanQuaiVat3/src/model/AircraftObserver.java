package model;

import model.strategypattern.Aircraft;

public interface AircraftObserver {
	void onAircraftConfirmed(Aircraft selectedAircraft);
}
