package hro.infdta011;

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

	@Override
	public String toString() {
		return "<User " + id + " | ratings: " + ratings.size() + ">";
	}
}
