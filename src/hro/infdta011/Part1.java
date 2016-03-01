package hro.infdta011;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) {
		Map<Integer, User> users = readFile(args[0]);
		System.out.println(users);
	}

	private static Map<Integer, User> readFile(String file) {
		Map<Integer, User> out = new HashMap<>();
		try(Scanner in = new Scanner(new BufferedInputStream(new FileInputStream(file)), "utf-8")) {
			while(in.hasNext()) {
				String[] line = in.nextLine().split(",");
				if(line.length == 3) {
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
		return out;
	}
}
