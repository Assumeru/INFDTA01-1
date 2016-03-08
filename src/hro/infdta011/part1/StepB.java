package hro.infdta011.part1;

import hro.infdta011.User;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
		Map<Integer, User> out = new HashMap<>();
		try(Scanner in = new Scanner(new BufferedInputStream(new FileInputStream(file)), "utf-8")) {
			while(in.hasNext()) {
				String[] line = in.nextLine().split(seperator);
				if(line.length > 2) {
					int id = Integer.parseInt(line[0]);
					User user = out.get(id);
					if(user == null) {
						user = new User(id);
						out.put(id, user);
					}
					user.getRatings().put(Integer.parseInt(line[1]), Float.parseFloat(line[2]));
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return Collections.unmodifiableMap(out);
	}
}
