package hro.infdta011;

import hro.infdta011.calculation.UserCalculator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
	private Map<Integer, Float> ratings;
	private int id;

	public User(int id) {
		this.id = id;
		ratings = new HashMap<>();
	}

	public int getId() {
		return id;
	}

	public Map<Integer, Float> getRatings() {
		return ratings;
	}

	/**
	 * @param id The item to get the rating for
	 * @return The item's rating or 0 if this user hasn't rated the item
	 */
	public float getRating(int id) {
		if(ratings.containsKey(id)) {
			return ratings.get(id);
		}
		return 0;
	}

	/**
	 * @param users The collection of users to find neighbours in
	 * @param calculator The calculator to use to calculate the distance between users
	 * @param numNeighbours The maximum number of neighbours to return
	 * @return A sorted list of neighbours
	 */
	public List<Neighbour> calculateNeighbours(Collection<User> users, UserCalculator calculator, int numNeighbours) {
		List<Neighbour> values = new TopList<>(numNeighbours, !calculator.lowestIsNearest());
		for(User neighbour : users) {
			double distance = calculator.calculate(this, neighbour);
			if(!Double.isNaN(distance)) {
				values.add(new Neighbour(neighbour, distance));
			}
		}
		return values;
	}

	@Override
	public String toString() {
		return "<User " + id + " | ratings: " + ratings.size() + ">";
	}
}
