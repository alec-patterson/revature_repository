package src.fixtures;

public class Lights extends Items implements Interactables{

	boolean on = false;
	String locationName;
	
	/*
	 * Allows the user to turn the lights on and off
	 */
	public Lights(String locationName) {
		super("Lights");
		this.locationName =  locationName;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void interact() {
		if(on == false) {
			System.out.println(locationName + " Lights are now on!\n");
			on = true;
		}
		else {
			System.out.println(locationName + " Lights are now off.\n");
			on = false;
		}
	}
	
}
