package hro.infdta011.calculation;

public class PearsonCoefficient extends AbstractUserCalculator {
	public PearsonCoefficient() {
		super(true);
	}

	@Override
	protected double calculate(float[] lhs, float[] rhs) {
		double avgX = 0;
		double avgY = 0;
		for(int i = 0; i < lhs.length; i++) {
			avgX += lhs[i];
			avgY += rhs[i];
		}
		avgX /= lhs.length;
		avgY /= rhs.length;
		double sumXY = 0;
		double sumXX = 0;
		double sumYY = 0;
		for(int i = 0; i < lhs.length; i++) {
			double x = lhs[i] - avgX;
			double y = rhs[i] - avgY;
			sumXY += x * y;
			sumXX += x * x;
			sumYY += y * y;
		}
		return sumXY / Math.sqrt(sumXX * sumYY);
	}
}
