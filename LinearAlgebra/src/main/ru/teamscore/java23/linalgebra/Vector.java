package ru.teamscore.java23.linalgebra;

import java.util.Arrays;

public class Vector {
	private final double[] values;
	public double[] getValues() {
		return values.clone();
	}
	
	public int[] getDimensions() {
		return new int[] { values.length };
	}
	
	public Vector(double[] values) {
		this.values = values;
	}

	public static Vector ofValue(int n, double value) {
		var values = new double[n];
	    Arrays.fill(values, value);
		return new Vector(values);
	}

	public static Vector ofZero(int n) {
		return ofValue(n, 0);
	}

	public static Vector ofOne(int n) {
		return ofValue(n, 1);
	}

	public Vector add(double value) {
		var resultValues = getValues();
		for (int i = 0; i < resultValues.length; i++) {
			resultValues[i] += value;
		}
		return new Vector(resultValues);
	}
	
	public Vector add(Vector other) {
		if (!Arrays.equals(getDimensions(), other.getDimensions())) {
			return null; // TODO throw exception
		}
		var resultValues = getValues();
		var otherValues = other.getValues();
		for (int i = 0; i < resultValues.length; i++) {
			resultValues[i] += otherValues[i];
		}
		return new Vector(resultValues);
	}
}
