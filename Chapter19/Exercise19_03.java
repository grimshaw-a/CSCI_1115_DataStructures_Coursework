/*
 * Author: Adam Grimshaw
 * Date: 7.13.20
 * Description: Tests the removeDuplicates method.
 * */
import java.util.ArrayList;

public class Exercise19_03 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(14);
		list.add(24);
		list.add(14);
		list.add(42);
		list.add(25);
		
		ArrayList<Integer> newList = removeDuplicates(list);

		System.out.print(newList);
	}
	
	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
		ArrayList<E> newList = new ArrayList<>();
		while(list.size() > 0) {
			E o = list.get(list.size() - 1);
			list.remove(list.size() - 1);
			newList.add(o);
			while(list.contains(o)) {
				list.remove(list.indexOf(o));
			}
		}
		return newList;
	}
}
