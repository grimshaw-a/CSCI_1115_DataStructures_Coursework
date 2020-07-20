/*
* Author: Adam Grimshaw
* Date: 7/17/20
* Description: Finds the maximum consectutive increasingly ordered substring from a user defined string.
*/

import java.util.*;

class StringSearcher {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter a string: ");
		String inputString = input.nextLine();
		
		System.out.println("\nMaximum consecutive substring is: " + searchString(inputString.toLowerCase()));
	}
	
	// Linear time complexity for this loop, O(n)
	public static String searchString(String s) {
		String currentSegment = s.substring(0, 1);
		String longestSegment = currentSegment;
		
		for(int i = 1; i < s.length(); i++) {
			if(s.charAt(i) > s.charAt(i - 1)) {
				currentSegment = currentSegment + s.substring(i, i + 1);
			} else {
				if(currentSegment.length() > longestSegment.length()) {
					longestSegment = currentSegment;
				}
				currentSegment = s.substring(i, i + 1);
			}	
			if(currentSegment.length() > longestSegment.length()) {
				longestSegment = currentSegment;
			}
		}
		return longestSegment;
	}
}