package src.fixtures;

public abstract class Fixture {
	public String name;
	public String shortDescription;
	public String longDescription;
	
	public Fixture(String name, String shortDescription, String longDescription) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}
}
