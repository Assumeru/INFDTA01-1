package hro.infdta011;

public class ItemDeviation {
	private Item lhs;
	private Item rhs;
	private double sum;
	private int users;

	public ItemDeviation(Item lhs, Item rhs) {
		if(lhs == null || rhs == null) {
			throw new NullPointerException();
		}
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public void addDeviation(Item direction, double deviation) {
		if(direction == lhs) {
			sum += deviation;
		} else if(direction == rhs) {
			sum -= deviation;
		} else {
			throw new IllegalArgumentException();
		}
		users++;
	}

	public double getDeviation(Item direction) {
		if(direction == lhs) {
			return sum / users;
		} else if(direction == rhs) {
			return -sum / users;
		}
		throw new IllegalArgumentException();
	}

	public int getUsers() {
		return users;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		} else if(obj instanceof ItemDeviation) {
			ItemDeviation dev = (ItemDeviation) obj;
			return (lhs == dev.lhs && rhs == dev.rhs) || (lhs == dev.rhs && rhs == dev.lhs);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return lhs.hashCode() + rhs.hashCode();
	}
}
