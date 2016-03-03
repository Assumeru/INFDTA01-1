package hro.infdta011.calculation;

public class EuclideanDistance extends AbstractUserCalculator {
	public EuclideanDistance() {
		super(true);
	}

	@Override
	protected double calculate(float[] lhs, float[] rhs) {
		double sum = 0;
		for(int i = 0; i < lhs.length; i++) {
			double delta = lhs[i] - rhs[i];
			sum += delta * delta;
		}
		return Math.sqrt(sum);
	}
}
