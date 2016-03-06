package hro.infdta011.part1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import hro.infdta011.Step;
import hro.infdta011.User;

public class StepB implements Step<String, Map<Integer, User>> {
	private String seperator;

	public StepB() {
		this(",");
	}

	StepB(String seperator) {
		this.seperator = seperator;
	}

	@Override
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
