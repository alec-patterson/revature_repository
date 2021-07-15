package Sorter;

import java.util.HashMap;
import java.util.Set;

public class Members {
	private String name;
	private HashMap<String, Integer> scores;
	
	public Members(String name) {
		this.name = name;
		scores = new HashMap<String, Integer>();
	}
	
	public int getTestScore(String test) {
		Integer i = scores.get(test);
		if(i == null)
			return -1;
		return i;
	}
	
	public void addTestScore(String test, int score) {
		scores.put(test, score);
	}
	
	public void printScore(String test) {
		int t = getTestScore(test);
		if(t != -1) {
			System.out.println(name + " - " + test + ": " + t);
		}
		else {
			System.out.println(name + " did not take this test.");
		}
	}
	
	public String getMemberName() {
		return name;
	}
	
	public boolean hasTest(String testName) {
		Set<String> t = scores.keySet();
		for(String s: t) {
			if(s.equals(testName)) {
				return true;
			}
		}
		System.out.println("Test \"" + testName + "\" does not exist.");
		return false;
	}
	
}
