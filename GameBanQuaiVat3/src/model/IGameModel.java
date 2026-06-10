package model;

import java.util.List;

import model.strategypattern.Aircraft;
import model.strategypattern.Laser;

public interface IGameModel {
	void addObserver(GameObserver o);
    void removeObserver(GameObserver o);
    void notifyObservers();

    void setPlayer(Aircraft player);
    Aircraft getPlayer();

    List<Laser> getLasers1();
    List<Laser> getLasers2();
    List<Laser> getEnemyBullets();
    List<Enemy> getEnemies();

    void gameTick();
	void setGameOver(boolean b);
	boolean isGameOver();

}
