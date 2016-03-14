package hro.infdta011.part2;

import hro.infdta011.InputParser;
import hro.infdta011.InputParser.LineReader;

public class ItemReader {
	private String seperator;

	public ItemReader(String seperator) {
		this.seperator = seperator;
	}

	public ParsedFile readItems(String file) {
		final ParsedFile ratings = new ParsedFile();
		InputParser.parse(file, seperator, new LineReader() {
			@Override
			public void read(int userId, int itemId, float rating) {
				ratings.setRating(userId, itemId, rating);
			}
		});
		return ratings;
	}
}
