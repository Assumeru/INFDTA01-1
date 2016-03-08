package hro.infdta011.part1;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import hro.infdta011.Neighbour;
import hro.infdta011.Rating;
import hro.infdta011.Step;
import hro.infdta011.TopSet;
import hro.infdta011.User;
import hro.infdta011.calculation.ApproximatePearsonCoefficient;
import hro.infdta011.calculation.Ratings;
import hro.infdta011.calculation.UserCalculator;

public class StepG implements Step<String, Void> {
	private static final UserCalculator PEARSON = new ApproximatePearsonCoefficient();

	@Override
	public Void run(String input) {
		Map<Integer, User> users = new StepB("\t").run(input);
		Set<Integer> predictableItems = new HashSet<>();
		Set<Neighbour> neighbours = getNeighbours(users, predictableItems);
		printRatings(predictRatings(neighbours, predictableItems, 8, null));
		printRatings(predictRatings(neighbours, predictableItems, 8, 3));
		return null;
	}

	private void printRatings(Set<Rating> ratings) {
		System.out.println("Predicted ratings: [item, rating]");
		for(Rating r : ratings) {
			System.out.println(r);
		}
	}

	private Set<Rating> predictRatings(Set<Neighbour> neighbours, Set<Integer> predictableItems, int maxRatings, Integer minNeighbours) {
		Set<Rating> ratings = new TopSet<>(maxRatings, true);
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

	private boolean ratedByAtLeast(int item, int minNeighbours, Set<Neighbour> neighbours) {
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

	private Set<Neighbour> getNeighbours(Map<Integer, User> users, Set<Integer> predictableItems) {
		User user = users.get(186);
		Set<Neighbour> values = new TopSet<>(25, !PEARSON.lowestIsNearest());
		for(User neighbour : users.values()) {
			if(neighbour != user) {
				values.add(new Neighbour(neighbour, PEARSON.calculate(user, neighbour)));
				predictableItems.addAll(neighbour.getRatings().keySet());
			}
		}
		predictableItems.removeAll(user.getRatings().keySet());
		return values;
	}
}
