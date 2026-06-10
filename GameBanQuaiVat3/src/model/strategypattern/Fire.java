package model.strategypattern;

import java.util.List;

import model.strategypattern.Laser;

public interface Fire {

	public void fire(int x,int y,List<Laser>list);

}
