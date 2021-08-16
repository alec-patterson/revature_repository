package src.game;

import src.fixtures.Room;

public class Main {
	
	public static boolean run = true;
	
	public static void main(String[] args) {
		RoomManager sr = new RoomManager();
		sr.init();
		Player alec = new Player(sr.startingRoom);
		
		while(run) {
			printRoom(alec);
			Input.parse(Input.collectInput(), alec);
		}
		
		Input.sc.close();
	}
	
	/*
	 * Prints the information regarding the current room:
	 * 	Room Name
	 * 	Long Description of current room
	 * 	List of all available exits and their short Descriptions
	 * 	List of all interactable items
	 */
	private static void printRoom(Player player) {
		System.out.println(player.getCurrentRoom().name + "\n");
		System.out.println(player.getCurrentRoom().longDescription + "\n");
		System.out.println("Exits");
		Room north = player.getCurrentRoom().exits.get("north");
		Room east = player.getCurrentRoom().exits.get("east");
		Room south = player.getCurrentRoom().exits.get("south");
		Room west = player.getCurrentRoom().exits.get("west");
		
		if(north != null)
			System.out.println("North: " + north.name);
		if(east != null)
			System.out.println("East: " + east.name);
		if(south != null)
			System.out.println("South: " + south.name);
		if(west != null)
			System.out.println("West: " + west.name);
		
		System.out.println("\nInteractables:");
		for(int i = 0; i < player.getCurrentRoom().getItems().length; i++) {
			if(player.getCurrentRoom().findItem(i) != null)
				System.out.println(player.getCurrentRoom().findItem(i).name);
		}
		System.out.print("\n> ");
	}
	
}
