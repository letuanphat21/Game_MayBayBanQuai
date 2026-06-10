package model;

public interface IModel {

	public void registerObserverVolume(ObserverVolume o);

	public void removeObserverVolume(ObserverVolume o);

	public void notifyObserverVolume();

	public void setVolume(int value);

	public int getVolume();

}
