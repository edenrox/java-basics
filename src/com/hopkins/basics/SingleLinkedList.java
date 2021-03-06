package com.hopkins.basics;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** A Single linked list **/
public class SingleLinkedList<T>
	implements Iterable<T> {
	public int INDEX_ITEM_NOT_FOUND = -1;

	protected Node mHead;
	protected int mCount;
	
	public SingleLinkedList() {
		mHead = null;
		mCount = 0;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new SingleLinkedListIterator();
	}
	
	public void removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("Canot remove, the list is empty");
		}
		mHead = mHead.getNext();
		mCount--;
	}
	
	public void insertFirst(T item) {
		Node oldHead = mHead;
		mHead = new Node(item, oldHead);
		mCount++;
	}
	
	public void insertLast(T item) {
		if (mHead == null) {
			// List is empty, so insertLast = insertFirst
			insertFirst(item);
		} else {
			// Find the last item, and insert after it
			Node last = mHead;
			while (last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(new Node(item));
			mCount++;
		}
	}
	
	public T getFirst() {
		return mHead.getItem();
	}
	public int size() {
		return mCount;
	}
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int indexOf(T item) {
		int i = 0;
		for (Node cur = mHead; cur != null; cur = cur.getNext(), i++) {
			if ((item == null) && (cur.getItem() == null)) {
				return i;
			} else if (item.equals(cur.getItem())) {
				return i;
			}
		}
		
		return INDEX_ITEM_NOT_FOUND;
	}
	public boolean contains(T item) {
		return (indexOf(item) != INDEX_ITEM_NOT_FOUND);
	}
	
	public void clear() {
		mHead = null;
		mCount = 0;
	}
	
	
	protected class Node {
		protected T mItem;
		protected Node mNext;
		
		public Node() {
			init(null, null);
		}
		public Node(T item) {
			init(item, null);
		}
		public Node(T item, Node next) {
			init(item, next);
		}
		
		protected final void init(T item, Node next) {
			mItem = item;
			mNext = next;
		}
		
		public T getItem() {
			return mItem;
		}
		public void setItem(T value) {
			mItem = value;
		}
		public Node getNext() {
			return mNext;
		}
		public void setNext(Node value) {
			mNext = value;
		}
	}
	
	public class SingleLinkedListIterator implements Iterator<T> {
		protected Node cur;
		
		public SingleLinkedListIterator() {
			cur = SingleLinkedList.this.mHead;
		}
		
		@Override
		public boolean hasNext() {
			return (cur != null);
		}
		
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {
				T item = cur.getItem();
				cur = cur.getNext();
				return item;
			}
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
