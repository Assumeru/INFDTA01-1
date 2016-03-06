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
		Set<Rating> ratings = predictRatings(neighbours, predictableItems, 8);
		System.out.println("Predicted ratings: [item, rating]");
		for(Rating r : ratings) {
			System.out.println(r);
		}
		return null;
	}

	private Set<Rating> predictRatings(Set<Neighbour> neighbours, Set<Integer> predictableItems, int maxRatings) {
		Set<Rating> ratings = new TopSet<>(maxRatings, true);
		for(int item : predictableItems) {
			float rating = Ratings.predict(neighbours, item);
			if(!Float.isNaN(rating)) {
				ratings.add(new Rating(item, rating));
			}
		}
		return ratings;
	}

	private Set<Neighbour> getNeighbours(Map<Integer, User> users, Set<Integer> predictableItems) {
		User user = users.get(186);
		Set<Neighbour> values = new TopSet<>(50, !PEARSON.lowestIsNearest());
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
