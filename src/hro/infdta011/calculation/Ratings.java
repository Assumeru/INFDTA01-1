package hro.infdta011.calculation;

import hro.infdta011.Neighbour;

public class Ratings {
	/**
	 * Predicts what rating a user would give an item.
	 * 
	 * @param neighbours The user's neighbours
	 * @param item The item's id
	 * @return The predicted rating
	 */
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
}
