package fixtures;

public class Items {
	public String name;
	
	/*
	 * Items class is the parent class to all Interactable classes.
	 * Each interactable class will be saved to this items array in Room.java
	 */
	public Items(String name) {
		this.name = name;
	}
}
