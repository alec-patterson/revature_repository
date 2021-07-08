package animal;

public class Dog extends Animal {
	
	public Dog(String name, int age) {
		super(name, "Dog", age);
	}
	
	public void bark() {
		System.out.println("woof woof");
	}
}
