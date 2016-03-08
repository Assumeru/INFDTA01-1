package hro.infdta011.part1;

import hro.infdta011.Neighbour;
import hro.infdta011.Rating;
import hro.infdta011.Step;
import hro.infdta011.TopList;
import hro.infdta011.User;
import hro.infdta011.calculation.ApproximatePearsonCoefficient;
import hro.infdta011.calculation.Ratings;
import hro.infdta011.calculation.UserCalculator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StepG implements Step<String, Void> {
	private static final UserCalculator PEARSON = new ApproximatePearsonCoefficient();

	@Override
	public Void run(String input) {
		Map<Integer, User> users = new StepB("\t").run(input);
		User user = users.get(186);
		Collection<Neighbour> neighbours = user.calculateNeighbours(users.values(), PEARSON, 25);
		Set<Integer> predictableItems = getPredictableItems(neighbours, user);
		printRatings(predictRatings(neighbours, predictableItems, 8, null));
		printRatings(predictRatings(neighbours, predictableItems, 8, 3));
		return null;
	}

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

	private Collection<Rating> predictRatings(Collection<Neighbour> neighbours, Set<Integer> predictableItems, int maxRatings, Integer minNeighbours) {
		Collection<Rating> ratings = new TopList<>(maxRatings, true);
		for(int item : predictableItems) {
			if(minNeighbours == null || ratedByAtLeast(item, minNeighbours, neighbours)) {
				float rating = Ratings.predict(neighbours, item);
				if(!Float.isNaN(rating)) {
					ratings.add(new Rating(item, rating));
				}
			}
		}
		return ratings;
	}

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
