package hro.infdta011.calculation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import hro.infdta011.User;

public abstract class AbstractUserCalculator implements UserCalculator {
	private boolean union;
	private boolean lowestIsNearest;

	/**
	 * @param union If true only items rated by both users will be considered, if false unrated items will have a rating of 0
	 * @param lowestIsNearest If true lower values will be considered closer
	 */
	public AbstractUserCalculator(boolean union, boolean lowestIsNearest) {
		this.union = union;
		this.lowestIsNearest = lowestIsNearest;
	}

	@Override
	public double calculate(User lhs, User rhs) {
		float[][] values = getValues(lhs.getRatings(), rhs.getRatings());
		return calculate(values[0], values[1]);
	}

	/**
	 * Calculates the distance between two sets of ratings.
	 * 
	 * @param lhs The first user's ratings
	 * @param rhs The second user's ratings
	 * @return The distance between both users
	 */
	protected abstract double calculate(float[] lhs, float[] rhs);

	private float[][] getValues(Map<Integer, Float> lhs, Map<Integer, Float> rhs) {
		Set<Integer> keys = new HashSet<>(lhs.size());
		if(union) {
			for(int key : lhs.keySet()) {
				if(rhs.containsKey(key)) {
					keys.add(key);
				}
			}
		} else {
			keys.addAll(lhs.keySet());
			keys.addAll(rhs.keySet());
		}
		float[][] out = { new float[keys.size()], new float[keys.size()] };
		int i = 0;
		for(int key : keys) {
			out[0][i] = lhs.containsKey(key) ? lhs.get(key) : 0;
			out[1][i] = rhs.containsKey(key) ? rhs.get(key) : 0;
			i++;
		}
		return out;
	}

	@Override
	public boolean lowestIsNearest() {
		return lowestIsNearest;
	}
}
