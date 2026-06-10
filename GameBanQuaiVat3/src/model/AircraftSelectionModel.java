package model;

import java.util.ArrayList;
import java.util.List;

import model.strategypattern.Aircraft;

public class AircraftSelectionModel implements IModelAircraft {
	private Aircraft aircraft;
	private List<AircraftObserver> observers;

	public AircraftSelectionModel() {
		observers = new ArrayList<>();
	}

	@Override
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
		notifyObserver();

	}

	@Override
	public Aircraft getAircraft() {
		return aircraft;
	}

	@Override
	public void addObserver(AircraftObserver observer) {
		observers.add(observer);

	}

	@Override
	public void removeObserver(AircraftObserver observer) {
		observers.remove(observer);

	}

	@Override
	public void notifyObserver() {
		for (AircraftObserver observer : observers) {
			observer.onAircraftConfirmed(aircraft);
		}

	}

}
