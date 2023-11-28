import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ArraysTest {


	@Test
	void sameArrays() {
		int[] arr1 = { 9,8,7 };
		int[] arr2 = arr1;
		assertTrue(arr1.equals(arr2), ".equals()");
		assertTrue(Arrays.equals(arr1, arr2), "Arrays.equals()");
		assertEquals(0, Arrays.compare(arr1, arr2), "Arrays.compare()");
	}
	
	@Test
	void equalArrays() {
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 1, 2, 3 };
		assertFalse(arr1.equals(arr2), ".equals()");
		assertTrue(Arrays.equals(arr1, arr2), "Arrays.equals()");
		assertEquals(0, Arrays.compare(arr1, arr2), "Arrays.compare()");
	}
	
	@Test
	void emptyArrays() {
		int[] arr1 = {};
		int[] arr2 = {};
		assertFalse(arr1.equals(arr2), ".equals()");
		assertTrue(Arrays.equals(arr1, arr2), "Arrays.equals()");
		assertEquals(0, Arrays.compare(arr1, arr2), "Arrays.compare()");
	}

	@Test
	void nullArrays() {
		int[] arr1 = null;
		int[] arr2 = null;
		assertTrue(Arrays.equals(arr1, arr2), "Arrays.equals()");
		assertEquals(0, Arrays.compare(arr1, arr2), "Arrays.compare()");
	}

	@Test
	void firstArrayNull() {
		int[] arr1 = null;
		int[] arr2 = { 2, 4, 5, 6 };
		assertFalse(Arrays.equals(arr1, arr2), "Arrays.equals()");
		assertEquals(-1, Arrays.compare(arr1, arr2), "Arrays.compare() arr1 = null, всегда меньше");
	}
	
	@Test
	void firstArrayGreaterMatching() {
		int[] arr1 = { 2, 4, 5, 10 };
		int[] arr2 = { 2, 4, 5, 6 };
		assertEquals(1, Arrays.compare(arr1, arr2), "Arrays.compare() arr1 больше, длина одинаковая");
		assertEquals(3, Arrays.mismatch(arr1, arr2), "Arrays.mismatch() общий префикс длиной 3");
	}

	@Test
	void firstArrayLongerNotMatching() {
		int[] arr1 = { 1, 2, 3, 4, 5 };
		int[] arr2 = { 1, 2 };
		assertEquals(3, Arrays.compare(arr1, arr2), "Arrays.compare() arr1 больше и длиннее на 3 элемента");
		assertEquals(2, Arrays.mismatch(arr1, arr2), "Arrays.mismatch() общий префикс длиной 2");
	}
	
	@Test
	void firstArrayGreaterNotMatching() {
		int[] arr1 = { 100 };
		int[] arr2 = { 1 };
		assertEquals(1, Arrays.compare(arr1, arr2), "Arrays.compare() arr1 больше, длина одинаковая");
		assertEquals(0, Arrays.mismatch(arr1, arr2), "Arrays.mismatch() нет совпадающего префикса");
	}

	@Test
	void firstArrayLesserMatching() {
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 1, 2, 4 };
		assertEquals(-1, Arrays.compare(arr1, arr2), "Arrays.compare() arr1 меньше, длина одинаковая");
		assertEquals(2, Arrays.mismatch(arr1, arr2), "Arrays.mismatch() общий префикс длиной 2");
	}

	@Test
	void firstArrayShorterMatching() {
		int[] arr1 = { 1 };
		int[] arr2 = { 1, 2, 4 };
		assertEquals(-2, Arrays.compare(arr1, arr2), "Arrays.compare() arr1 короче на 2 элемента");
		assertEquals(1, Arrays.mismatch(arr1, arr2), "Arrays.mismatch() общий префикс длиной 1");
	}
	
	@Test
	void firstArrayLesserNotMatching() {
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 4, 1, 2, 4 };
		assertEquals(-1, Arrays.compare(arr1, arr2), "Arrays.compare() arr1 начинается с меньшего значения");
		assertEquals(0, Arrays.mismatch(arr1, arr2), "Arrays.mismatch() нет совпадающего префикса");
	}
}
