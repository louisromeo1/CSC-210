/*
 * Louis Romeo
 * CSC 210
 * Purpose: This function contains the ArrayStack class,
 * which contains a DynamicArray object that is passed
 * through to a StackInterface.
 */



public class ArrayStack implements StackInterface {
	
	// ArrayStack takes in StackInterface and given methods.
	
	public DynamicArray array; 
	
	public ArrayStack() {
		
		// Constructor for ArrayStack class.
		
		array = new DynamicArray();
	}
	
	public ArrayStack(ArrayStack copy) {
		
		// Copy constructor for class.
		
		array = new DynamicArray(copy.array);
	}

	public void push(int value) {
		
		// O(1)
		
		array.add(value);
	}

	public int pop() {
		
		// O(1)
		
		if(array != null) {
			int pop = array.get(array.size() - 1);
			array.remove(array.size() - 1);
			return pop;
		}
		return -1;
	}

	public int peek() {
		
		// O(1)
		
		if(array != null) {
			
			int peek = array.get(array.size() - 1);
			return peek;
		}
		return -1;
	}	


	public boolean isEmpty() {
		
		// O(1)
		
		if(array.size() == 0) {
			
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

