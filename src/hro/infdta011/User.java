package hro.infdta011;

import hro.infdta011.calculation.UserCalculator;

import java.util.Collection;
import java.util.HashMap;
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

	public float getRating(int id) {
		if(ratings.containsKey(id)) {
			return ratings.get(id);
		}
		return 0;
	}

	public Collection<Neighbour> calculateNeighbours(Collection<User> users, UserCalculator calculator, int numNeighbours) {
		Collection<Neighbour> values = new TopList<>(numNeighbours, !calculator.lowestIsNearest());
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
