/*
 * Louis Romeo
 * CSC 210
 * Purpose: This function contains the LinkedList class,
 * which initializes a LinkedList object that can be passed
 * through to other classes in the file.
 */

public class LinkedList {
	
	public Node head;
	public Node tail;
	public int size;
	
	private class Node {
		
		public int data;
		public Node next;
	
		public Node(int data) {
			
			this.data = data;
			this.next = null;
		}
	}
	
	public LinkedList() {
		
		// Constructor method for LinkedList object.
		
		head = null;
		tail = null;
		size = 0;
	}
	
	public LinkedList(LinkedList copy) {
		
		// Copy constructor.
		
		Node curr = copy.head;
		while(curr != null) {
			addLast(curr.data);
			curr = curr.next;
		}
	}
	
	public void addLast(int data) {
		
		Node newNode = new Node(data);
		size++;
		
		if (head == null)
		{
			head = newNode;
			tail = newNode;
		
		} else {
			
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	
	
	public int getLast() {
		
		return tail.data;
	
	}
	
	public int get( int idx ) {
		
		Node p = head;
		for (int i = 0; i < idx; i++) 
			p = p.next;
			return p.data;
	}
	
	
	public void removeLast() {
				
		if(size == 1) {
			head = null;
			tail = null;
			size--;
		} else {
			
		Node curr = head;
		while(curr.next.next != null) {
			curr = curr.next;
		}
		curr.next = null;
		tail = curr;
		size--;
	}
}
	
	
	public boolean equals(Object obj) {
		
		
		if (obj instanceof LinkedList) {
			
            LinkedList newListListk = (LinkedList) obj;
            Node curr = head;
            Node temp = newListListk.head;
            
            if(size != newListListk.size) {
            	return false;
            }
            
            while(curr != null && temp != null) {
            	if(curr.data != temp.data) {
            		return false;
            	}
            	curr = curr.next;	
            	temp = temp.next;
            }
            return true;
		
		} else {
			
			return false;
		}
	}
	
	public String toString() {
		
		
		if(size == 0) {
			return "{}";
		}
		String str = "{";
		Node curr = head;
		while(curr != null) 
		{
			str += curr.data + ",";
			curr = curr.next;
		}
		str = str.substring(0, str.length() - 1);
		str += "}";
		return str;
	}
	
	public int size() {
		return size;
	}

	public int removeFirst() {
		
		// Method created to remove first item from LinkedList.
		
		int headVal = head.data;
		if(head != null) {
			head = head.next;
	}
		size--;
		return headVal;
	}
}
