package game;

import fixtures.Room;

public class Player {
	private Room currentRoom;
	
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
