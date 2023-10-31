/*
 * Louis Romeo
 * CSC 210
 * Purpose: This function contains the ArrayQueue class,
 * which takes an object of the DynamicArray class, and
 * implements certain methods to manage the queue.
 */

public class ArrayQueue implements QueueInterface {
	
	// ArrayQueue implements methods from QueueInterface given class.
	
	public DynamicArray array;
	
	public ArrayQueue() {
		
		// Constructor method for ArrayQueue class.
		
		array = new DynamicArray();
	}
	

	public ArrayQueue(ArrayQueue copy) {
		
		// Copy constructor.
		
		array = new DynamicArray(copy.array);
	}
	
	public void enqueue(int value) {
		
		// O(1) complexity.
		
		array.add(value);
	}

	public int dequeue() {
		
		// O(1)
		
		if(array.size() != 0) {
			
			int first = array.get(0);
			array.remove(0);
			return first;
		}
		return -1; 
	}

	public int peek() {
		
		// O(1)
		
		if(array.size() != 0) {
			
			int curr = array.get(0);
			return curr;
		}
		return 0;
	}

	public boolean isEmpty() {
		
		// O(1)
		
		if(array.size() == 0){
			
			return true;
		
		} else {
			
			return false;
		}
	}

	public int size() {
		
		// O(1)
		
		return array.size();
	}

	public void clear() {
		
		// O(1)
		
		array = new DynamicArray();
	}
	
	public String toString() {
		
		return array.toString();
	
	}
	
}
