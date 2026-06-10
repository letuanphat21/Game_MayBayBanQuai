package model.strategypattern;

import java.util.List;

public class SlowBullet implements Fire {

	@Override
	public void fire(int x, int y, List<Laser> list) {
		list.add(new Laser(x + 2, y - 30, 5));
		
	}

}
