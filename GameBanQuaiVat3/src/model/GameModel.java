package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.strategypattern.Aircraft;
import model.strategypattern.Laser;

public class GameModel implements IGameModel {
	private Aircraft player;
	private List<Laser> lasers1 = new ArrayList<>();
	private List<Laser> lasers2 = new ArrayList<>();
	private List<Laser> enemyBullets = new ArrayList<>();
	private List<Enemy> enemies = new ArrayList<>();
	private List<GameObserver> observers = new ArrayList<>();
	private boolean gameOver = false;


	public GameModel() {
		// khởi tạo 5 enemy ban đầu
		for (int i = 0; i < 5; i++) {
			enemies.add(new Enemy(Enemy.randomX(), Enemy.randomY()));
		}
	}

	@Override
	public void addObserver(GameObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(GameObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (GameObserver o : observers) {
			o.update();
		}
	}

	@Override
	public void setPlayer(Aircraft player) {
		this.player = player;
	}

	@Override
	public Aircraft getPlayer() {
		return player;
	}

	@Override
	public List<Laser> getLasers1() {
		return lasers1;
	}

	@Override
	public List<Laser> getLasers2() {
		return lasers2;
	}

	@Override
	public List<Laser> getEnemyBullets() {
		return enemyBullets;
	}

	@Override
	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void gameTick() {
		updateLasers(lasers1);
		updateLasers(lasers2);
		updateEnemyBullets();
		updateEnemies();
		checkCollisions();
		checkPlayerCollision();
		notifyObservers();
	}

	private void updateLasers(List<Laser> lasers) {
		Iterator<Laser> iterator = lasers.iterator();
		while (iterator.hasNext()) {
			Laser laser = iterator.next();
			laser.move(); // ⛔ make sure move() actually moves upward
			if (laser.isOutOfScreen()) {
				iterator.remove();
			}
		}
	}

	private void updateEnemyBullets() {
		Iterator<Laser> bulletIterator = enemyBullets.iterator();
		while (bulletIterator.hasNext()) {
			Laser bullet = bulletIterator.next();
			bullet.moveDown();
			if (bullet.isOutOfScreen()) {
				bulletIterator.remove();
			}
		}
	}

	private void updateEnemies() {
		for (Enemy enemy : enemies) {
			enemy.move(); 
			int x = ThreadLocalRandom.current().nextInt(100);
			if (x < 2) {
				enemyBullets.add(new Laser(enemy.getX() + 20, enemy.getY() + 40));
			}
		}
	}

	private void checkCollisions() {
		Iterator<Enemy> enemyIterator = enemies.iterator();
		while (enemyIterator.hasNext()) {
			Enemy enemy = enemyIterator.next();
			Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), 40, 40);

			Iterator<Laser> l1 = lasers1.iterator();
			while (l1.hasNext()) {
				Laser laser = l1.next();
				Rectangle r = new Rectangle(laser.getX(), laser.getY(), 4, 15);
				if (r.intersects(enemyRect)) {
					l1.remove();
					enemy.resetPosition();
					break;
				}
			}

			Iterator<Laser> l2 = lasers2.iterator();
			while (l2.hasNext()) {
				Laser laser = l2.next();
				Rectangle r = new Rectangle(laser.getX(), laser.getY(), 4, 15);
				if (r.intersects(enemyRect)) {
					l2.remove();
					enemy.resetPosition();
					break;
				}
			}
		}
	}

	private void checkPlayerCollision() {
		if (player == null || player.getPosition() == null)
			return;

		Rectangle playerRect = new Rectangle(player.getPosition().x - 15, player.getPosition().y - 15, 40, 40);

		for (Laser bullet : enemyBullets) {
			Rectangle r = new Rectangle(bullet.getX(), bullet.getY(), 4, 15);
			if (playerRect.intersects(r)) {
				notifyGameOver();
				break;
			}
		}

		for (Enemy enemy : enemies) {
			Rectangle r = new Rectangle(enemy.getX(), enemy.getY(), 40, 40);
			if (playerRect.intersects(r)) {
				notifyGameOver();
				break;
			}
		}
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
		notifyObservers();
	}

	private void notifyGameOver() {
		if (!gameOver) {
			gameOver = true;
			for (GameObserver observer : observers) {
				if (observer instanceof GameEventListener) {
					((GameEventListener) observer).onGameOver();
				}
			}
		}
	}

}
