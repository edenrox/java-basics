package com.hopkins.basics;

import static org.junit.Assert.*;

import org.junit.Test;


public class MergeSortTest {
	
	@Test
	public void testSort() {
		int[] nums = new int[] {50, 1, 23, 13, 4, 7, 100, 20, 32};
		
		printArray(nums);
		assertFalse(isSorted(nums));
		nums = MergeSort.sort(nums);
		printArray(nums);
		assertTrue(isSorted(nums));
		
		nums = new int[] {50, 1, 23, 13, 4, 7, 100, 20, 32, 15, 15, 12, 41, 29, 18, 17, 29, 17, 15};
		assertFalse(isSorted(nums));
		nums = MergeSort.sort(nums);
		assertTrue(isSorted(nums));
	}
	
	protected void printArray(int[] items) {
		System.err.print("Array: [");
		for(int i = 0; i < items.length; i++) {
			if (i > 0) {
				System.err.print(", ");
			}
			System.err.print(items[i]);
		}
		System.err.println("]");
	}
	
	protected boolean isSorted(int[] items) {
		int last = Integer.MIN_VALUE;
		for(int i = 0; i < items.length; i++) {
			if (last > items[i]) {
				return false;
			} else {
				last = items[i];
			}
		}
		return true;
	}
}
