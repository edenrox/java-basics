package com.hopkins.basics;

public class HashDictionary<T> {
	public static final double MAX_LOAD_FACTOR = 0.8;
	public static final double RESIZE_FACTOR = 2;
	public static final int DEFAULT_CAPACITY = 8;
	public static final int POSITION_KEY_NOT_FOUND = -1;
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
		mCount = 0;
		
		if (oldTable != null) {
			for(int i = 0; i < oldTable.length; i++) {
				if (oldTable[i] != null) {
					KeyValuePair item = (KeyValuePair) oldTable[i];
					put(item.getKey(), item.getValue());
				}
			}
		}
	}
	
	protected void resizeIfNeeded(int newCount) {
		double loadFactor = (double) newCount / mTable.length;
		if (loadFactor > MAX_LOAD_FACTOR) {
			resize((int) Math.round(mTable.length * RESIZE_FACTOR));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void put(String key, T value) {
		if (key == null) {
			throw new IllegalArgumentException("Cannot insert null key into HashDictionary");
		}
		int pos = getPositionForKey(key, false);
		if (pos == POSITION_KEY_NOT_FOUND) {
			resizeIfNeeded(mCount + 1);
			pos = getPositionForKey(key, true);
			mTable[pos] = new KeyValuePair(key, value);
			mCount++;
		} else {
			KeyValuePair kvp = (KeyValuePair) mTable[pos];
			kvp.setValue(value);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T get(String key) {
		int pos = getPositionForKey(key, false);
		if (pos == POSITION_KEY_NOT_FOUND) {
			return null;
		} else {
			KeyValuePair kvp = (KeyValuePair) mTable[pos];
			return kvp.getValue();
		}
	}
	
	public void remove(String key) {
		int pos = getPositionForKey(key, false);
		if (pos == POSITION_KEY_NOT_FOUND) {
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
		int pos = getPositionForKey(key, false);
		return (pos != POSITION_KEY_NOT_FOUND);
	}
	
	@SuppressWarnings("unchecked")
	protected int getPositionForKey(String key, boolean forInsert) {
		int hash = key.hashCode();
		int hashPos = hash % mTable.length;
		
		// Use linear probing to find the next position.
		for (int i = 0; i < mTable.length; i++) {
			int probePos = (hashPos + i) % mTable.length;
			KeyValuePair kvp = (KeyValuePair) mTable[probePos];
			if (forInsert) {
				if ((kvp == null) || (kvp == DELETED_KVP)) {
					return probePos;
				}
			}
			if ((kvp != null) && (kvp != DELETED_KVP) && (kvp.getKey().equals(key))) {
				return probePos;
			}
		}
		return POSITION_KEY_NOT_FOUND;
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
