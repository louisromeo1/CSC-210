/* 
 * Author: Louis Romeo
 * Course: CSC 210
 * Purpose: This program creates a generic map by
 * implementing a HashTable.
 */

import java.util.*;

class Node<K, V> {
	
	// Class for a node object within HashMap.
	
	public K key;
	public V value;
	
	public Node(K key, V value) {
		
		// Constructor for node object.
		
		this.key = key;
		this.value = value;
		
		}
	}

class MyHashMap<K, V> {
	
	static int size = 0;
	final static int TABLE_SIZE = 8;
	
	ArrayList<LinkedList< Node<K,V>>> hashTable = new ArrayList<>(TABLE_SIZE);

	private int hash(K key) {
		
		// Given code for hash function.
	
		int hashCode = key.hashCode();
		int index = hashCode % TABLE_SIZE;
		return Math.abs(index);
	}

	MyHashMap() {
		
		// Creates a HashTable object for HashMap.
	
		for (int i = 0; i < TABLE_SIZE; i++)
			hashTable.add(new LinkedList<Node<K,V>>());
}


	V put(K key, V value) {
		
		// Associates the specified value with the specified key in this map.
		
		int index = hash(key);

		if (containsKey(key)) {
			var list = hashTable.get(index);

			for (Node<K,V> node: list) {
				node.value = value;
				size++;
			}
		
		} else {
			
			Node<K, V> node = new Node<>(key, value);
			hashTable.get(index).addFirst(node);
			size ++;
		}

		return value;
	}


	V remove(K key) {
		
		// Removes the mapping for the specified key from this map if present.
		
		V value = null;

		if (!isEmpty() && containsKey(key)) {
			int index = hash(key);
			var list = hashTable.get(index);

			for (Node<K,V> node: list) {
				value = node.value;
				if (node.key == key) {
					hashTable.get(index).remove(node);
					size--;
				}
			}
		}

		return value;
	}


	V get(K key) {
		
		// Returns the value to which the specified key is mapped, or 
		// null if this map contains no mapping for the key.
		
		int index = hash(key);
		var list = hashTable.get(index);
		V value = null;

		for (Node<K,V> node: list) {
			if (node.key == key)
				value = node.value;
		}

		return value;
	}


	Set<K> keySet() {
		
		// Returns a Set view of the keys contained in this map.
		
		Set<K> set = new HashSet<>();

		for (int i = 0; i < TABLE_SIZE; i++) {
			
			var list = hashTable.get(i);

			for (Node<K,V> node: list)
				set.add(node.key);
		}

		return set;
	}


	int size() {
		
		// Returns the number of key-value mappings in this map.
		
		return size;
	}


	void clear() {
		
		// Removes all of the mappings from this map.
		
		ArrayList<LinkedList< Node<K,V>>> cleared = new ArrayList<>(TABLE_SIZE);

		for (int i = 0; i < TABLE_SIZE; i++)
			cleared.add(new LinkedList<Node<K,V>>());

		hashTable = cleared;
		size = 0;
		}


	boolean isEmpty() {
		
		// Returns true if this map contains no key-value mappings.
	
		if (size() == 0)
			return true;

		return false;
	}


	boolean containsKey(K key) {
		
		// Returns true if this map contains a mapping for the specified key.
		
		return keySet().contains(key);
	}


	boolean containsValue(V value) {
		
		// Returns true if this map maps one or more keys to the specified value.
		
		Set<V> set = new HashSet<>();

		for (int i = 0; i < TABLE_SIZE; i++) {
			var list = hashTable.get(i);

			for (Node<K,V> node: list) {
				set.add(node.value);
			}
		}

		return set.contains(value);
	}


	void printTable() {
		
		// Outputs how many conflicts occur at each bucket and list the keys in that bucket.
		
		int conflictCounter = 0;

		for (int i = 0; i < TABLE_SIZE; i++) {
			var list = hashTable.get(i);
			int conflictPerIndex = list.size();

		if (list.size() != 0)
			conflictPerIndex = list.size() - 1;

		conflictCounter += conflictPerIndex;

			System.out.print("Index " + i + ": " + "(" + conflictPerIndex + " conflicts)," );
			System.out.print("[");

			for (Node<K,V> node: list)
				System.out.print(node.key + ", ");

		System.out.print("]");
		System.out.println();
	}

	System.out.println("Total # of conflicts: " + conflictCounter);
	
	}
}
