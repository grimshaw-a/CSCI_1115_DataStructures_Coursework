/*
* Author: Adam Grimshaw
* Date: 7/15/20
* Description: Counts the instances of each word in a string.
*/

import java.util.*;

public class CountOccurrenceOfWords {
	public static void main(String[] args) {
		// Set text in a string
		String text = "Good morning. Have a good class. " +
			"Have a good visit. Have fun!";

		// Create a TreeMap to hold words as key and count as value
		Map<String, Integer> map = new HashMap<>();
		List<WordOccurrence> list = new ArrayList<>();

		String[] words = text.split("[\\s+\\p{P}]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();
			
			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
					list.add(new WordOccurrence(key, 1));
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
					for(WordOccurrence instance: list) {
						if(instance.getWord().equals(key)) {
							instance.setCount(value);
						}
					}
				}
			}
		}

		// Display key and value for each entry
		Collections.sort(list);
		for(WordOccurrence instance: list) {
			System.out.println(instance.getWord() + " " + instance.getCount());
		}
	}
}

class WordOccurrence implements Comparable<WordOccurrence> {
	private Integer count = 0;
	private String word;
	
	public WordOccurrence(String s, Integer i) {
		word = s;
		count = i;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setCount(Integer i) {
		count = i;
	}
	
	public void setWord(String s) {
		word = s;
	}
	
	@Override
	public int compareTo(WordOccurrence o) {
		if(this.getCount() > o.getCount()) {
			return 1;
		} else if (this.getCount() < o.getCount()) {
			return -1;
		} else {
			return 0;
		}
	}
}