package com.hopkins.basics;

public class DoubleLinkedList<T> {
	// TODO: incomplete class, need to implement methods here
	
	
	protected class Node<T> {
		protected T mItem;
		protected Node<T> mPrev;
		protected Node<T> mNext;
		
		public Node() {
			init(null, null, null);
		}
		public Node(T item) {
			init(item, null, null);
		}
		public Node(T item, Node<T> prev, Node<T> next) {
			init(item, prev, next);
		}
		
		protected final void init(T item, Node<T> prev, Node<T> next) {
			mItem = item;
			mPrev = prev;
			mNext = next;
		}
		
		public T getItem() {
			return mItem;
		}
		public void setItem(T item) {
			mItem = item;
		}
		public Node<T> getNext() {
			return mNext;
		}
		public void setNext(Node<T> next) {
			mNext = next;
		}
		public Node<T> getPrev() {
			return mPrev;
		}
		public void setPrev(Node<T> prev) {
			mPrev = prev;
		}
	}
}
