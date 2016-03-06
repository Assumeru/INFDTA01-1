package hro.infdta011.calculation;

import hro.infdta011.User;

public interface UserCalculator {
	public double calculate(User lhs, User rhs);

	public boolean lowestIsNearest();
}
