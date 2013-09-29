package com.hopkins.basics;

public class HashDictionary<T> {
	public static final double MAX_LOAD_FACTOR = 0.8;
	public static final int DEFAULT_CAPACITY = 8;
	protected final KeyValuePair DELETED_KVP = new KeyValuePair(null, null);
	
	protected Object[] mTable;
	protected int mCount;
	
	public HashDictionary() {
		mCount = 0;
		resize(DEFAULT_CAPACITY);
	}
	public HashDictionary(int capacity) {
		mCount = 0;
		resize(capacity);
	}
	
	public int capacity() {
		return mTable.length;
	}
	
	public int size() {
		return mCount;
	}
	
	@SuppressWarnings("unchecked")
	protected final void resize(int capacity) {
		Object[] oldTable = mTable;

		// Allocate new storage
		mTable = new Object[capacity];
		
		if (oldTable != null) {
			for(int i = 0; i < oldTable.length; i++) {
				if (oldTable[i] != null) {
					KeyValuePair item = (KeyValuePair) oldTable[i];
					put(item.getKey(), item.getValue());
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void put(String key, T value) {
		int pos = getPositionForKey(key);
		if (mTable[pos] == null) {
			mTable[pos] = new KeyValuePair(key, value);
			mCount++;
		} else {
			KeyValuePair kvp = (KeyValuePair) mTable[pos];
			kvp.setValue(value);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T get(String key) {
		int pos = getPositionForKey(key);
		KeyValuePair kvp = (KeyValuePair) mTable[pos];
		if (kvp != null) {
			return kvp.getValue();
		} else {
			return null;
		}
	}
	
	public void remove(String key) {
		int pos = getPositionForKey(key);
		if (mTable[pos] == null) {
			// this key doesn't exist, so do nothing
		} else {
			// mark this key as deleted
			mTable[pos] = DELETED_KVP;
			mCount--;
		}
	}
	
	public void clear() {
		mCount = 0;
		for(int i = 0; i < mTable.length; i++) {
			mTable[i] = null;
		}
	}
	
	public boolean containsKey(String key) {
		int pos = getPositionForKey(key);
		return (mTable[pos] != null);
	}
	
	@SuppressWarnings("unchecked")
	protected int getPositionForKey(String key) {
		int hash = key.hashCode();
		int hashPos = hash % mTable.length;
		
		// Use linear probing to find the next position.
		for (int i = 0; i < mTable.length; i++) {
			int probePos = (hashPos + i) % mTable.length;
			KeyValuePair kvp = (KeyValuePair) mTable[probePos];
			if ((kvp == null) 
					|| (kvp == DELETED_KVP)
					|| (kvp.getKey().equals(key))) {
				return probePos;
			}
		}
		
		return 0;
	}
	
	
	
	public class KeyValuePair {
		protected String mKey;
		protected T mValue;
		
		public KeyValuePair(String key, T value) {
			mKey = key;
			mValue = value;
		}
		
		public String getKey() {
			return mKey;
		}
		
		public T getValue() {
			return mValue;
		}
		
		public void setValue(T value) {
			mValue = value;
		}
	}
}
