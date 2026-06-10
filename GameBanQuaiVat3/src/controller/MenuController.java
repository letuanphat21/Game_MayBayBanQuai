package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ennum.SoundPath;
import model.AircraftSelectionModel;
import model.IModel;
import model.IModelAircraft;
import utils.Music;
import view.ChoosePanel;
import view.IntroducePanel;
import view.MenuPanel;
import view.SettingPanel;

public class MenuController implements ISoundButtonHover {
	private MenuPanel view;
	private IScreenNavigator navigator;
	private IModel model;
	private Music music;

	public MenuController(MenuPanel view, IScreenNavigator navigator, IModel model, Music music) {
		this.view = view;
		this.navigator = navigator;
		this.model = model;
		this.music = music;

		if (view != null) {
			setupListeners();
		}
	}

	// Cho phép gán view sau (nếu cần inject controller trước)
	public void setView(MenuPanel view) {
		this.view = view;
		setupListeners();
	}

	private void setupListeners() {
		view.getBtnStart().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleStart();

			}
		});
		view.getBtnSettings().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleSettings();

			}
		});
		view.getBtnGuide().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleGuide();

			}
		});
	}

	private void handleStart() {
		IModelAircraft aircraftModel = new AircraftSelectionModel();
		ChooseAircraftController chooseController = new ChooseAircraftController(null, aircraftModel, model, navigator,
				music);
		ChoosePanel choosePanel = new ChoosePanel(aircraftModel, chooseController, this);
		chooseController.setView(choosePanel);

		navigator.switchPanel(choosePanel);
	}

	private void handleSettings() {
		SettingController settingController = new SettingController(model, navigator, music);
		SettingPanel settingPanel = new SettingPanel(model, settingController, settingController);
		settingController.setView(settingPanel);
		navigator.switchPanel(settingPanel);
	}

	private void handleGuide() {
		IntroduceController introduceController = new IntroduceController(navigator, music);
		IntroducePanel introducePanel = new IntroducePanel(introduceController);
		introduceController.setView(introducePanel);
		navigator.switchPanel(introducePanel);
	}

	@Override
	public void playHoverSound() {
		if (music != null) {
			music.playSound(SoundPath.HOVER.getPath());
		}
	}
}
