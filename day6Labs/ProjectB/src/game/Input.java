package game;

import java.util.Scanner;

import fixtures.Room;

public class Input {
	
	protected static Scanner sc = new Scanner(System.in);
	
	protected static String[] collectInput() {
		String[] parsed;
		String temp = sc.nextLine();
		parsed = temp.split(" ");
		return parsed;
	}
	
	protected static void parse(String[] command, Player player) {
		Room room;
		if((command[0].toLowerCase()).equals("go") && command.length == 2) {
			if((command[1].toLowerCase()).equals("north") || (command[1].toLowerCase()).equals("south") || (command[1].toLowerCase()).equals("east") || (command[1].toLowerCase()).equals("west")) {
				room = player.getCurrentRoom().getExit(command[1]);
				if(room == null)
					System.out.println("You Ran into a wall, please try again.\n");
				else
					player.updateCurrentRoom(room);
			} else
				System.out.println("Error, where is it you want to go.\n");
		} else if (command[0].equals("quit"))
			Main.run = false;
		else 
			System.out.println("Error your input is not recognized.\n");
			
	}

}
