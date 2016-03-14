package hro.infdta011;

import hro.infdta011.part2.ItemReader;
import hro.infdta011.part2.StepB1;
import hro.infdta011.part2.StepB2;

public class Part2 {
	public static void main(String[] args) {
		System.out.println("First bullet");
		new StepB1().run(new ItemReader(",").readItems(args[0]));
		System.out.println("\nSecond bullet");
		new StepB2().run(new ItemReader("\t").readItems(args[1]));
	}
}
