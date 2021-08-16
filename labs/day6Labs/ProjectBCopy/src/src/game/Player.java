package src.game;

import src.fixtures.Room;

public class Player {
	private Room currentRoom;
	
	/*
	 * Player class holds the room you are currently in
	 */
	public Player(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void updateCurrentRoom(Room newRoom) {
		currentRoom = newRoom;
	}
}
