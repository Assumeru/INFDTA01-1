package hro.infdta011.calculation;

import hro.infdta011.Item;
import hro.infdta011.ItemDeviation;
import hro.infdta011.User;
import hro.infdta011.part2.ParsedFile;

import java.util.Map.Entry;

public class SlopeOne {
	private SlopeOne() {
	}

	public static ItemDeviation calculateDeviation(Item lhs, Item rhs) {
		ItemDeviation deviation = new ItemDeviation(lhs, rhs);
		for(Entry<Integer, Float> entry : lhs.getRatings().entrySet()) {
			if(rhs.getRatings().containsKey(entry.getKey())) {
				deviation.addDeviation(lhs, entry.getValue() - rhs.getRatings().get(entry.getKey()));
			}
		}
		return deviation;
	}

	public static float predictRating(ParsedFile file, User user, Item item) {
		double numerator = 0;
		double denominator = 0;
		for(Entry<Integer, Float> entry : user.getRatings().entrySet()) {
			ItemDeviation dev = file.getItem(entry.getKey()).getDeviation(item);
			numerator += (entry.getValue() + dev.getDeviation(item)) * dev.getUsers();
			denominator += dev.getUsers();
		}
		return (float) (numerator / denominator);
	}
}
