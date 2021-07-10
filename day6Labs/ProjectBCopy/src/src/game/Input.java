package src.game;

import java.util.Scanner;

import src.fixtures.Books;
import src.fixtures.Lights;
import src.fixtures.Opener;
import src.fixtures.Room;
import src.fixtures.Sinks;
import src.fixtures.Swimming;

public class Input {
	
	protected static Scanner sc = new Scanner(System.in);
	
	// Grabs user input from the console
	protected static String[] collectInput() {
		String[] parsed;
		String temp = sc.nextLine();
		parsed = temp.split(" ");
		return parsed;
	}
	
	/*
	 * parse takes the provided input that was taken from the user and determines what the user wants to do based on specific commands
	 * To move rooms:
	 * 		move <Room Name>
	 * 		go <Room Name>
	 * 
	 * To Interact with Items:
	 * 		interact <Item Name>
	 * 		press Item Name>
	 * 
	 * Note: For interactions certain items have specific key words in order it intact with them. 
	 * 
	 * If a provided input is incorrect an error message will be printed and the user will be 
	 * prompted to try again
	 */
	protected static void parse(String[] command, Player player) {
		boolean found = false;
		Room room;
		if((command[0].toLowerCase().equals("go") || command[0].toLowerCase().equals("move")) && command.length == 2) {
			if(command[1].toLowerCase().equals("north") || command[1].toLowerCase().equals("south") || command[1].toLowerCase().equals("east") || command[1].toLowerCase().equals("west")) {
				room = player.getCurrentRoom().exits.get(command[1]);
				if(room == null)
					System.out.println("You Ran into a wall, please try again.\n");
				else
					player.updateCurrentRoom(room);
			} else
				System.out.println("Error, where is it you want to go.\n");
		} else if (command[0].equals("quit")) {
			Main.run = false;
		} else if(command[0].toLowerCase().equals("interact") && command.length == 2) {
			for(int i = 0; i < player.getCurrentRoom().getItems().length; i++) {
				if(player.getCurrentRoom().findItem(i) != null) {
					if(player.getCurrentRoom().findItem(i).name.toLowerCase().equals(command[1].toLowerCase())) {
						if(player.getCurrentRoom().findItem(i) instanceof Books) {
							((Books)player.getCurrentRoom().findItem(i)).interact();
						}
						if(player.getCurrentRoom().findItem(i) instanceof Sinks) {
							((Sinks)player.getCurrentRoom().findItem(i)).interact();
						}
						if(player.getCurrentRoom().findItem(i) instanceof Swimming) {
							((Swimming)player.getCurrentRoom().findItem(i)).interact();
						}
						found = true;
					}
				}
			}
			if(found == false)
				System.out.println("Interaction not found, try again.\n");
		} else if(command[0].toLowerCase().equals("press") && command.length == 2) {
			for(int i = 0; i < player.getCurrentRoom().getItems().length; i++) {
				if(player.getCurrentRoom().findItem(i) != null) {
					if(player.getCurrentRoom().findItem(i).name.toLowerCase().equals(command[1].toLowerCase())) {
						if(player.getCurrentRoom().findItem(i) instanceof Opener) {
							((Opener)player.getCurrentRoom().findItem(i)).interact();
						}
						if(player.getCurrentRoom().findItem(i) instanceof Lights) {
							((Lights)player.getCurrentRoom().findItem(i)).interact();
						}
						found = true;
					} 
				}
			}
			if(found == false)
				System.out.println("Interaction not found, try again.\n");
		} else 
			System.out.println("Error your input is not recognized.\n");
			
	}

}
