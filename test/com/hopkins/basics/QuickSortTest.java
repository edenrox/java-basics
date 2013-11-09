package com.hopkins.basics;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSortTest {

	
	@Test
	public void testPivot() {
		testPivot(0, 2);
		testPivot(0, 100);
		testPivot(5, 5);
		testPivot(4, 1);
		testPivot(50, 50);
	}
	
	protected void testPivot(int offset, int size) {
		int pivot = QuickSort.getPivot(offset, size);
		assertTrue(pivot >= offset);
		assertTrue(pivot < offset + size);
	}
	
	@Test
	public void testSwap() {
		int[] nums = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
		
		QuickSort.swap(nums, 0, 5);
		assertEquals(5, nums[0]);
		assertEquals(0, nums[5]);
		
		QuickSort.swap(nums, 2, 3);
		assertEquals(3, nums[2]);
		assertEquals(2, nums[3]);
		
	}
	
	@Test
	public void testSort() {
		int[] nums = new int[] {50, 1, 23, 13, 4, 7, 100, 20, 32};
		assertFalse(isSorted(nums));
		QuickSort.sort(nums);
		assertTrue(isSorted(nums));
		
		nums = new int[] {50, 1, 23, 13, 4, 7, 100, 20, 32, 15, 15, 12, 41, 29, 18, 17, 29, 17, 15};
		assertFalse(isSorted(nums));
		QuickSort.sort(nums);
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
