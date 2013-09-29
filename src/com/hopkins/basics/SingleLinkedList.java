package com.hopkins.basics;

import java.util.NoSuchElementException;

/** A Single linked list **/
public class SingleLinkedList<T> {
	public int INDEX_ITEM_NOT_FOUND = -1;

	protected Node<T> mHead;
	protected int mCount;
	
	public SingleLinkedList() {
		mHead = null;
		mCount = 0;
	}
	
	public void removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("Canot remove, the list is empty");
		}
		mHead = mHead.getNext();
		mCount--;
	}
	
	public void insertFirst(T item) {
		Node<T> oldHead = mHead;
		mHead = new Node<T>(item, oldHead);
		mCount++;
	}
	
	public void insertLast(T item) {
		if (mHead == null) {
			// List is empty, so insertLast = insertFirst
			insertFirst(item);
		} else {
			// Find the last item, and insert after it
			Node<T> last = mHead;
			while (last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(new Node<T>(item));
			mCount++;
		}
	}
	
	public T getFirst(T item) {
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
		for (Node<T> cur = mHead; cur != null; cur = cur.getNext(), i++) {
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
	
	
	protected class Node<T> {
		protected T mItem;
		protected Node<T> mNext;
		
		public Node() {
			init(null, null);
		}
		public Node(T item) {
			init(item, null);
		}
		public Node(T item, Node<T> next) {
			init(item, next);
		}
		
		protected final void init(T item, Node<T> next) {
			mItem = item;
			mNext = next;
		}
		
		public T getItem() {
			return mItem;
		}
		public void setItem(T value) {
			mItem = value;
		}
		public Node<T> getNext() {
			return mNext;
		}
		public void setNext(Node<T> value) {
			mNext = value;
		}
	}
}
