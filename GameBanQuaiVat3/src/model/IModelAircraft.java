package model;

import model.strategypattern.Aircraft;

public interface IModelAircraft {
	public void setAircraft(Aircraft aircraft);

	public Aircraft getAircraft();

	public void addObserver(AircraftObserver observer);

	public void removeObserver(AircraftObserver observer);

	public void notifyObserver();

}
