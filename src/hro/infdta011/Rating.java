package hro.infdta011;

public class Rating implements Comparable<Rating> {
	private int id;
	private float rating;

	/**
	 * @param id The item's id
	 * @param rating The item's rating
	 */
	public Rating(int id, float rating) {
		this.id = id;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public float getRating() {
		return rating;
	}

	@Override
	public String toString() {
		return id + ": " + rating;
	}

	@Override
	public int compareTo(Rating o) {
		return Float.compare(rating, o.rating);
	}
}
