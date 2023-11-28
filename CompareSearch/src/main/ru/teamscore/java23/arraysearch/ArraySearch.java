package ru.teamscore.java23.arraysearch;

import java.util.Arrays;

public class ArraySearch {
	private final Item[] items;
	private final Item[] sortedItems;
	
	public Item getItem(int index) {
		if (index < 0 || index >= items.length) {
			return null; // TODO throw
		}
		return items[index];
	}

	public Item getSortedItem(int index) {
		if (index < 0 || index >= sortedItems.length) {
			return null; // TODO throw
		}
		return sortedItems[index];
	}
	
	ArraySearch(Item[] items) {
		this.items = items;
		sortedItems = items.clone();
		Arrays.sort(sortedItems);
	}
	
	int searchValueBrute(int value) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].getValue() == value) {
				return i;
			}
		}
		return -1;
	}
	
	int searchValueBinary(int value) {
		return Arrays.binarySearch(sortedItems, new Item(value));
	}
}
