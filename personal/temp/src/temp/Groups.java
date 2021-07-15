package temp;

import java.util.ArrayList;
import java.util.Iterator;

public class Groups {
	private String name;
	private ArrayList<Members> members;
	
	public Groups(String name) {
		this.name = name;
		members = new ArrayList<Members>();
	}
	
	public void addMember(String memberName) {
		members.add(new Members(memberName));
	}
	
	public String getGroupName() {
		return name;
	}
	
	public int getAverageScore(String test) {
		Iterator<Members> i = members.iterator();
		int sum = 0;
		while(i.hasNext()) {
			sum += ((Members)i.next()).getTestScore(test);
		}
		return sum/members.size();
	}
	
	public void printGroup() {
		System.out.println("Group " + name + ":");
		for(int i = 0; i < members.size(); i++) {
			System.out.println(members.get(i).getMemberName());
		}
		System.out.println();
	}
	
	public void setTestScores(String testName) {
		for(int i = 0; i < members.size(); i++) {
			String currentName = members.get(i).getMemberName();
			System.out.print("Enter score for " + currentName + ": " );
			int score = GroupManager.scan.nextInt();
			members.get(i).addTestScore(testName, score);
		}
		GroupManager.scan.nextLine();
	}
}
