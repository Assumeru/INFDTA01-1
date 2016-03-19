package hro.infdta011.part2;

import java.util.List;

import hro.infdta011.Rating;
import hro.infdta011.TopList;
import hro.infdta011.User;

public class StepB2 {
	public void run(ParsedFile input) {
		User user = input.getUser(186);
		long time = System.nanoTime();
		List<Rating> ratings = predictRatings(input, user);
		time = System.nanoTime() - time;
		System.out.println(ratings);
		System.out.println("Calculation time: " + (time / 1000000) + "ms");
	}

	private List<Rating> predictRatings(ParsedFile input, User user) {
		List<Rating> ratings = new TopList<>(5, true);
		for(int item : input.getItems()) {
			if(!user.getRatings().containsKey(item)) {
				float rating = input.predictRating(user, input.getItem(item));
				if(!Float.isNaN(rating)) {
					ratings.add(new Rating(item, rating));
				}
			}
		}
		return ratings;
	}
}
