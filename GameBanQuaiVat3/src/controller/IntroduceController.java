package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ennum.SoundPath;
import utils.Music;
import view.IntroducePanel;

public class IntroduceController implements ISoundButtonHover {
	private IntroducePanel view;
	private IScreenNavigator navigator;
	private Music music;


	public IntroduceController(IScreenNavigator navigator, Music music) {
		this.navigator = navigator;
		this.music = music;
	}

	public void setView(IntroducePanel view) {
		this.view = view;

		view.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleBack();
			}
		});
	}

	private void handleBack() {
		navigator.goBack(); 
	}

	@Override
	public void playHoverSound() {
		if (music != null && SoundPath.HOVER.getPath() != null) {
			music.playSound(SoundPath.HOVER.getPath());
		}
	}
}
