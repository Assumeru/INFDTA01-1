package hro.infdta011.part2;

import hro.infdta011.Rating;
import hro.infdta011.TopList;
import hro.infdta011.User;
import hro.infdta011.calculation.SlopeOne;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StepB2 {
	public void run(ParsedFile input) {
		Set<Integer> items = new HashSet<>(input.getItems());
		User user = input.getUser(186);
		List<Rating> ratings = new TopList<>(5, true);
		for(int item : items) {
			if(!user.getRatings().containsKey(item)) {
				float rating = SlopeOne.predictRating(input, user, input.getItem(item));
				if(!Float.isNaN(rating)) {
					ratings.add(new Rating(item, rating));
				}
			}
		}
		System.out.println(ratings);
	}
}
