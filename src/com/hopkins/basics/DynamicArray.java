package com.hopkins.basics;

/** Similar to an ArrayList **/
public class DynamicArray<T> {
	
	public static final int DEFAULT_CAPACITY = 8;
	public static final int RESIZE_FACTOR = 2;
	public static final int INDEX_ITEM_NOT_FOUND = -1;
	
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
	
	public void addAll(T... items) {
		for(T item : items) {
			add(item);
		}
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
	
	public void removeAt(int index) {
		ensureValidIndex(index);
		System.arraycopy(mData, index + 1, mData, index, (mCount - index + 1));
		mCount--;
	}
	
	public void remove(T item) {
		int index = indexOf(item);
		if (index != INDEX_ITEM_NOT_FOUND) {
			removeAt(index);
		}
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
	
	public Object[] toArray() {
		Object[] rv = new Object[mCount];
		System.arraycopy(mData, 0, rv, 0, mCount);
		return rv;
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
