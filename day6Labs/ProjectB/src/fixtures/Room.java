package fixtures;

public class Room extends Fixture{
	public Room[] exits;
	
	public Room(String name, String shortDescription, String longDescription) {
		super(name, shortDescription, longDescription);
		this.exits = new Room[4]; // size is based on the directions (north, east, south, west)
	}
	
	public Room[] getExits() {
		return exits;
	}
	
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
			default:
				System.out.print("Your command is not recognized.");
		}
		return null;
	}
}
