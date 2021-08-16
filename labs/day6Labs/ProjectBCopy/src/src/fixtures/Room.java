package src.fixtures;

import java.util.HashMap;

public class Room extends Fixture{
	public HashMap<String, Room> exits = new HashMap<>();
	private Items[] item;
	
	public Room(String name, String shortDescription, String longDescription) {
		super(name, shortDescription, longDescription);
		item = new Items[4];
	}
	
	
	/*
	 * Add items will add interactables to the 
	 * interactables array
	 * 
	 * Array can only hold up to four interactables but so far
	 * None of them have that many
	 */
	public void addItems(Items itm) {
		for(int i = 0; i < item.length; i++) {
			if(item[i] == null) {
				item[i] = itm;
				break;
			}
		}
	}
	
	/*
	 * This function finds the Item based on the index passed in
	 */
	public Items findItem(int index) {
		return item[index];
	}
	
	/*
	 * returns the entire item array for a room
	 * this is really only used to get the length of the array
	 */
	public Items[] getItems() {
		return item;
	}
}
