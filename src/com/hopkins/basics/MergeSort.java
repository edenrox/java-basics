package com.hopkins.basics;

/** Merge sort, allocates a lot of temporary space (relies on garbage collector) **/
public class MergeSort {
	
	public static int[] sort(int[] items) {
		
		if (items.length < 2) {
			return items;
		}
		
		// Split the array into two smaller lists (left, right)
		int leftSize = items.length / 2;
		int[] left = new int[leftSize];
		System.arraycopy(items, 0, left, 0, leftSize);
		int[] right = new int[items.length - leftSize];
		System.arraycopy(items, leftSize, right, 0, right.length);
		
		// Sort the smaller lists
		left = sort(left);
		right = sort(right);

		// Merge the sorted smaller lists
		return merge(left, right);
	}
	
	protected static int[] merge(int[] left, int[] right) {
		int[] rv = new int[left.length + right.length];
		int indexLeft = 0, indexRight = 0;
		while(indexLeft + indexRight < rv.length) {
			if ((indexRight != right.length) &&
					((indexLeft == left.length) || (left[indexLeft] > right[indexRight]))) {
				// Left list is empty, pull from the right
				rv[indexLeft + indexRight] = right[indexRight];
				indexRight++;
			} else {
				// Right list is smaller, pull from right
				rv[indexLeft + indexRight] = left[indexLeft];
				indexLeft++;
			}
		}
		
		return rv;
	}

}
