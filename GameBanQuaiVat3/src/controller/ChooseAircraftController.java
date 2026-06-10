package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ennum.SoundPath;
import model.GameModel;
import model.IGameModel;
import model.IModel;
import model.IModelAircraft;
import model.strategypattern.Aircraft;
import model.strategypattern.PlayerAicraftSlow;
import model.strategypattern.PlayerAircraftFast;
import utils.Music;
import view.ChoosePanel;
import view.GamePanel;

public class ChooseAircraftController implements IChooseAircraftController, ISoundButtonHover {
	private ChoosePanel view;
	private IModelAircraft model;
	private IScreenNavigator navigator;
	private Music music;
	private IModel imodel;

	public ChooseAircraftController(ChoosePanel view, IModelAircraft model, IModel i, IScreenNavigator navigator,
			Music music) {
		this.view = view;
		this.model = model;
		this.navigator = navigator;
		this.music = music;
		this.imodel = i;
	}

	@Override
	public void onConfirmAircraftSelection() {
		String selectedName = view.getSelectedAircraftName();
		Aircraft selectedAircraft;

		// Chọn máy bay dựa trên tên
		if ("Hoàng tử sa mạc".equals(selectedName)) {
			selectedAircraft = new PlayerAircraftFast(0, 0);
		} else if ("Đại bàng xanh".equals(selectedName)) {
			selectedAircraft = new PlayerAicraftSlow(0, 0);
		} else {
			selectedAircraft = new PlayerAircraftFast(0, 0);
		}

		model.setAircraft(selectedAircraft); 


		IGameModel gameModel = new GameModel();
		gameModel.setPlayer(selectedAircraft);


		IGameController gameController = new GameController(navigator, gameModel, imodel, music);


		GamePanel gamePanel = new GamePanel(gameModel, gameController);


		navigator.switchPanel(gamePanel);
		gamePanel.requestFocusInWindow();
	}

	public void setView(ChoosePanel view) {
		this.view = view;
		view.getBackButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleBack();

			}
		});
	}

	public void handleBack() {
		navigator.goBack();
	}

	@Override
	public void playHoverSound() {
		if (music != null) {
			music.playSound(SoundPath.HOVER.getPath());
		}
	}

	@Override
	public void onAircraftSelected(int index) {
		view.updateAircraftImage(index);

	}

}
