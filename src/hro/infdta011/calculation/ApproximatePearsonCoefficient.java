package hro.infdta011.calculation;

public class ApproximatePearsonCoefficient extends AbstractUserCalculator {
	public ApproximatePearsonCoefficient() {
		super(true, false);
	}

	@Override
	protected double calculate(float[] lhs, float[] rhs) {
		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumXX = 0;
		double sumYY = 0;
		for(int i = 0; i < lhs.length; i++) {
			sumX += lhs[i];
			sumY += rhs[i];
			sumXX += lhs[i] * lhs[i];
			sumYY += rhs[i] * rhs[i];
			sumXY += lhs[i] * rhs[i];
		}
		return (sumXY - sumX * sumY / lhs.length) / Math.sqrt((sumXX - sumX * sumX / lhs.length) * (sumYY - sumY * sumY / lhs.length));
	}
}
