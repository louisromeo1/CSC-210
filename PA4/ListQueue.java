/*
 * Louis Romeo
 * CSC 210
 * Purpose: This function contains the ListQueue class,
 * which contains a LinkedList object that is passed
 * through to a QueueInterface.
 */

public class ListQueue implements QueueInterface {

	// ListQueue takes in QueueInterface and given methods.
	
	public LinkedList linkedList;
	
	public ListQueue() {
		
		// Constructor method for ListQueue.
		
		linkedList = new LinkedList();
	}
	
	public ListQueue(ListQueue copy) {
		
		// Copy constructor.
		
		linkedList = new LinkedList(copy.linkedList);
	}
	
	public void enqueue(int value) {
		
		// O(1)
		
		linkedList.addLast(value);
	}


	public int dequeue() {
		
		// O(1)
		
		if(linkedList.size() != 0) {
			
			int first = linkedList.get(0);
			linkedList.removeFirst();
			return first;
		}
		return -1; 
	}


	public int peek() {
		
		// O(1)
		
		if(linkedList.size() != 0) {
			
			int curr = linkedList.get(0);
			return curr;
		}
		return 0;
	}

	public boolean isEmpty() {
		
		// O(1)
		
		if(linkedList.size() == 0) {
			
			return true;
			
		} else {
			
			return false;
		}
	}

	public int size() {
		
		// O(1)
		
		return linkedList.size();
	}

	public void clear() {
		
		// O(1)
		
		linkedList = new LinkedList();
	}
	
	public String toString() {
		
		return linkedList.toString();
	}
}
