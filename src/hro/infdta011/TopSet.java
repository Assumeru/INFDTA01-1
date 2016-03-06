package hro.infdta011;

import java.util.Iterator;
import java.util.TreeSet;

public class TopSet<E extends Comparable<E>> extends TreeSet<E> {
	private static final long serialVersionUID = 3073699649894888870L;
	private int maxValues;
	private boolean reverse;

	/**
	 * @param maxValues Max. number of values to keep
	 * @param reverse True if the highest values should be kept
	 */
	public TopSet(int maxValues, boolean reverse) {
		this.maxValues = maxValues;
		this.reverse = reverse;
	}

	@Override
	public boolean add(E e) {
		if(size() < maxValues) {
			return super.add(e);
		} else if(reverse) {
			E first = first();
			if(e.compareTo(first) > 0) {
				remove(first);
				return super.add(e);
			}
		} else {
			E last = last();
			if(e.compareTo(last) < 0) {
				remove(last);
				return super.add(e);
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		if(reverse) {
			return super.descendingIterator();
		}
		return super.iterator();
	}

	@Override
	public Iterator<E> descendingIterator() {
		if(reverse) {
			return super.iterator();
		}
		return super.descendingIterator();
	}
}
