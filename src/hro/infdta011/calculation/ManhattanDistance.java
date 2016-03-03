package hro.infdta011.calculation;

public class ManhattanDistance extends AbstractUserCalculator {
	public ManhattanDistance() {
		super(true);
	}

	@Override
	protected double calculate(float[] lhs, float[] rhs) {
		double sum = 0;
		for(int i = 0; i < lhs.length; i++) {
			sum += Math.abs(lhs[i] - rhs[i]);
		}
		return sum;
	}
}
