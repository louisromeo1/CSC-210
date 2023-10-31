interface StackInterface {

    /*
     * Add an element to the top of the stack.
     */
    void push(int value);

    /*
     * Remove and return the top element in the stack.
     * 
     * If the user attempts to pop an empty stack, ignore the
     * request (i.e. make no changes to the stack) and return -1.
     */
    int pop();

    /*
     * Return (but do NOT remove) the top element of the stack.
     * 
     * If the user attempts to peek on an empty stack, ignore the
     * request (i.e. make no changes to the stack) and return -1.
     */
    int peek();

    /*
     * Returns true if the stack has no elements.
     */
    boolean isEmpty();

    /*
     * Returns the number of elements in the stack.
     */
    int size();

    /*
     * Removes all elements from the stack.
     */
    void clear();

}
