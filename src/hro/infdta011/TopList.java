package hro.infdta011;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A fixed-size list that keeps track of the top or bottom <i>n</i> values.
 */
public class TopList<E extends Comparable<E>> extends AbstractList<E> {
	private int size;
	private boolean reverse;
	private Object[] values;

	/**
	 * @param maxValues Max. number of values to keep
	 * @param reverse True if the highest values should be kept
	 */
	public TopList(int maxValues, boolean reverse) {
		this.reverse = reverse;
		values = new Object[maxValues];
	}

	@Override
	public Iterator<E> iterator() {
		if(reverse) {
			return new TopListDescendingIterator();
		}
		return new TopListIterator();
	}

	public Iterator<E> descendingIterator() {
		if(reverse) {
			return new TopListIterator();
		}
		return new TopListDescendingIterator();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(E e) {
		if(e != null) {
			if(size < values.length) {
				set(size++, e);
				return true;
			} else if(reverse) {
				E first = get(0);
				if(e.compareTo(first) > 0) {
					set(0, e);
					return true;
				}
			} else {
				E last = get(size - 1);
				if(e.compareTo(last) < 0) {
					set(size - 1, e);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E get(int index) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		return (E) values[index];
	}

	@Override
	public E set(int index, E element) {
		E prev = get(index);
		values[index] = element;
		if(element == null) {
			size--;
			if(index < size) {
				System.arraycopy(values, index + 1, values, index, values.length - index);
			}
		} else {
			Arrays.sort(values, 0, size);
		}
		return prev;
	}

	@Override
	public int indexOf(Object o) {
		for(int i = 0; i < size; i++) {
			if(values[i] == o || (o != null && o.equals(values[i]))) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if(index != -1) {
			set(index, null);
		}
		return false;
	}

	private class TopListIterator implements Iterator<E> {
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public E next() {
			if(index >= size) {
				throw new NoSuchElementException();
			}
			return get(index++);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class TopListDescendingIterator implements Iterator<E> {
		private int index = size;

		@Override
		public boolean hasNext() {
			return index > 0;
		}

		@Override
		public E next() {
			if(index < 1) {
				throw new NoSuchElementException();
			}
			return get(--index);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
