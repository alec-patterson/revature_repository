package fixtures;

public class Sinks extends Items implements Interactables{
	String locationName;
	
	/*
	 * allows the user to turn the sink on and off
	 */
	public Sinks(String locationName) {
		super("Sink");
		this.locationName = locationName;
		// TODO Auto-generated constructor stub
	}

	boolean on = false;
	
	@Override
	public void interact() {
		if(on == false) {
			System.out.println("Running water from the " + locationName + " sink\n");
			on = true;
		}
		else {
			System.out.println(locationName + " sink is now turned off\n");
			on = false;
		}
			
	}
}
