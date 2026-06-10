package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Stack;

import javax.swing.JPanel;

import model.IModel;
import model.SoundManager;
import model.VolumeModel;
import utils.Music;
import view.MenuPanel;
import view.ScreenMain;

public class ScreenMainController implements IScreenNavigator {
	private ScreenMain screenMain;
	private Music music;
	private IModel model;
	private SoundManager soundManager;
	private Stack<JPanel> panelStack = new Stack<>();

	public ScreenMainController(ScreenMain screenMain) {
		this.screenMain = screenMain;
		this.music = new Music();
		this.model = new VolumeModel();
		this.soundManager = new SoundManager(model, music);
	}

	public void startApp() {
		music.playBackgroundMusic("/res/gamems.wav");
		MenuController menuController = new MenuController(null, this, model, music);
		MenuPanel menuPanel = new MenuPanel(menuController, menuController);
		menuController.setView(menuPanel);
		switchPanel(menuPanel);
	}

	@Override
	public void switchPanel(JPanel panel) {
		screenMain.getContentPane().removeAll();
		screenMain.getContentPane().add(panel, BorderLayout.CENTER);
		panelStack.push(panel);
		screenMain.revalidate();
		screenMain.repaint();
	}

	@Override
	public void goBack() {
		if (panelStack.size() > 1) {
			panelStack.pop(); // remove current
			JPanel previous = panelStack.peek(); // get previous
			screenMain.getContentPane().removeAll();
			screenMain.getContentPane().add(previous, BorderLayout.CENTER);
			screenMain.revalidate();
			screenMain.repaint();
		}
	}

	public Music getMusic() {
		return music;
	}

	public IModel getModel() {
		return model;
	}

}
