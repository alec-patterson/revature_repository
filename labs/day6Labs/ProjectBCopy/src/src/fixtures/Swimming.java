package src.fixtures;

public class Swimming extends Items implements Interactables{
	boolean inWater = false;
	Room backyard;
	Room diningRoom;
	
	/*
	 * if the user chooses to go into the pool while their current location is in the backyard
	 * The user will not be able to go anywhere until they get out of the pool
	 */
	public Swimming(Room backyard, Room diningRoom) {
		super("Pool");
		this.backyard = backyard;
		this.diningRoom = diningRoom;
	}
	
	@Override
	public void interact() {
		if(inWater == false) {
			System.out.println("Your are swimming in the pool\n");
			backyard.exits.clear();
			inWater = true;
		}
		else {
			System.out.println("You are now out of the pool\n");
			backyard.exits.put("east", diningRoom);
			inWater = false;
		}
	}
}
