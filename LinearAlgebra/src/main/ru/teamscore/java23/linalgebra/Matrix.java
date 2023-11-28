package ru.teamscore.java23.linalgebra;

import java.util.Arrays;

public class Matrix {
	private final double[][] values;
	public double[][] getValues() {
		double[][] valuesCopy = new double[values.length][];
		for (int i = 0; i < values.length; i++) {
			valuesCopy[i] = values[i].clone();
		}
		return valuesCopy;
	}
	
	public int[] getDimensions() {
		return new int[] { values.length, values[0].length };
	}

	public Matrix(double[][] values) {
		this.values = values;
	}

	public static Matrix ofValue(int n, int m, double value) {
		var values = new double[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(values[i], value);
		}
		return new Matrix(values);
	}

	public static Matrix ofZero(int n, int m) {
		return ofValue(n, m, 0);
	}

	public static Matrix ofOne(int n, int m) {
		return ofValue(n, m, 1);
	}

	public static Matrix diagonal(int n, double value) {
		var values = new double[n][n];
		for (int i = 0; i < n; i++) {
			values[i][i] = value;
		}
		return new Matrix(values);
	}

	public static Matrix identity(int n) {
		return diagonal(n, 1);
	}

	public Matrix add(double value) {
		var resultValues = getValues();
		for (int i = 0; i < resultValues.length; i++) {
			for (int j = 0; j < resultValues[i].length; j++) {
				resultValues[i][j] += value;
			}
		}
		return new Matrix(resultValues);
	}
	
	public Matrix add(Matrix other) {
		if (!Arrays.equals(getDimensions(), other.getDimensions())) {
			return null; // TODO throw exception
		}
		var resultValues = getValues();
		var otherValues = other.getValues();
		for (int i = 0; i < resultValues.length; i++) {
			for (int j = 0; j < resultValues[i].length; j++) {
				resultValues[i][j] += otherValues[i][j];
			}
		}
		return new Matrix(resultValues);
	}
	
	public Vector multiply(Vector vector) {
		var dims = getDimensions();
		if (dims[1] != vector.getDimensions()[0]) {
			return null; // TODO throw exception
		}
		var matrixValues = this.getValues();
		var vectorValues = vector.getValues();
		var resultValues = new double[dims[0]];
		for (int i = 0; i < dims[0]; i++) {
			for (int j = 0; j < dims[1]; j++) {
				resultValues[i] += vectorValues[j] * matrixValues[i][j];
			}
		}
		return new Vector(resultValues);
	}
}
