/*
* Author: Adam Grimshaw
* Date: 7/17/20
* Description: Searches for a substring in a user supplied string. This assumes that the substring is at least two characters long and that the original string does not include consecutive duplicate characters.
*/

import java.util.*;

class CheckForSubstring2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter a string: ");
		String inputString1 = input.nextLine();
		
		System.out.print("Please enter a substring of that string: ");
		String inputString2 = input.next();
		
		int result = searchForSubstring(inputString1, inputString2);
		
		if(result != -1) {
			System.out.println("Match found at index: " + result);
		} else {
			System.out.println("Match not found.");
		}
	}
	
	public static int searchForSubstring(String s, String ss) {
		int substringCounter = 0;
		for(int i = 0; i < s.length(); i ++) {
			if(findMatchingCharacter(s.charAt(i), ss.charAt(substringCounter))) {
				substringCounter++;
				if(substringCounter == ss.length()) {
					return i - substringCounter + 1;
				}
			} else {
				substringCounter = 0;
			}
		}
		return -1;
	}
	
	public static boolean findMatchingCharacter(char a, char b) {
		if(a == b) {
			return true;
		} else {
			return false;
		}
	}
}