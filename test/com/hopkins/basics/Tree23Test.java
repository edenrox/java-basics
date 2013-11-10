package com.hopkins.basics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Tree23Test {

	protected Tree23<String, String> empty;
	protected Tree23<String, String> single;
	
	@Before
	public void createTrees() {
		empty = new Tree23<String, String>();
		
		single = new Tree23<String, String>();
		single.put("Gandalf", "Wizard");
	}

	
	@Test
	public void testEmptyAndClear() {
		
		assertTrue(empty.isEmpty());
		assertFalse(single.isEmpty());
		
		single.clear();
		assertTrue(single.isEmpty());
		
	}
	
	@Test
	public void testContains() {
		assertFalse(empty.contains("lova"));
		
		assertTrue(single.contains("Gandalf"));
		assertFalse(single.contains("Gandalf2"));
	}
	
	@Test
	public void testGet() {
		assertEquals("Wizard", single.get("Gandalf"));
		assertEquals(null, single.get("Hello"));
	}
	
	@Test
	public void testPutMany() {
		
		empty.put("Frodo", "Hobbit");
		empty.put("Samwise", "Hobbit");
		empty.put("Legolas", "Elf");
		empty.put("Gandalf", "Wizard");
		
		assertEquals(4, empty.size());
		assertFalse(empty.isEmpty());
		assertTrue(empty.contains("Frodo"));
		assertFalse(empty.contains("Sauron"));
		
		empty.put("Sauron", "Necromancer");
		empty.put("Elrond", "Elf");
		empty.put("Aragorn", "Human");
		empty.put("Gimli", "Dwarf");
		
		assertEquals(8, empty.size());
		
		StringBuilder sb = new StringBuilder();
		for(String item : empty.keySet()) {
			sb.append(item);
			sb.append(", ");
		}
		
		assertEquals("Aragorn, Elrond, Frodo, Gandalf, Gimli, Legolas, Samwise, Sauron, ", sb.toString());
		
	}

}
