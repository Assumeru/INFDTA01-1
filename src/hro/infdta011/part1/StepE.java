package hro.infdta011.part1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hro.infdta011.Neighbour;
import hro.infdta011.Step;
import hro.infdta011.TopSet;
import hro.infdta011.User;
import hro.infdta011.calculation.ApproximatePearsonCoefficient;
import hro.infdta011.calculation.CosineSimilarity;
import hro.infdta011.calculation.EuclideanDistance;
import hro.infdta011.calculation.Ratings;
import hro.infdta011.calculation.UserCalculator;

public class StepE implements Step<Map<Integer, User>, Void> {
	private static final UserCalculator PEARSON = new ApproximatePearsonCoefficient();
	private static final UserCalculator EUCLIDIAN = new EuclideanDistance();
	private static final UserCalculator COSINE = new CosineSimilarity();

	@Override
	public Void run(Map<Integer, User> input) {
		System.out.println("1]");
		Set<Neighbour> neighbours = b1(input);
		System.out.println("2]");
		b2(input);
		System.out.println("3]");
		b3(neighbours);
		System.out.println("4]");
		b4(input);
		System.out.println("5]");
		b5(input);
		System.out.println("6]");
		b6(input);
		return null;
	}

	private Set<Neighbour> calculateNeighbours(User user, Collection<User> users, UserCalculator calculator, int numNeighbours) {
		Set<Neighbour> values = new TopSet<>(numNeighbours, !calculator.lowestIsNearest());
		for(User neighbour : users) {
			values.add(new Neighbour(neighbour, calculator.calculate(user, neighbour)));
		}
		return values;
	}

	private Set<Neighbour> b1(Map<Integer, User> input) {
		System.out.println("Nearest neighbours for '7' using Pearson:");
		Set<Neighbour> neighbours = b1(input, PEARSON);
		System.out.println("Nearest neighbours for '7' using Euclidian:");
		b1(input, EUCLIDIAN);
		System.out.println("Nearest neighbours for '7' using Cosine:");
		b1(input, COSINE);
		return neighbours;
	}

	private Set<Neighbour> b1(Map<Integer, User> input, UserCalculator calculator) {
		User u7 = input.get(7);
		List<User> users = new ArrayList<>(input.values());
		users.remove(u7);
		Set<Neighbour> neighbours = calculateNeighbours(u7, users, calculator, 3);
		for(Neighbour neighbour : neighbours) {
			System.out.println(" user " + neighbour.getUser().getId() + ": " + neighbour.getDistance());
		}
		return neighbours;
	}

	private void b2(Map<Integer, User> input) {
		System.out.println("Pearson similarity between '3' and '4':");
		System.out.println(" " + PEARSON.calculate(input.get(3), input.get(4)));
	}

	private void b3(Set<Neighbour> neighbours) {
		predictRating(neighbours, 101);
		predictRating(neighbours, 103);
		predictRating(neighbours, 106);
	}

	private void predictRating(Set<Neighbour> neighbours, int item) {
		float rating = Ratings.predict(neighbours, item);
		System.out.println("Prediction for item '" + item + "':");
		System.out.println(" " + rating);
	}

	private void b4(Map<Integer, User> input) {
		List<User> users = new ArrayList<>(input.values());
		users.remove(input.get(4));
		predictRating(calculateNeighbours(input.get(4), users, PEARSON, 3), 101);
	}

	private void b5(Map<Integer, User> input) {
		b5(input, 2.8f);
	}

	private void b5(Map<Integer, User> input, float rating) {
		User u7 = input.get(7);
		u7.getRatings().put(106, rating);
		List<User> users = new ArrayList<>(input.values());
		users.remove(u7);
		Set<Neighbour> neighbours = calculateNeighbours(u7, users, PEARSON, 3);
		predictRating(neighbours, 101);
		predictRating(neighbours, 103);
	}

	private void b6(Map<Integer, User> input) {
		b5(input, 5f);
	}
}
