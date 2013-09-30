package com.hopkins.basics;

import static junit.framework.Assert.*;

import org.junit.Test;

public class HashDictionaryTest {

	@Test
	public void testConstruction() {
		
		HashDictionary<String> hd = new HashDictionary<String>();
		assertEquals(0, hd.size());
		assertEquals(HashDictionary.DEFAULT_CAPACITY, hd.capacity());
		assertFalse(hd.containsKey("name"));
		
		hd = new HashDictionary<String>(10);
		assertEquals(0, hd.size());
		assertEquals(10, hd.capacity());
	}
	
	@Test
	public void testGetAndPut() {
		HashDictionary<String> hd = new HashDictionary<String>(10);
		assertEquals(0, hd.size());
		assertEquals(10, hd.capacity());
		assertFalse(hd.containsKey("name"));
		
		hd.put("name", "Gandalf");
		assertTrue(hd.containsKey("name"));
		assertEquals("Gandalf", hd.get("name"));
		assertEquals(1, hd.size());
		
		hd.put("color", "grey");
		assertTrue(hd.containsKey("color"));
		assertEquals("grey", hd.get("color"));
		assertEquals(2, hd.size());
		
		hd.put("color", "white");
		assertTrue(hd.containsKey("color"));
		assertEquals("white", hd.get("color"));
		assertEquals(2, hd.size());
		
	}
	
	@Test
	public void testResize() {
		HashDictionary<String> hd = new HashDictionary<String>(2);
		assertEquals(0, hd.size());
		assertEquals(2, hd.capacity());
		
		hd.put("name", "Gandalf");
		assertEquals(1, hd.size());
		assertEquals(2, hd.capacity());
		
		hd.put("color", "grey");
		assertEquals(2, hd.size());
		assertEquals(4, hd.capacity());
		assertTrue(hd.containsKey("name"));
		assertTrue(hd.containsKey("color"));
		
		hd.put("color", "white");
		hd.put("horse", "Shadowfax");
		hd.put("beard", "yes");
		
		assertEquals(4, hd.size());
		assertEquals(8, hd.capacity());
		assertTrue(hd.containsKey("name"));
		assertTrue(hd.containsKey("color"));
		assertTrue(hd.containsKey("horse"));
	}
	
	@Test
	public void testPutAndDelete() {
		HashDictionary<String> hd = new HashDictionary<String>(2);
		assertEquals(0, hd.size());
		assertEquals(2, hd.capacity());
		
		hd.put("name", "Gandalf");
		hd.put("color", "white");
		hd.put("horse", "Shadowfax");
		hd.put("beard", "yes");
		
		assertEquals(4, hd.size());
		assertTrue(hd.containsKey("name"));
		assertEquals("Gandalf", hd.get("name"));
		
		hd.remove("name");
		assertEquals(3, hd.size());
		assertFalse(hd.containsKey("name"));
		assertEquals(null, hd.get("name"));
		
		hd.put("name", "The White Wizard");
		assertEquals(4, hd.size());
		assertTrue(hd.containsKey("name"));
		assertEquals("The White Wizard", hd.get("name"));
		
		
		hd.clear();
		assertEquals(0, hd.size());
		assertFalse(hd.containsKey("name"));
		
	}
}
