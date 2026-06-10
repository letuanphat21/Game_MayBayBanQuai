package controller;

import java.awt.Point;

import javax.swing.JOptionPane;

import model.Enemy;
import model.GameEventListener;
import model.IGameModel;
import model.IModel;
import model.strategypattern.Aircraft;
import utils.Music;
import view.MenuPanel;

public class GameController implements IGameController, GameEventListener {
	private  IGameModel model;
	private IScreenNavigator navigator;
	private Music music;
	private IModel iModel;

	public GameController(IScreenNavigator navigator, IGameModel model, IModel iModel, Music music) {
		this.model = model;
		this.navigator = navigator;
		this.music = music;

		this.iModel = iModel;
		model.addObserver(this);
	}

	@Override
	public void firePrimary() {
		Aircraft player = model.getPlayer();
		if (player != null && player.getPosition() != null) {
			player.performFire(player.getPosition().x, player.getPosition().y, model.getLasers1());
		}
	}

	@Override
	public void fireSecondary() {
		Aircraft player = model.getPlayer();
		if (player != null && player.getPosition() != null) {
			player.performFire(player.getPosition().x, player.getPosition().y, model.getLasers2());
		}
	}

	@Override
	public void movePlayer(Point position) {
		Aircraft player = model.getPlayer();
		if (player != null) {
			player.setPosition(position);
		}
	}

	@Override
	public void onGameOver() {
		int choice = JOptionPane.showConfirmDialog(null, "Bạn đã thua! Bạn có muốn chơi lại không?", "Game Over",
				JOptionPane.YES_NO_OPTION);

		if (choice == JOptionPane.YES_OPTION) {
			// Reset game logic
			model.getLasers1().clear();
			model.getLasers2().clear();
			model.getEnemyBullets().clear();
			model.getEnemies().clear();
			for (int i = 0; i < 5; i++) {
				model.getEnemies().add(new Enemy(Enemy.randomX(), Enemy.randomY()));
			}

			model.getPlayer().setPosition(new Point(200, 400));
			model.setGameOver(false);
		} else {
			MenuController menuController = new MenuController(null, navigator, iModel, music);
			MenuPanel menuPanel = new MenuPanel(menuController, menuController);
			menuController.setView(menuPanel);
			navigator.switchPanel(menuPanel);
			model.setGameOver(true);
		}
	}

	public void outGame() {
		MenuController menuController = new MenuController(null, navigator, iModel, music);
		MenuPanel menuPanel = new MenuPanel(menuController, menuController);
		menuController.setView(menuPanel);
		navigator.switchPanel(menuPanel);
		model.setGameOver(true);
	}

	@Override
	public void update() {
		// Được gọi khi có thay đổi từ model (ví dụ khi game over)
	}

}
