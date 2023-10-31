/*
 * Louis Romeo
 * CSC 210
 * Purpose: This program simulates a hospital's patient
 * queue, and allows for actions to be done to the 
 * Patient object.
 */

public class PatientQueue {
	
	private Patient [] heap;
	private int size;
	private int capacity = 10; // Size is 10.
	
		
	public PatientQueue() {
		
		// Constructor for PatientQueue.
		
 		heap = new Patient[capacity];
		heap[0] = null; // First spot in heap is always null.
	}
	

	public void resize() {
		
		// Method responsible for resizing the heap.
		
		capacity = capacity * 2;
		Patient[] tempQueue = new Patient[capacity];
		
		for(int i = 0; i < size; i++) {
			
			tempQueue[i] = heap[i];
		}
		heap = tempQueue;
	}
	
	public void add(Patient patient, int spot) {
		
		// Helper method for enqueue.
		
		heap[spot] = patient;
		int child = spot;
		int parent = spot / 2;
		if(parent == 0) {
			return;
		}
		while(child > 0 && heap[parent].priority > heap[child].priority || 
				((heap[parent].priority == heap[child].priority 
				&& heap[parent].name.compareTo(heap[child].name) > 0))) 
		{
			Patient temp = heap[parent];
			heap[parent] = heap[child];
			heap[child] = temp;
			child = parent;
			parent = child / 2;
			if(parent == 0) {
				return;
			}
		}

	}
	
	public void enqueue(String name, int priority) 
	{
		/*
		 * Adds a Patient to the back of the queue. 
		 * param: name The name String asssociated with the Patient.
		 * param: priority The priority number assigned to the Patient.
		 */
		
		size++;
		if(size >= capacity) {
			resize();
		}
		Patient p = new Patient(name, priority);
		add(p, size);
	}

	public void enqueue(Patient patient) {
		
		/*
		 * Adds a Patient to the back of the queue. 
		 * param: name The name String asssociated with the Patient.
		 */
		
		size++;
		if(size >= capacity) {
			resize();
		}
		add(patient, size);
	}
	
	public String dequeue() 
	{
		
		/*
		 * Method responsible for taking a Patient out of the 
		 * Queue. Returns the string of Patient removed.
		 */
		
		Patient tempPatient = heap[1];
		heap[1] = heap[size];
		heap[size] = null;
		size--;
		int parent = 1;
		int child1 = (2 * (parent));
		int child2 = (2 * (parent)) + 1;
		Patient curP = heap[parent];
		Patient curCh1 = heap[child1];
		Patient curCh2 = heap[child2];
		
		Patient curCh;
		int curLoc;
		
		if(curCh2 == null) {
			curCh = curCh1;
			curLoc = child1;
		}
		else if(curCh1.priority < curCh2.priority ||  (curCh1.name.compareTo(curCh2.name) < 0 
				&& curCh1.priority == curCh2.priority)) 
		{
			curCh = curCh1;
			curLoc = child1;
		
		} else {
			
			curCh = curCh2;
			curLoc = child2;
		}
		
		while(curP != null && curCh1 != null && curLoc != size/2) 
		{
			if(curP.priority > curCh.priority || 
				((curP.priority == curCh.priority && 
				curP.name.compareTo(curCh.name) > 0))) {
				
			heap[parent] = curCh;
			heap[curLoc] = curP;
			
			parent = curLoc;
			child1 = (2 * (parent));
			child2 = (2 * (parent)) + 1;
			curP = heap[parent];
			curCh1 = heap[child1];
			curCh2 = heap[child2];
			
			if(curCh2 == null) {
				curCh = curCh1;
				curLoc = child1;
			}
			else if(curCh1.priority < curCh2.priority ||  (curCh1.name.compareTo(curCh2.name) < 0 
					&& curCh1.priority == curCh2.priority)) 
			{
				curCh = curCh1;
				curLoc = child1;
			
			} else {
				
				curCh = curCh2;
				curLoc = child2;
			}
			} else {
				
				break;
			}
		}
		return tempPatient.toString();	
	}
	
	
	public String peek() {
		
		/*
		 * Method that returns the name String of the Patient
		 * that is highest up in the Queue.
		 */
		
		try {
			
			return heap[1].name;
		
		} catch(ArrayIndexOutOfBoundsException e) {
			
			System.out.println("There is no patient.");
			return (" ");
			
		}
	}
	
	public int peekPriority() {
		
		/*
		 * Returns the priority of the patient at the 
		 * front of the Queue.
		 */
		
		try {
			return heap[1].priority;
		
		} catch(ArrayIndexOutOfBoundsException e) {
			
			return -1;
		}
		
	}
	
	public void changePriority(String name, int newPriority) {
		
		/*
		 * Method implemented to change the priority assigned to
		 * a Patient, using the name and priority values.
		 * param: name The name String asssociated with the Patient.
		 * param: newPriority The new priority number assigned to the Patient.
		 */
		
		Patient p = new Patient(name, newPriority);
		
		for(int i = 0; i < size; i++) {
			
			if(heap[i] != null) {
				
				if(heap[i].name.equals(name)) {
					
					add(p, i);
				}
			}
		}
	}
	
	public boolean isEmpty() {return size == 0;} // Checks if heap is empty.
	
	public int size() {return size;} // Returns the size of the queue.
	
	public void clear() {heap = new Patient[capacity];} // Clears the queue.

	public String toString() {
		
		// Override of java toString() method for printing heap.
		
		String s = "{";
		
		for (int i = 1; i <= size; i++) {
			s += (i == 1 ? "" : ", ") + heap[i].toString();
		}
		return s + "}";
	}
}

// Check previous submission for Heap class implementation, 
// Autograder would not execute, but worked perfectly in console.