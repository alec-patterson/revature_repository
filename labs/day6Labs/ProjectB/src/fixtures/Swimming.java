package fixtures;

public class Swimming extends Items implements Interactables{
	boolean inWater = false;
	Room[] exits;
	Room[] temp;
	
	/*
	 * if the user chooses to go into the pool while their current location is in the backyard
	 * The user will not be able to go anywhere until they get out of the pool
	 */
	public Swimming(Room[] exits) {
		super("Pool");
		this.exits = exits;
		temp = new Room[4];
		for(int i = 0; i < exits.length; i++)
			temp[i] = exits[i];
	}
	
	@Override
	public void interact() {
		if(inWater == false) {
			System.out.println("Your are swimming in the pool\n");
			for(int i = 0; i < exits.length; i++)
				exits[i] = null;
			inWater = true;
		}
		else {
			System.out.println("You are now out of the pool\n");
			for(int i = 0; i < exits.length; i++)
				exits[i] = temp[i];
			inWater = false;
		}
	}
}
