package hro.infdta011.calculation;

import hro.infdta011.User;

public interface UserCalculator {
	/**
	 * Calculates the distance between two users.
	 * 
	 * @param lhs The first user
	 * @param rhs The second user
	 * @return The distance between lhs and rhs
	 */
	public double calculate(User lhs, User rhs);

	/**
	 * @return True if lower values are closer
	 */
	public boolean lowestIsNearest();
}
