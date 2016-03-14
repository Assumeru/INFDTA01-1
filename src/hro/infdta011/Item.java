package hro.infdta011;

import hro.infdta011.part2.ParsedFile;

import java.util.HashMap;
import java.util.Map;

public class Item {
	private Map<Integer, Float> ratings;
	private int id;
	private ParsedFile parent;

	public Item(int id, ParsedFile parent) {
		this.id = id;
		ratings = new HashMap<>();
		this.parent = parent;
	}

	public int getId() {
		return id;
	}

	public Map<Integer, Float> getRatings() {
		return ratings;
	}

	public ItemDeviation getDeviation(Item other) {
		return parent.getDeviations().get(id).get(other.getId());
	}
}
