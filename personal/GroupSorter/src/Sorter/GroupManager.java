package Sorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class GroupManager {
	public static Scanner scan = new Scanner(System.in);
	private ArrayList<Groups> groupList;
	
	public GroupManager() {
		groupList = new ArrayList<Groups>();
	}
	
	public void addGroup() {
		System.out.print("Enter the Group name: ");
//		scan.nextLine();
		String name = scan.nextLine();
		groupList.add(new Groups(name));
		System.out.println();
	}
	
	public Groups getGroup(String name) {
		if(!isGroupListEmpty()) {
			Groups temp = null;
			for(int i = 0; i < groupList.size(); i++) {
				temp = groupList.get(i);
				if(temp.getGroupName().equals(name))
					return temp;
			}
		}
		return null;
	}
	
	public void viewScoreMenu() {
		if(!isGroupListEmpty()) {
			System.out.println("1. View Member test score");
			System.out.println("2. View Group test score");
			System.out.println("3. View all test scores");
			System.out.println("4. Get Average test score");
			System.out.print("\n> ");
			try {
				int option = Integer.parseInt(scan.nextLine());
				switch(option) {
				case 1:
					viewMemberScore();
					break;
				case 2:
					viewGroupScores();
					break;
				case 3:
					viewAllScores();
					break;
				case 4:
					getAverageGroupScores();
					break;
				case 5:
					break;
				default:
					System.out.println("Error, incorrect input backing out.");
				}		
			} catch (NumberFormatException e) {
				System.out.println("Error you need to input a number");
			}
			System.out.println();
		}
	}
	
	public void viewGroupMenu() {
		if(!isGroupListEmpty()) {
			for(int i = 0; i < groupList.size(); i++) {
				groupList.get(i).printGroup();
			}
		}
		System.out.println();
	}
	
	public boolean isGroupListEmpty() {
		if(groupList.isEmpty()) {
			System.out.println("There are no groups declared.");
			return true;
		}
		return false;
	}
	
	public void addMemberToGroup() {
		if(!isGroupListEmpty()) {
			System.out.print("Enter the Group Name: ");
			String gName = scan.nextLine();
			if(hasGroup(gName)) {
				Groups temp = getGroup(gName);
				System.out.print("Enter the Member Name: ");
				temp.addMember(scan.nextLine());
			}
		}
		System.out.println();
	}
	
	public void addTestScore() {
		if(!isGroupListEmpty()) {
			while(true) {
				System.out.print("Enter the name of the test: ");
				String testName = scan.nextLine();
				if(!testName.equals("")) {
					for(int i = 0; i < groupList.size(); i++) {
						System.out.println("Setting scores for Group " + groupList.get(i).getGroupName());
						groupList.get(i).setTestScores(testName);
					}
					break;
				} else {
					System.out.println("Error you need to enter a test name.");
				}
			}
		}
		System.out.println();
	}
	
	public void viewMemberScore() {
		System.out.print("Enter Group Name: ");
		String gName = scan.nextLine();
		if(hasGroup(gName)) {
			Groups temp = getGroup(gName);
			System.out.print("Enter Member Name: ");
			String memberName = scan.nextLine();
			if(temp.hasMember(memberName)) {
				System.out.print("Enter Test Name: ");
				String testName = scan.nextLine();
				temp.printMemberScore(testName, memberName);
			}
		}
		System.out.println();
	}
	
	public void viewGroupScores() {
		System.out.print("Enter Group Name: ");
		String groupName = scan.nextLine();
		Groups temp1 = getGroup(groupName);
		System.out.print("Enter Test Name: ");
		String test1 = scan.nextLine();
		temp1.printGroupScore(test1);
		System.out.println();
	}
	
	public void viewAllScores() {
		System.out.print("Enter Test Name: ");
		String testName = scan.nextLine();
		for(int i = 0; i < groupList.size(); i++) {
			groupList.get(i).printGroupScore(testName);
		}
		System.out.println();
	}
	
	public void  getAverageGroupScores(){
		System.out.print("Enter the Test Name: ");
		String testName1 = scan.nextLine();
		HashMap<String, Integer> list = new HashMap<String, Integer>();
		for(int i = 0; i < groupList.size(); i++) {
			String groupName1 = groupList.get(i).getGroupName();
			int score = groupList.get(i).getAverageScore(testName1);
			list.put(groupName1, score);
		}
		TreeMap<String, Integer> t = new TreeMap<String, Integer>();
		t.putAll(list);
		for(Map.Entry<String, Integer> entry: t.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		System.out.println();
	}
	
	public boolean hasGroup(String groupName) {
		for(Groups g: groupList) {
			if(g.getGroupName().equals(groupName))
				return true;
		}
		System.out.println("The group " + groupName + " does not exist.");
		return false;
	}
	
	public static void main(String[] args) {
		boolean run = true;
		GroupManager manager = new GroupManager();
		while(run) {
			System.out.println("1. Add Group");
			System.out.println("2. Add Member to Group");
			System.out.println("3. Add TestScore");
			System.out.println("4. View scores");
			System.out.println("5. View Groups");
			System.out.println("6. Quit");
			System.out.print("\n>");
			try {
			int option = Integer.parseInt(scan.nextLine());
			if(option != -1) {
				switch(option) {
					case 1:
						manager.addGroup();
						break;
					case 2: 
						manager.addMemberToGroup();
						break;
					case 3:
						manager.addTestScore();
						break;
					case 4:
						manager.viewScoreMenu();
						break;
					case 5:
						manager.viewGroupMenu();
						break;
					case 6:
						run = false;
						break;
					default:
						System.out.println("Error, invalid option try again");
					}
				}
			} catch(NumberFormatException e) {
				System.out.println("Error you must enter a number.");
			}
		}
		scan.close();
	}
}
