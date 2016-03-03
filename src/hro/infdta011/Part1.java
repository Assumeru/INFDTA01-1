package hro.infdta011;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import hro.infdta011.calculation.EuclideanDistance;
import hro.infdta011.calculation.UserCalculator;

public class Part1 {
	public static void main(String[] args) {
		Map<Integer, User> users = readFile(args[0]);
		e1(users);
	}

	private static void e1(Map<Integer, User> users) {
		List<User> u = new ArrayList<>(users.values());
		u.remove(users.get(7));
		System.out.println(Arrays.toString(calculateNeighbours(users.get(7), u, new EuclideanDistance())));
	}

	private static Neighbour[] calculateNeighbours(User user, Collection<User> users, UserCalculator calculator) {
		Neighbour[] values = new Neighbour[users.size()];
		int i = 0;
		for(User neighbour : users) {
			values[i] = new Neighbour(neighbour, calculator.calculate(user, neighbour));
			i++;
		}
		Arrays.sort(values);
		return values;
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
