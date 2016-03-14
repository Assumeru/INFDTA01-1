package hro.infdta011;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputParser {
	public static void parse(String file, String seperator, LineReader reader) {
		try(Scanner in = new Scanner(new BufferedInputStream(new FileInputStream(file)), "utf-8")) {
			while(in.hasNext()) {
				String[] line = in.nextLine().split(seperator);
				reader.read(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Float.parseFloat(line[2]));
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static interface LineReader {
		public void read(int user, int item, float rating);
	}
}
