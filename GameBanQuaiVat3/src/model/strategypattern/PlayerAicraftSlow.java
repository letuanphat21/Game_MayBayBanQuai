package model.strategypattern;

public class PlayerAicraftSlow extends Aircraft {

	public PlayerAicraftSlow(int x, int y) {
		super(x, y, "/res/jet.png");
		fire = new SlowBullet();
		
	}

}
