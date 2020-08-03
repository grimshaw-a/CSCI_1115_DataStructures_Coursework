/*
* Author: Adam Grimshaw
* Date: 7/24/20
* Description: The class defines a generic queue.
*/

import java.util.*;

public class GenericQueue<E> extends LinkedList<E> {
	
	public void enqueue(E e) {
		addLast(e);
	}
	
	public E dequeue() {
		return removeFirst();
	}
	
	public int getSize() {
		return size();
	}
	
	@Override
	public String toString() {
		return "Queue: " + super.toString();
	}
}