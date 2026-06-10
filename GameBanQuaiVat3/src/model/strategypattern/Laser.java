package model.strategypattern;

public class Laser {
	int x, y;
	int tocDoDan;

	public Laser(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Laser(int x, int y, int tocDoDan) {
		this.x = x;
		this.y = y;
		this.tocDoDan = tocDoDan;
	}

	public void move() {
		y -= tocDoDan; // Di chuyển lên trên
	}

	public boolean isOutOfScreen() {
		return y <= 10;
	}

	public void moveDown() {
		y += 5; // Tốc độ đạn đi xuống
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

}
