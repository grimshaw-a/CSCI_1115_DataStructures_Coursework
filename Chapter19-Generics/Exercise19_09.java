/*
 * Author: Adam Grimshaw
 * Date: 7.13.20
 * Description: This tests the sort method.
 */
import java.util.ArrayList;

public class Exercise19_09 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(14);
		list.add(24);
		list.add(4);
		list.add(42);
		list.add(5);
		Exercise19_09.<Integer>sort(list);
		
		System.out.print(list);
	}
	public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
		int counter = 0;
		for(int i = 0; i < list.size(); i++) {
			for(int j = counter; j < list.size(); j++) {
				if(list.get(i).compareTo(list.get(j)) > 0) {
					E o = list.get(j);
					list.set(j, list.get(i));
					list.set(i, o);
					counter++;
				}
			}
		}
	}
}
