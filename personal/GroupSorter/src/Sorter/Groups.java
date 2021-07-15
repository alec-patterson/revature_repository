package Sorter;

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
		if(!members.isEmpty()) {
			System.out.println("Group " + name + ":");
			for(int i = 0; i < members.size(); i++) {
				System.out.println(members.get(i).getMemberName());
			}
			System.out.println();
		} else {
			System.out.println("Group " + name + " doesn't have members");
		}
		System.out.println();
	}
	
	public void setTestScores(String testName) {
		if(!members.isEmpty()) {
			for(int i = 0; i < members.size(); i++) {
				String currentName = members.get(i).getMemberName();
				while(true) {
					System.out.print("Enter score for " + currentName + ": " );
					String input = GroupManager.scan.nextLine();
					try { 
						int score = Integer.parseInt(input);
						if(score >= 0 && score <= 100) {
							members.get(i).addTestScore(testName, score);
							break;
						}
						else
							System.out.println("Error, test score must be between 0 - 100.");
					} catch (NumberFormatException e) {
						System.out.println("Error input for Test score must be a number. Try again.");
					}
				}
			}
		} else {
			System.out.println("Error no Members to add scores to.");
		}
	}
	
	public void printGroupScore(String testName) {
		for(int i = 0; i < members.size(); i++) {
			members.get(i).printScore(testName);
		}
	}
	
	public void printMemberScore(String testName, String memberName) {
		for(Members m: members) {
			if(m.getMemberName().equals(memberName)) {
				if(m.hasTest(testName)) {
					int num = m.getTestScore(testName);
					if(num == -1) {
						System.out.println(memberName + " did not take this test");
					}
					else {
						System.out.println(memberName + " - " + num);
					}
				}
			}
		}
	}
	
	public boolean hasMember(String mName) {
		for(Members m: members) {
			if(m.getMemberName().equals(mName));
				return true;
		}
		System.out.println("Member \"" + mName + "\" does not exist in group " + name);
		return false;
	}
}
