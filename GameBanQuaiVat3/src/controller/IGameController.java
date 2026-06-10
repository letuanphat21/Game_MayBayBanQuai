package controller;

import java.awt.Point;

public interface IGameController {
	void firePrimary();

	void fireSecondary();

	void movePlayer(Point position);

	void outGame();
}
