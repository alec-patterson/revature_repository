package animal;

public class Animal {
	public String name;
	public String type;
	public int age;
	
	public Animal(String name, String type, int age) {
		this.name = name;
		this.type = type;
		this.age = age;
	}
	
	public void eat() {
		System.out.println(name + " is eating - nom nom");
	}
}
