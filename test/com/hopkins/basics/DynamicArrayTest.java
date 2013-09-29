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
	

}
