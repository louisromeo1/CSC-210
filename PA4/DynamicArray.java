/*
 * Louis Romeo
 * CSC 210
 * Purpose: This function contains the DynamicArray class,
 * which initializes a DynamicArray object that can be passed
 * through to other classes in the file.
 */


public class DynamicArray {
	
	private int array[]; 
	private int size;
	public int capacity = 10; 
	
	public DynamicArray() {
		
		// Constructor method for the DynamicArray object.
		
		array = new int[capacity];
		size = 0;
	}
	
	public DynamicArray(DynamicArray copy) {
		
		// Copy constructor for DynamicArray.
		
		size = copy.size();
		capacity = copy.capacity;
		array = new int [capacity];
		for(int i = 0; i < copy.size() - 1; i++) {
			array[i] = copy.get(i);
		}
	}
	
	public void add(int value) {	
		
		if (size >= array.length) {
			
			resize(2 * array.length);
		}
		array[size] = value;
		size++;
	}
	
	public int get(int index) {
		
		return array[index];
		
	}
	
	
	public void remove(int index) {
		
		for(int j = index; j < size - 1; j++) 
		{
			array[j] = array[j+1]; // Sets array pointer to next object.
		}
		array[size] = 0;
		size--;
	}
	
	
	private void resize(int capacity) {
		
		int temp[] = new int[capacity];
		size = capacity < size ? capacity:size;
		for (int i=0; i < size; i++) {
			temp[i] = array[i];
		}
		array = temp;
	}
	

	public int size() {
		
		return size;
	}
	
	public String toString() {
		
		// Method for converting array values to strings from integers.
		
		if(size == 0) {
			return "{}";
		}
	
		String str = "{";
		for(int l = 0; l < size; l++) {
			str += array[l] + ", ";
			
		}
		str = str.substring(0, str.length() - 1);
		StringBuffer newStr = new StringBuffer(str);
		newStr.deleteCharAt(str.length() - 1);
		String finalStr = newStr.toString();
		finalStr += "}";
		
		return finalStr;
	}

}