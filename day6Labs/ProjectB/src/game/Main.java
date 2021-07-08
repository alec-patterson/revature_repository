package game;

import fixtures.Room;

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
	
	private static void printRoom(Player player) {
		System.out.println(player.getCurrentRoom().name + "\n");
		System.out.println(player.getCurrentRoom().longDescription + "\n");
		System.out.println("Exits");
		Room[] exit = player.getCurrentRoom().getExits();
		for(int i = 0; i < exit.length; i++) {
			String direction = "north";
			if(i == 0)
				direction = "north";
			if(i == 1)
				direction = "east";
			if(i == 2)
				direction = "south";
			if(i == 3)
				direction = "west";
			
			if(exit[i] != null) {
				System.out.println(direction + ": " + exit[i].shortDescription);
			}
		}
		System.out.print("\n> ");
	}
	
	
}
