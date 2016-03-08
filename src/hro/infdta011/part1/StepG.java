package hro.infdta011.part1;

import hro.infdta011.Neighbour;
import hro.infdta011.Rating;
import hro.infdta011.TopList;
import hro.infdta011.User;
import hro.infdta011.calculation.ApproximatePearsonCoefficient;
import hro.infdta011.calculation.Ratings;
import hro.infdta011.calculation.UserCalculator;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StepG {
	private static final UserCalculator PEARSON = new ApproximatePearsonCoefficient();

	/**
	 * Predicts ratings for user 186.
	 * 
	 * @param input The location of the ratings file
	 */
	public void run(String input) {
		Map<Integer, User> users = new StepB("\t").run(input);
		User user = users.get(186);
		Collection<Neighbour> neighbours = user.calculateNeighbours(users.values(), PEARSON, 25);
		Set<Integer> predictableItems = getPredictableItems(neighbours, user);
		printRatings(predictRatings(neighbours, predictableItems, 8, 0));
		printRatings(predictRatings(neighbours, predictableItems, 8, 3));
	}

	/**
	 * Finds items not yet rated by a user.
	 * 
	 * @param neighbours The neighbours to find rated items from
	 * @param user The user
	 * @return A set of item ids not yet rated by the user
	 */
	private Set<Integer> getPredictableItems(Collection<Neighbour> neighbours, User user) {
		Set<Integer> predictableItems = new HashSet<>();
		for(Neighbour neighbour : neighbours) {
			predictableItems.addAll(neighbour.getUser().getRatings().keySet());
		}
		predictableItems.removeAll(user.getRatings().keySet());
		return predictableItems;
	}

	private void printRatings(Collection<Rating> ratings) {
		System.out.println("Predicted ratings: [item, rating]");
		for(Rating r : ratings) {
			System.out.println(r);
		}
	}

	/**
	 * Predicts ratings for items.
	 * 
	 * @param neighbours The neighbours to base prediction on
	 * @param predictableItems The items to predict a rating for
	 * @param maxRatings The maximum number of ratings to return
	 * @param minNeighbours The minimum number of users that must have rated an item
	 * @return A sorted list of predicted ratings
	 */
	private List<Rating> predictRatings(Collection<Neighbour> neighbours, Set<Integer> predictableItems, int maxRatings, int minNeighbours) {
		List<Rating> ratings = new TopList<>(maxRatings, true);
		for(int item : predictableItems) {
			if(minNeighbours < 1 || ratedByAtLeast(item, minNeighbours, neighbours)) {
				float rating = Ratings.predict(neighbours, item);
				if(!Float.isNaN(rating)) {
					ratings.add(new Rating(item, rating));
				}
			}
		}
		return ratings;
	}

	/**
	 * @param item The item to check against
	 * @param minNeighbours The minimum number of users that must have rated the item
	 * @param neighbours The neighbours to check for ratings
	 * @return True if the item has been rated by at least <i>minNeighbours</i> users
	 */
	private boolean ratedByAtLeast(int item, int minNeighbours, Collection<Neighbour> neighbours) {
		int found = 0;
		for(Neighbour n : neighbours) {
			if(n.getUser().getRating(item) > 0) {
				found++;
				if(found >= minNeighbours) {
					return true;
				}
			}
		}
		return false;
	}
}
