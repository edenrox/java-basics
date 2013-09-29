package com.hopkins.basics;

import static junit.framework.Assert.*;

import org.junit.Test;

public class DynamicArrayTest {
	
	
	@Test
	public void testConstruction() {
		
		DynamicArray<String> da = new DynamicArray<String>();
		assertEquals(DynamicArray.DEFAULT_CAPACITY, da.capacity());
		assertEquals(0, da.size());
		
		da = new DynamicArray<String>(5);
		assertEquals(5, da.capacity());
		assertEquals(0, da.size());
		
	}
	
	@Test
	public void testAddAndResize() {
		DynamicArray<String> da = new DynamicArray<String>(2);
		assertEquals(2, da.capacity());
		assertEquals(0, da.size());
		
		da.add("one");
		assertEquals(1, da.size());
		assertEquals("one", da.get(0));
		
		da.add("two");
		assertEquals(2, da.size());
		assertEquals("two", da.get(1));
		
		da.add("three");
		assertEquals(4, da.capacity());
		assertEquals("three", da.get(2));
		
		da.add("four");
		assertEquals("four", da.get(3));
		
		da.add("five");
		assertEquals(8, da.capacity());
		assertEquals("five", da.get(4));
	}
	
	@Test
	public void testAddAndRemove() {
		DynamicArray<String> da = new DynamicArray<String>(2);
		da.add("a");
		da.add("b");
		da.add("c");
		da.add("d");
		da.add("e");
		assertEquals(5, da.size());
		
		da.removeAt(0);
		assertEquals(4, da.size());
		assertEquals("b", da.get(0));
		
		da.remove("b");
		assertEquals(3, da.size());
		assertEquals("c", da.get(0));
		assertEquals("d", da.get(1));
		
	}
	
	@Test
	public void testAddAllSetAndClear() {
		DynamicArray<String> da = new DynamicArray<String>(5);
		
		da.addAll("a", "B", "c", "d", "e");
		assertEquals(5, da.size());
		assertEquals("a", da.get(0));
		
		da.set(0, "A");
		assertEquals("A", da.get(0));
		
		da.clear();
		assertEquals(0, da.size());
	}
	
	@Test
	public void testBadIndexes() {
		DynamicArray<String> da = new DynamicArray<String>(5);
		
		da.addAll("a", "B", "c", "d", "e");
		
		testBadIndex(da, -1);
		testBadIndex(da, 5);
		testBadIndex(da, 100);
		
		assertEquals("B", da.get(1));
	}
	
	protected void testBadIndex(DynamicArray<String> da, int index) {
		boolean didFail = false;
		try {
			da.get(index);
		} catch (IndexOutOfBoundsException ex) {
			didFail = true;
		}
		
		assertTrue(didFail);
	}
	
	@Test
	public void testToArray() {
		DynamicArray<String> da = new DynamicArray<String>(5);
		
		da.addAll("a", "B", "c", "d", "e");
		
		Object[] all = da.toArray();
		assertEquals(5, all.length);
		assertEquals("B", all[1]);
		
		da.set(1, "Slumber");
		assertEquals("B", all[1]);
		assertEquals("Slumber", da.get(1));
		
		da.add("fiver");
		assertEquals(6, da.size());
		assertEquals(5, all.length);
	}
}
