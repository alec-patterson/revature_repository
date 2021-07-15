package temp;

import java.util.ArrayList;
import java.util.Scanner;

public class GroupManager {
	public static Scanner scan = new Scanner(System.in);
	private ArrayList<Groups> groupList;
	
	public GroupManager() {
		groupList = new ArrayList<Groups>();
	}
	
	public void addGroup() {
		System.out.print("Enter the Group name: ");
		scan.nextLine();
		String name = scan.nextLine();
		groupList.add(new Groups(name));
	}
	
	public Groups getGroup(String name) {
		Groups temp = null;
		for(int i = 0; i < groupList.size(); i++) {
			temp = groupList.get(i);
			if(temp.getGroupName().equals(name))
				return temp;
		}
		return null;
	}
	
	public void viewScoreMenu() {
		
	}
	
	public void viewGroupMenu() {
		
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
			System.out.print("\n>");
			int option = scan.nextInt();
			if(option != -1) {
				switch(option) {
					case 1:
						manager.addGroup();
						break;
					case 2: 
						System.out.print("Enter the Group Name: ");
						scan.nextLine();
						Groups temp = manager.getGroup(scan.nextLine());
						System.out.print("Enter the Member Name: ");
						temp.addMember(scan.nextLine());
						break;
					case 3:
						System.out.print("Enter the name of the test: ");
						String testName = scan.nextLine();
						for(int i = 0; i < manager.groupList.size(); i++) {
							System.out.println("Setting scores for Group " + manager.groupList.get(i).getGroupName());
							manager.groupList.get(i).setTestScores(testName);
						}
						break;
					case 4:
						manager.viewScoreMenu();
						break;
					case 5:
						manager.viewGroupMenu();
						break;
					default:
						System.out.println("Error, invalid option try again");
				}
			}
		}
		scan.close();
	}
}
