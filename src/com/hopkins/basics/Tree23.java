package com.hopkins.basics;

import java.util.ArrayList;
import java.util.List;

public class Tree23<K extends Comparable<K>, V> {
	
	protected Node root;
	protected int size;
	
	public Tree23() {
		root = null;
		size = 0;
	}
	
	public List<K> keySet() {
		ArrayList<K> rv = new ArrayList<K>();
		addInOrder(rv, root);
		return rv;
	}
	
	protected void addInOrder(List<K> list, Node node) {
		if (node == null) {
			return;
		} else {
			addInOrder(list, node.left);
			list.add(node.small.getKey());
			addInOrder(list, node.middle);
			if (node.large != null) {
				list.add(node.large.getKey());
				addInOrder(list, node.right);
			}
		}
	}
	
	
	public void put(K key, V value) {
		Node node = find(key);
		if (node != null) {
			if (node.small.getKey().equals(key)) {
				node.small.setValue(value);
			} else {
				node.large.setValue(value);
			}
		} else {
			insert(key, value);
		}
	}
	
	protected void insert(K key, V value) {
		size++;
		KeyValuePair kvp = new KeyValuePair(key, value);
		
		Node cur = root;
		
		if (cur != null) {
			// Find the leaf where this node should be inserted
			while (!cur.isLeaf()) {
				int cts = kvp.getKey().compareTo(cur.small.getKey());
				if (cts < 0) {
					cur = cur.left;
				} else {
					int ctl = -1;
					if (cur.large != null) {
						ctl = kvp.getKey().compareTo(cur.large.getKey());
					}
					if (ctl < 0) {
						cur = cur.middle;
					} else {
						cur = cur.right;
					}
				}
			}
		}
	
		addToNode(cur, null, null, kvp);
	}
	
	
	protected void addToNode(Node node, Node lsub, Node rsub, KeyValuePair kvp) {
		if (node == null) {
			// Base case (new root node)
			Node created = new Node(kvp);
			created.left = lsub;
			created.middle = rsub;
			created.linkChildren();
			root = created;
			return;
		}
		
		
		int cts = kvp.getKey().compareTo(node.small.getKey());
		if (node.large == null) {
			// We are expanding a node to have 2 values
			if (cts < 0) {
				node.large = node.small;
				node.small = kvp;
				
				node.right = node.middle;
				node.middle = node.left;
				node.left = lsub;
			} else {
				node.large = kvp;
				node.right = node.middle;
				node.middle = lsub;
			}
			node.linkChildren();
		} else {
			// Recursive case (We are splitting a node that already has 2 values)
			KeyValuePair toPromote = null;
			Node created = null;
			if (cts < 0) {
				toPromote = node.small;
				
				// Create left child
				created = new Node(kvp);
				created.left = lsub;
				created.middle = rsub;
				created.linkChildren();
				
				// Fix up right child
				node.small = node.large;
				node.large = null;
				node.left = node.middle;
				node.middle = node.right;
				node.right = null;
			} else {
				int ctl = kvp.getKey().compareTo(node.large.getKey());
				if (ctl < 0) {
					toPromote = kvp;
					
					// Create the left child
					created = new Node(node.small);
					created.left = node.left;
					created.middle = lsub;
					created.linkChildren();
					
					// Fix up the right child
					node.small = node.large;
					node.large = null;
					node.left = rsub;
					node.middle = node.right;
					node.right = null;
				} else {
					toPromote = node.large;
					
					// Create the left child
					created = new Node(node.small);
					created.left = node.left;
					created.middle = node.middle;
					created.linkChildren();
					
					// Fix up the right child
					node.small = kvp;
					node.large = null;
					node.left = lsub;
					node.middle = rsub;
					node.right = null;
				}
			}
			// Promote an item up to the parent
			addToNode(node.parent, created, node, toPromote);
		}
	}
	
	public V get(K key) {
		Node node = find(key);
		if (node == null) {
			return null;
		} else if (node.small.getKey().equals(key)) {
			return node.small.getValue();
		} else {
			return node.large.getValue();
		}
	}
	
	public void delete(K key) {
		// TODO: impelement delete
		throw new UnsupportedOperationException();
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public boolean contains(K key) {
		return (find(key) != null);
	}
	
	public Node find(K key) {
		Node cur = root;
		while (true) {
			if (cur == null) {
				return null;
			} else {
				int cts = key.compareTo(cur.small.getKey());
				if (cts < 0) {
					cur = cur.left;
				} else if (cts == 0) {
					return cur;
				} else {
					int ctl = -1;
					if (cur.large != null) {
						ctl = key.compareTo(cur.large.getKey());
					}
					if (ctl < 0) {
						cur = cur.middle;
					} else if (ctl == 0) {
						return cur;
					} else {
						cur = cur.right;
					}
				}
			}
		}
	}
	
	public class Node {
		public KeyValuePair small, large;
		public Node parent, left, middle, right;
		
		public Node(KeyValuePair item) {
			small = item;
		}
		
		public boolean isLeaf() {
			return (left == null && middle == null && right == null);
		}
		
		public void linkChildren() {
			if (left != null) {
				left.parent = this;
			}
			if (middle != null) {
				middle.parent = this;
			}
			if (right != null) {
				right.parent = this;
			}
		}
		
		@Override
		public String toString() {
			return String.format("[%s, %s] ", left, right);
		}
	}
	
	public enum NodeType {
		Leaf, Two, Three;
	}
	
	
	public class KeyValuePair {
		protected K key;
		protected V value;
		
		public KeyValuePair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
	}
	
	
	
}
