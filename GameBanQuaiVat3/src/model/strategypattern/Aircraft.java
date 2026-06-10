package model.strategypattern;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.util.List;

import javax.swing.ImageIcon;

public abstract class Aircraft {
	protected Point position;
	protected Image image;
	protected Fire fire;

	public Aircraft(int x, int y, String imagePath) {
		this.position = new Point(x, y);
		this.image = new ImageIcon(getClass().getResource(imagePath)).getImage().getScaledInstance(60, 60,
				Image.SCALE_SMOOTH);
	}

	public void draw(Graphics2D g2d, ImageObserver ob) {
		g2d.drawImage(image, this.position.x - 25, this.position.y - 25, 60, 60, ob);
	}

//	public abstract void fire(List<Laser> lasers);

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void performFire(int x, int y, List<Laser> lasers) {
		fire.fire(x, y, lasers);
	}

}
