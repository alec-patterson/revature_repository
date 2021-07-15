package temp;

import java.util.HashMap;

public class Members {
	private String name;
	private HashMap<String, Integer> scores;
	
	public Members(String name) {
		this.name = name;
		scores = new HashMap<String, Integer>();
	}
	
	public int getTestScore(String test) {
		return scores.get(test);
	}
	
	public void addTestScore(String test, int score) {
		scores.put(test, score);
	}
	
	public void printScore(String test) {
		System.out.println(name + " - " + test + ": " + getTestScore(test));
	}
	
	public String getMemberName() {
		return name;
	}
	
}
