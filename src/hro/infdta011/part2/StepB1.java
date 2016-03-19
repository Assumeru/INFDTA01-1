package hro.infdta011.part2;

import hro.infdta011.User;

public class StepB1 {
	public void run(ParsedFile file) {
		System.out.println("i]");
		i(file);
		System.out.println("ii]");
		ii(file);
		System.out.println("iii]");
		iii(file);
	}

	private void i(ParsedFile file) {
		User user = file.getUser(7);
		predictRating(file, user, 101);
		predictRating(file, user, 103);
		predictRating(file, user, 106);
	}

	private void predictRating(ParsedFile file, User user, int item) {
		System.out.println("Prediction for item " + item + " and user " + user.getId());
		System.out.println(" " + file.predictRating(user, file.getItem(item)));
	}

	private void ii(ParsedFile file) {
		User user = file.getUser(3);
		predictRating(file, user, 103);
		predictRating(file, user, 105);
	}

	private void iii(ParsedFile file) {
		file.setRating(3, 105, 4);
		i(file);
	}
}
