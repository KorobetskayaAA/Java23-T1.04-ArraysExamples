package ru.teamscore.java23.linalgebra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

class MatrixTest {
	private final long seed = 10;
	private final Random rnd = new Random(seed);
	private final int n = 10;
	private final int m = 10;
	
	private final static double[][] randomValues(Random rnd, int n, int m) {
		double[][] values = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				values[i][j] = rnd.nextDouble(-100, 100);
			}
		}
		return values;
	}
	
	private final double[][] exampleValues;
	{
		exampleValues = randomValues(rnd, n, m);
	}

	private void assertDimensions(int first, int second, Matrix matrix) {
		var matrixDimensions = matrix.getDimensions();
		assertEquals(first, matrixDimensions[0], "Has correct first dimension");
		assertEquals(second, matrixDimensions[1], "Has correct second dimension");
	}
	
	private void assertValues(double[][] expected, double[][] actual) {
		for (int i = 0; i < n; i++) {
			assertTrue(Arrays.equals(expected[i], actual[i]), "Returns same values");
		}
	}

	@Test
	void testGetValues() {
		var matrix = new Matrix(exampleValues);
		var matrixValues = matrix.getValues();
		assertNotEquals(exampleValues, matrixValues, "Returns other matrix object");
		assertValues(exampleValues, matrixValues);
	}

	@Test
	void testGetDimensions() {
		var matrix = new Matrix(exampleValues);
		var matrixDimensions = matrix.getDimensions();
		assertEquals(2, matrixDimensions.length, "Has two dimensions");
		assertEquals(n, matrixDimensions[0], "Dimension has correct first");
		assertEquals(m, matrixDimensions[1], "Dimension has correct second");
	}

	@Test
	void testOfValue() {
		final double value = rnd.nextDouble(1, 100);
		var matrix = Matrix.ofValue(n, m, value);
		assertDimensions(n, m, matrix);
		var matrixValues = matrix.getValues();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				assertEquals(value, matrixValues[i][j], "Returns same value");
			}
		}
	}

	@Test
	void testDiagonal() {
		final double value = rnd.nextDouble(1, 100);
		var matrix = Matrix.diagonal(n, value);
		assertDimensions(n, n, matrix);
		var matrixValues = matrix.getValues();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == j) {
					assertEquals(value, matrixValues[i][j], "Diagonal has same value");
				} else {
					assertEquals(0, matrixValues[i][j], "Non-diagonal is zero");
				}
			}
		}
	}


	@Test
	void testAddDouble() {
		final double value = rnd.nextDouble(1, 100);
		var matrix = new Matrix(exampleValues);
		var addMatrix = matrix.add(value);
		var addMatrixValues = addMatrix.getValues();
		assertNotEquals(exampleValues, addMatrixValues, "Returns new matrix object");
		assertDimensions(n, m, matrix);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				assertEquals(exampleValues[i][j] + value, addMatrixValues[i][j], "Each element is correct sum");
			}
		}
	}

	@Test
	void testAddMatrix() {
		final double[][] valuesToAdd = randomValues(rnd, n, m);
		var matrix = new Matrix(exampleValues);
		var addMatrix = matrix.add(new Matrix(valuesToAdd));
		var addMatrixValues = addMatrix.getValues();
		assertNotEquals(exampleValues, addMatrixValues, "Returns new matrix object");
		assertDimensions(n, m, matrix);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				assertEquals(exampleValues[i][j] + valuesToAdd[i][j], addMatrixValues[i][j], "Each element is correct sum");
			}
		}
	}

	@Test
	void testAddMatrixWrongDimensions() {
		var matrix = new Matrix(exampleValues);
		assertNull(matrix.add(new Matrix(new double[n + 1][m + 1])), "Wrong first dimension");
		assertNull(matrix.add(new Matrix(new double[n][m + 1])), "Wrong second dimension");
		assertNull(matrix.add(new Matrix(new double[n - 1][m - 1])), "Wrong both dimensions");
	}

	@Test
	void testMutiply() {
		var vectorValues = new double[] { 5, 9, 0 };
		var matrixValues = new double[][] {
			{1, 1, 1},
			{2, -5, 3},
			{0, 1, 0},
			{0, 0, 0}
		};
		var multiplyValues = new double[] {
			14,
			-35,
			9,
			0
		};
		var vector = new Vector(vectorValues);
		var matrix = new Matrix(matrixValues);
		var resultVector = matrix.multiply(vector);
		var resultVectorValues = resultVector.getValues();
		assertEquals(multiplyValues.length, resultVectorValues.length, "Result vector has correct length");
		assertTrue(Arrays.equals(multiplyValues, resultVectorValues), "Returns same values");
	}
}
