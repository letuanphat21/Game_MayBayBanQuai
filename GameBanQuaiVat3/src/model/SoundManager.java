package model;

import utils.Music;

public class SoundManager implements ObserverVolume {
	private IModel model;
	private Music music;

	public SoundManager(IModel model, Music music) {
		this.model = model;
		this.music = music;
		model.registerObserverVolume(this); 
	}

	@Override
	public void updateVolume() {
		int volume = model.getVolume();
		music.setClipVolume(volume);
	}

}
