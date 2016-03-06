package hro.infdta011.calculation;

import java.util.Arrays;

import hro.infdta011.Neighbour;

public class Ratings {
	public static float predict(Iterable<Neighbour> neighbours, int item) {
		double sumD = 0;
		double sumR = 0;
		for(Neighbour neighbour : neighbours) {
			float rating = neighbour.getUser().getRating(item);
			if(rating > 0) {
				sumR += neighbour.getDistance() * rating;
				sumD += neighbour.getDistance();
			}
		}
		return (float) (sumR / sumD);
	}

	public static float predict(Neighbour[] neighbours, int item) {
		return predict(Arrays.asList(neighbours), item);
	}
}
