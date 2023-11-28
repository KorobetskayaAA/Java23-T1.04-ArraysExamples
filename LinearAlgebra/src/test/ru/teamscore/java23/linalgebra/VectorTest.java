package ru.teamscore.java23.linalgebra;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

class VectorTest {
	private final double[] exampleValues = { 5, 2.12, 0, -1, 10.5 };
	private final long seed = 10;

	@Test
	void testOfValue() {
		final int len = 5;
		final double value = 0.5;
		var vector = Vector.ofValue(len, value);
		var vectorValues = vector.getValues();
		assertEquals(len, vectorValues.length, "Has correct length");
		for(double vectorValue : vectorValues) {
			assertEquals(value, vectorValue, "Each value is correct");
		}
	}
	
	@Test
	void testGetValues() {
		var vector = new Vector(exampleValues);
		var vectorValues = vector.getValues();
		assertNotEquals(exampleValues, vectorValues, "Returns other array object");
		assertTrue(Arrays.equals(exampleValues, vectorValues), "Returns same values");
	}

	@Test
	void testGetDimensions() {
		var vector = new Vector(exampleValues);
		var vectorDimensions = vector.getDimensions();
		assertEquals(1, vectorDimensions.length, "Has one dimension");
		assertEquals(exampleValues.length, vectorDimensions[0], "Dimension has correct value");
	}

	@Test
	void testAddSingle() {
		double addValue = 10;
		var vector = new Vector(exampleValues);
		var addResult = vector.add(addValue);
		var addResultValues = addResult.getValues();
		assertEquals(exampleValues.length, addResultValues.length, "Sum has correct length");
		for (int i = 0; i < exampleValues.length; i++) {
			assertEquals(exampleValues[i] + addValue, addResultValues[i], "Each value is correct");
		}
	}
	
	@Test
	void testAddVector() {
		double[] addVectorValues = new double[exampleValues.length];
		Random rnd = new Random(seed);
		for (int i = 0; i < exampleValues.length; i++) {
			addVectorValues[i] = rnd.nextDouble();
		}
		var vector = new Vector(exampleValues);
		var addResult = vector.add(new Vector(addVectorValues));
		var addResultValues = addResult.getValues();
		assertEquals(exampleValues.length, addResultValues.length, "Sum has correct length");
		for (int i = 0; i < exampleValues.length; i++) {
			assertEquals(exampleValues[i] + addVectorValues[i], addResultValues[i], "Each value is correct");
		}
	}
}
