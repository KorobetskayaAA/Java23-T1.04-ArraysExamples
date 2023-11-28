package ru.teamscore.java23.arraysearch;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class SearchMain {
	public static void main(String[] args) {
		Random rnd = new Random(100);
	    Item[] values = new Item[10_000_000];
	    for (int i = 0; i < values.length; i++) {
	    	values[i] = new Item(rnd.nextInt(-values.length, values.length));
	    }
	    
	    int[] valuesToSearch = new int[1000]; 
	    valuesToSearch[0] = values[0].getValue();
	    valuesToSearch[1] = values[values.length - 1].getValue();
	    for (int i = 2; i < valuesToSearch.length; i++) {
	    	valuesToSearch[i] = rnd.nextInt(-values.length, values.length);
	    }
	    
	    Instant startSort = Instant.now();
		ArraySearch search = new ArraySearch(values);
	    Instant finishSort = Instant.now();
	    System.out.printf("Sorting: %s ms\n",
	    		Duration.between(startSort, finishSort).toMillis());
	    
	    
	    Instant startBrute = Instant.now();
	    int[] bruteIndexes = new int[valuesToSearch.length]; 
	    for (int i = 0; i < valuesToSearch.length; i++) {
		    bruteIndexes[i] = search.searchValueBrute(valuesToSearch[i]);
	    }
	    Instant finishBrute = Instant.now();
	    System.out.printf("Brute search: %s ms %s\n",
	    		Duration.between(startBrute, finishBrute).toMillis(), 
	    		Arrays.toString(bruteIndexes));
	    

	    Instant startBinary = Instant.now();
	    int[] binaryIndexes = new int[valuesToSearch.length]; 
	    for (int i = 0; i < valuesToSearch.length; i++) {
	    	binaryIndexes[i] = search.searchValueBinary(valuesToSearch[i]);
	    }
	    Instant finishBinary = Instant.now();
	    System.out.printf("Binary search: %s ms %s\n",
	    		Duration.between(startBinary, finishBinary).toMillis(), 
	    		Arrays.toString(binaryIndexes));
	}
}
