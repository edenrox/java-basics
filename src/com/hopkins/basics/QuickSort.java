package com.hopkins.basics;

/** Randomized Quick Sort */ 
public class QuickSort {

	
	public static int[] sort(int[] items) {
		// recursively sort the list
		rsort(items, 0, items.length);
		
		// return the list (sorted in place
		return items;
	}
	
	protected static void rsort(int[] items, int offset, int size) {
		if (size <= 1) {
			// the list is already sorted
			return;
		}
		
		int pivot = getPivot(offset, size);
		int pivotValue = items[pivot];
		int numLess = 0;
		
		// put the pivot at the end of the list [items, pivot]
		swap(items, pivot, offset + size - 1);
		
		// move the items that are less than the pivot to the front of the list [small, large, pivot]
		for(int i = offset; i < offset + size - 1; i++) {
			if (items[i] < pivotValue) {
				swap(items, offset + numLess, i);
				numLess++;
			} 
		}
		
		// move the pivot to be between the two sub lists [small, pivot, large]
		swap(items, offset + numLess, offset + size - 1);
		
		// sort the portion smaller than the pivot
		rsort(items, offset, numLess);
		
		// sort the portion larger than the pivot
		rsort(items, offset + numLess + 1, size - (numLess + 1));
	}
	
	protected static int getPivot(int offset, int size) {
		return (int) Math.round(Math.random() * (size - 1)) + offset;
	}
	
	protected static void swap(int[] items, int posA, int posB) {
		int temp = items[posA];
		items[posA] = items[posB];
		items[posB] = temp;
	}
	
	
}
