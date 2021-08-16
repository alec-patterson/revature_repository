package fixtures;

public class Opener extends Items implements Interactables{	
	boolean open = false;
	public Room garage;
	public Room driveway;
	
	
	/*
	 * Establishes a connection with the garage and driveway
	 * 
	 * This way the driveway is able to be inserted into the garages exits
	 * when the garage door is open
	 */
	public Opener(Room garage, Room driveway) {
		super("GarageOpener");
		this.garage = garage;
		this.driveway = driveway;
	}
	
	@Override
	public void interact() {
		if(open == false) {
			System.out.println("The garage door is now open!\n");
			Room[] exit = garage.getExits();
			exit[2] = driveway;
			open = true;
		} else {
			System.out.println("The garage door is now closed!\n");
			Room[] exit = garage.getExits();
			exit[2] = null;
			open = false;
		}
	}
}
