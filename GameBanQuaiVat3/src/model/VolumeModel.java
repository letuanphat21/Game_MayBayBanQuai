package model;

import java.util.ArrayList;
import java.util.List;

public class VolumeModel implements IModel {
	private int volume;
	private final List<ObserverVolume> observers = new ArrayList<>();

	public VolumeModel() {
		this.volume = 50;
	}

	@Override
	public void registerObserverVolume(ObserverVolume o) {
		observers.add(o);

	}

	@Override
	public void removeObserverVolume(ObserverVolume o) {
		observers.remove(o);

	}

	@Override
	public void notifyObserverVolume() {
		for (ObserverVolume o : observers) {
			o.updateVolume();
		}

	}

	@Override
	public void setVolume(int value) {
		this.volume = value;
		notifyObserverVolume();

	}

	@Override
	public int getVolume() {
		return volume;
	}

}
