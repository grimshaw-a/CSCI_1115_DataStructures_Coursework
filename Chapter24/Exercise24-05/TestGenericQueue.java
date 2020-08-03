/*
* Author: Adam Grimshaw
* Date: 7/24/20
* Description: This tests the generic queue class.
*/

class TestGenericQueue {
	public static void main(String[] args) {
		GenericQueue<String> queue1 = new GenericQueue<>();
		
		queue1.enqueue("red");
		System.out.println("1) " + queue1);
		queue1.enqueue("green");
		System.out.println("2) " + queue1);
		queue1.enqueue("blue");
		System.out.println("3) " + queue1);
		queue1.dequeue();
		System.out.println("4) " + queue1);
		queue1.dequeue();
		System.out.println("5) " + queue1);
		queue1.dequeue();
		System.out.println("6) " + queue1);
		
	}
}
