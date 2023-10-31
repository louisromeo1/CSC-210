/*
 * Louis Romeo
 * CSC 210
 * Purpose: This function contains the ListStack class,
 * which contains a LinkedList object that is passed
 * through to a StackInterface.
 */


public class ListStack implements StackInterface {
	
	// ListStack takes in StackInterface and given methods.
	
	public LinkedList linkedList;
	
	public ListStack() {
		
		// Constructor method for ListStack class.
		
		linkedList = new LinkedList();
	}
	
	public ListStack(ListStack copy) {
		
		// Copy constructor.
		
		linkedList = new LinkedList(copy.linkedList);
	}
	
	public void push(int value) {
		
		// O(1)
		
		linkedList.addLast(value);
		
	}

	public int pop() {
		
		// O(1)
		
		int pop = linkedList.get(linkedList.size() - 1);
		linkedList.removeLast();
		return pop;
		
	}

	public int peek() {
		
		// O(1)
		
		if(linkedList.head != null) {
			
			int peek = linkedList.get(linkedList.size() - 1);
			return peek;
		}
		return -1;
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
