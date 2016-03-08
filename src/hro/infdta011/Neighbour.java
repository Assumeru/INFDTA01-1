package hro.infdta011;

public class Neighbour implements Comparable<Neighbour> {
	private User user;
	private double distance;

	/**
	 * @param user The neighbour
	 * @param distance The distance to the neighbour
	 */
	public Neighbour(User user, double distance) {
		this.user = user;
		this.distance = distance;
	}

	public User getUser() {
		return user;
	}

	public double getDistance() {
		return distance;
	}

	@Override
	public String toString() {
		return user.toString() + " " + distance;
	}

	@Override
	public int compareTo(Neighbour o) {
		return Double.compare(distance, o.distance);
	}
}
