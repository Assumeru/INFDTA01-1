package hro.infdta011.part2;

import hro.infdta011.Item;
import hro.infdta011.ItemDeviation;
import hro.infdta011.User;
import hro.infdta011.calculation.SlopeOne;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParsedFile {
	private Map<Integer, Item> items;
	private Map<Integer, User> users;
	private Map<Integer, Map<Integer, ItemDeviation>> itemDeviations;

	public ParsedFile() {
		items = new HashMap<>();
		users = new HashMap<>();
	}

	public void setRating(int userId, int itemId, float rating) {
		Item item = items.get(itemId);
		if(item == null) {
			item = new Item(itemId, this);
			items.put(itemId, item);
		}
		User user = users.get(userId);
		if(user == null) {
			user = new User(userId);
			users.put(userId, user);
		}
		item.getRatings().put(userId, rating);
		user.getRatings().put(itemId, rating);
		itemDeviations = null;
	}

	public User getUser(int id) {
		return users.get(id);
	}

	public Item getItem(int id) {
		return items.get(id);
	}

	private void put(int lhs, int rhs, ItemDeviation dev) {
		Map<Integer, ItemDeviation> map = itemDeviations.get(lhs);
		if(map == null) {
			map = new HashMap<>();
			itemDeviations.put(lhs, map);
		}
		map.put(rhs, dev);
	}

	public Map<Integer, Map<Integer, ItemDeviation>> getDeviations() {
		if(itemDeviations == null) {
			calculateDeviations();
		}
		return itemDeviations;
	}

	private void calculateDeviations() {
		itemDeviations = new HashMap<>();
		for(Item lhs : items.values()) {
			for(Item rhs : items.values()) {
				if(lhs != rhs) {
					ItemDeviation dev = SlopeOne.calculateDeviation(lhs, rhs);
					//these maps are many times faster than searching a HashSet
					put(lhs.getId(), rhs.getId(), dev);
					put(rhs.getId(), lhs.getId(), dev);
				}
			}
		}
	}

	public Collection<Integer> getItems() {
		return items.keySet();
	}
}
