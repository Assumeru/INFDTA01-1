package hro.infdta011.part1;

import hro.infdta011.InputParser;
import hro.infdta011.InputParser.LineReader;
import hro.infdta011.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StepB {
	private String seperator;

	public StepB() {
		this(",");
	}

	/**
	 * @param seperator The line seperator to use
	 */
	StepB(String seperator) {
		this.seperator = seperator;
	}

	/**
	 * Reads user ratings from a file
	 * 
	 * @param file The file to read from
	 * @return A map of users by user id
	 */
	public Map<Integer, User> run(String file) {
		final Map<Integer, User> out = new HashMap<>();
		InputParser.parse(file, seperator, new LineReader() {
			@Override
			public void read(int id, int item, float rating) {
				User user = out.get(id);
				if(user == null) {
					user = new User(id);
					out.put(id, user);
				}
				user.getRatings().put(item, rating);
			}
		});
		return Collections.unmodifiableMap(out);
	}
}
