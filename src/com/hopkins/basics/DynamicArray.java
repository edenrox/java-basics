package com.hopkins.basics;

public class DynamicArray<T> {
	
	public int DEFAULT_CAPACITY = 8;
	public int RESIZE_FACTOR = 2;
	public int INDEX_ITEM_NOT_FOUND = -1;
	
	public Object[] mData;
	public int mCount;
	
	public DynamicArray() {
		resize(DEFAULT_CAPACITY);
	}
	
	public DynamicArray(int capacity) {
		resize(capacity);
	}
	
	protected final void resize(int newCapacity) {
		Object[] oldData = mData;
		
		// Allocate the new storage
		mData = new Object[newCapacity];
		
		// Copy the old objects into the new storage
		if (oldData != null) {
			System.arraycopy(oldData, 0, mData, 0, mCount);
		}
		
	}
	
	public void add(T item) {
		if (mCount == mData.length) {
			increaseSize();
		}
		mData[mCount] = item;
		mCount++;
	}
	
	public int size() {
		return mCount;
	}
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public int capacity() {
		return mData.length;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		ensureValidIndex(index);
		return (T) mData[index];
	}
	
	public void set(int index, T item) {
		ensureValidIndex(index);
		mData[index] = item;
	}
	
	public int indexOf(T item) {
		for(int i = 0; i < mCount; i++) {
			if ((item == null) && (get(i) == null)) {
				return i;
			} else if (item.equals(get(i))) {
				return i;
			}
		}
		
		return INDEX_ITEM_NOT_FOUND;
	}
	
	public boolean contains(T item) {
		return (indexOf(item) != INDEX_ITEM_NOT_FOUND);
	}
	
	public void clear() {
		mCount = 0;
	}
	
	protected void ensureValidIndex(int index) throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= mCount)) {
			throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds [0, %d)", index, mCount));
		}
	}
	
	protected void increaseSize() {
		resize(RESIZE_FACTOR * mData.length);
	}
	
}
