package hro.infdta011;

import java.util.Map;

import hro.infdta011.part1.StepB;
import hro.infdta011.part1.StepE;
import hro.infdta011.part1.StepG;

public class Part1 {
	public static void main(String[] args) {
		System.out.println("B>>>>>>>");
		Map<Integer, User> users = new StepB().run(args[0]);
		System.out.println(users);
		System.out.println("E>>>>>>>");
		new StepE().run(users);
		System.out.println("G>>>>>>>");
		new StepG().run(args[1]);
	}
}
