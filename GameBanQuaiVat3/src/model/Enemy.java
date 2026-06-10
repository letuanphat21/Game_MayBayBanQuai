package model;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy {
	private int x, y;
	private int speed;
	private int direction;
	private Image enemyImage;

	public Enemy(int startX, int startY) {
		this.x = startX;
		this.y = startY;
		this.speed = 2;
		this.direction = 1;
		enemyImage = new ImageIcon(getClass().getResource("/res/monster.png")).getImage();
	}

	public void move() {
		x += speed * direction;

		if (x >= 750) {
			direction = -1;
		} else if (x <= 10) {
			direction = 1;
		}
	}

	public static int randomX() {
		return new Random().nextInt(400) + 50;
	}
	public static int randomY() {
		return new Random().nextInt(200) + 10;
	}
	public void resetPosition() {
		Random rand = new Random();
		this.x = rand.nextInt(400) + 50; // X random từ 50 -> 450
		this.y = rand.nextInt(200) + 10; // Y random từ 10 -> 400
	}
	public Image getImage() {
		return enemyImage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Image getEnemyImage() {
		return enemyImage;
	}

	public void setEnemyImage(Image enemyImage) {
		this.enemyImage = enemyImage;
	}
	
}
