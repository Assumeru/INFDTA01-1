package hro.infdta011.calculation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import hro.infdta011.User;

public abstract class AbstractUserCalculator implements UserCalculator {
	private boolean union;

	public AbstractUserCalculator(boolean union) {
		this.union = union;
	}

	@Override
	public double calculate(User lhs, User rhs) {
		float[][] values = getValues(lhs.getRatings(), rhs.getRatings());
		return calculate(values[0], values[1]);
	}

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
}
