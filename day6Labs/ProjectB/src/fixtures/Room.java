package fixtures;

public class Room extends Fixture{
	private Room[] exits;
	private Items[] item;
	
	public Room(String name, String shortDescription, String longDescription) {
		super(name, shortDescription, longDescription);
		this.exits = new Room[4]; // size is based on the directions (north, east, south, west)
		item = new Items[4];
	}
	
	
	public Room[] getExits() {
		return exits;
	}
	
	/*
	 * returns the exit based on the string direction provided
	 */
	public Room getExit(String direction) {
		String d = direction.toLowerCase();
		switch(d) {
			case "north": 
				if(exits[0] != null)
					return exits[0];
				break;
			case "east": 
				if(exits[1] != null)
					return exits[1];
				break;
			case "south": 
				if(exits[2] != null)
					return exits[2];
				break;
			case "west": 
				if(exits[3] != null)
					return exits[3];
		}
		return null;
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
