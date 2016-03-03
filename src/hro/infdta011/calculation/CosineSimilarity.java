package hro.infdta011.calculation;

public class CosineSimilarity extends AbstractUserCalculator {
	public CosineSimilarity() {
		super(false);
	}

	@Override
	protected double calculate(float[] lhs, float[] rhs) {
		double dot = 0;
		double sumXX = 0;
		double sumYY = 0;
		for(int i = 0; i < lhs.length; i++) {
			dot += lhs[i] * rhs[i];
			sumXX += lhs[i] * lhs[i];
			sumYY += rhs[i] * rhs[i];
		}
		return dot / Math.sqrt(sumXX * sumYY);
	}
}
