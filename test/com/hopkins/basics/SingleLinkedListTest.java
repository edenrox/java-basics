package com.hopkins.basics;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class SingleLinkedListTest {
	
	@Test
	public void testEmpty() {
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testInsert() {
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		
		list.insertFirst("Tommy Boy");
		
		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
		assertEquals("Tommy Boy", list.getFirst());
		
		list.insertFirst("Markus");
		
		assertEquals(2, list.size());
		assertEquals("Markus", list.getFirst());
	}
	
	@Test
	public void testIndexOfAndContains() {
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		
		list.insertFirst("Lubba");
		list.insertFirst("Tommy Boy");
		list.insertFirst("Markus");
		list.insertFirst("Tommy Boy");
		
		assertEquals(0, list.indexOf("Tommy Boy"));
		assertEquals(1, list.indexOf("Markus"));
		assertEquals(3, list.indexOf("Lubba"));
		assertEquals(-1, list.indexOf("Smeagle"));
		
		assertFalse(list.contains("Buster"));
		assertTrue(list.contains("Markus"));
	}
	
	@Test
	public void testQueue() {
		SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
		
		// Start with three elements
		list.insertLast(1);
		list.insertLast(2);
		list.insertLast(3);
		
		// Verify the structure
		int item = list.getFirst();
		assertEquals(1, item);
		assertFalse(list.isEmpty());
		assertEquals(3, list.size());
		
		// Remove the first and add another
		list.removeFirst();
		list.insertLast(4);
		
		item = list.getFirst();
		assertEquals(2, item);
		assertFalse(list.isEmpty());
		assertEquals(3, list.size());
		
		// Remove 2
		list.removeFirst();
		list.removeFirst();
		
		item = list.getFirst();
		assertEquals(4, item);
		
		
	}
	
	@Test
	public void testClear() {
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		
		list.insertFirst("Nickelback");
		list.insertFirst("Usher");
		
		assertFalse(list.isEmpty());
		assertEquals(2, list.size());
		assertTrue(list.contains("Usher"));
		
		list.clear();
		
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		assertFalse(list.contains("Usher"));
	}
	
	@Test
	public void testStack() {
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		
		list.insertFirst("Ally");
		list.insertFirst("Beyonce");
		list.insertFirst("Candy");
		list.insertFirst("Dani");
		
		assertFalse(list.isEmpty());
		assertEquals(4, list.size());
		assertEquals("Dani", list.getFirst());
		
		// Remove one
		list.removeFirst();
		assertEquals(3, list.size());
		assertEquals("Candy", list.getFirst());
		
		// Remove two
		list.removeFirst();
		list.removeFirst();
		assertEquals("Ally", list.getFirst());
		assertEquals(1, list.size());
		
		// Remove last
		list.removeFirst();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		// Remove another
		try {
			list.removeFirst();
			fail("should have thrown an exception");
		} catch (NoSuchElementException ex) {
			// noop
		} catch (Exception e) {
			fail("unknown exception" + e.toString());
		}
	}
	
	@Test
	public void testIterator() {
		SingleLinkedList<String> list = new SingleLinkedList<String>();
		
		list.insertFirst("4");
		list.insertFirst("3");
		list.insertFirst("2");
		list.insertFirst("1");
		
		StringBuilder sb = new StringBuilder();
		for(String item : list) {
			sb.append(item);
		}
		
		assertEquals("1234", sb.toString());
	}
}
