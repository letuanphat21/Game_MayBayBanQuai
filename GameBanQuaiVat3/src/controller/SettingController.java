package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ennum.SoundPath;
import model.IModel;
import utils.Music;
import view.ScreenMain;
import view.SettingPanel;

public class SettingController implements ISettingController, ISoundButtonHover {
	private IModel model;
	private SettingPanel view;
	private IScreenNavigator navigator;
	private Music music;

	public SettingController(IModel model, IScreenNavigator navigator, Music music) {
		this.model = model;
		this.navigator = navigator;
		this.music = music;
	}

	public void setView(SettingPanel view) {
		this.view = view;

		this.view.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleBack();
			}
		});
	}

	@Override
	public void changeVolume(int value) {
		model.setVolume(value);
	}

	public void handleBack() {
		navigator.goBack(); // Đúng chuẩn MVC hơn
	}

	@Override
	public void playHoverSound() {
		if (music != null && SoundPath.HOVER.getPath() != null) {
			music.playSound(SoundPath.HOVER.getPath());
		}
	}

}
