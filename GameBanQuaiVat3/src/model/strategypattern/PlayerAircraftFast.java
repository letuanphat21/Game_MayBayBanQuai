package model.strategypattern;

public class PlayerAircraftFast extends Aircraft {

	public PlayerAircraftFast(int x, int y) {
		super(x, y, "/res/942346.png");
		fire = new FastBullet();
	}

//	@Override
//	public void fire(List<Laser> lasers) {
//		lasers.add(new Laser(this.position.x + 2, this.position.y - 30)); // Bắn từ giữa máy bay
//
//	}

}
