package hro.infdta011.part1;

import hro.infdta011.Neighbour;
import hro.infdta011.User;
import hro.infdta011.calculation.ApproximatePearsonCoefficient;
import hro.infdta011.calculation.CosineSimilarity;
import hro.infdta011.calculation.EuclideanDistance;
import hro.infdta011.calculation.Ratings;
import hro.infdta011.calculation.UserCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class StepE {
	private static final UserCalculator PEARSON = new ApproximatePearsonCoefficient();
	private static final UserCalculator EUCLIDIAN = new EuclideanDistance();
	private static final UserCalculator COSINE = new CosineSimilarity();

	/**
	 * Executes every bullet point.
	 * 
	 * @param input The loaded ratings
	 */
	public void run(Map<Integer, User> input) {
		System.out.println("1]");
		Collection<Neighbour> neighbours = b1(input);
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
	}

	private Collection<Neighbour> b1(Map<Integer, User> input) {
		System.out.println("Nearest neighbours for '7' using Pearson:");
		Collection<Neighbour> neighbours = printNeighbours(input, PEARSON);
		System.out.println("Nearest neighbours for '7' using Euclidian:");
		printNeighbours(input, EUCLIDIAN);
		System.out.println("Nearest neighbours for '7' using Cosine:");
		printNeighbours(input, COSINE);
		return neighbours;
	}

	/**
	 * Calculates and prints the 3 nearest neighbours for user 7.
	 * 
	 * @param input The list of users to consider
	 * @param calculator The calculator to use
	 * @return A sorted list of neighbours
	 */
	private List<Neighbour> printNeighbours(Map<Integer, User> input, UserCalculator calculator) {
		User u7 = input.get(7);
		List<User> users = new ArrayList<>(input.values());
		users.remove(u7);
		List<Neighbour> neighbours = u7.calculateNeighbours(users, calculator, 3);
		for(Neighbour neighbour : neighbours) {
			System.out.println(" user " + neighbour.getUser().getId() + ": " + neighbour.getDistance());
		}
		return neighbours;
	}

	private void b2(Map<Integer, User> input) {
		System.out.println("Pearson similarity between '3' and '4':");
		System.out.println(" " + PEARSON.calculate(input.get(3), input.get(4)));
	}

	private void b3(Collection<Neighbour> neighbours) {
		printPrediction(neighbours, 101);
		printPrediction(neighbours, 103);
		printPrediction(neighbours, 106);
	}

	private void printPrediction(Collection<Neighbour> neighbours, int item) {
		System.out.println("Prediction for item '" + item + "':\n " + Ratings.predict(neighbours, item));
	}

	private void b4(Map<Integer, User> input) {
		List<User> users = new ArrayList<>(input.values());
		users.remove(input.get(4));
		printPrediction(input.get(4).calculateNeighbours(users, PEARSON, 3), 101);
	}

	private void b5(Map<Integer, User> input) {
		changeRating(input, 2.8f);
	}

	/**
	 * Changes user 7's rating of item 106 and recalculates its neighbours.
	 * 
	 * @param input All users
	 * @param rating The rating to change to
	 */
	private void changeRating(Map<Integer, User> input, float rating) {
		User u7 = input.get(7);
		u7.getRatings().put(106, rating);
		List<User> users = new ArrayList<>(input.values());
		users.remove(u7);
		Collection<Neighbour> neighbours = u7.calculateNeighbours(users, PEARSON, 3);
		printPrediction(neighbours, 101);
		printPrediction(neighbours, 103);
	}

	private void b6(Map<Integer, User> input) {
		changeRating(input, 5f);
	}
}
