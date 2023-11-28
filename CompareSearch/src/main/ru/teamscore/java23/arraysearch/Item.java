package ru.teamscore.java23.arraysearch;

import java.util.UUID;

public class Item implements Comparable<Item> {
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public UUID getId() {
		return id;
	}

	private final UUID id;
	private int value;

	Item(UUID id, int value) {
		this.id = id;
		this.value = value;
	}

	Item(int value) {
		this(UUID.randomUUID(), value);
	}

	@Override
	public int compareTo(Item o) {
		return this.getValue() < o.getValue() ? -1 : this.getValue() > o.getValue() ? 1 : 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Item)) {
			return false;
		}
		return this.getValue() == ((Item)o).getValue();
	}
	
	@Override
	public int hashCode() {
		return getValue();
	}
	
	@Override
	public String toString() {
		return id.toString() + ": " + value;
	}
	
}
