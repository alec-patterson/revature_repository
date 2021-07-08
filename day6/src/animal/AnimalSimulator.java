package animal;

public class AnimalSimulator {
	public static void main(String[] args) {
		/*Animal anim = new Animal();
		anim.name = "Animal";
		anim.age = 4;
		anim.type = "Animal type";
		
		Dog d = new Dog();
		d.name = "pluto";
		d.age = 5;
		d.type = "dog";
		d.eat();
		d.bark();*/
		
		Animal anim = new Dog("pluto", 5);
		System.out.printf("%-10s %s\n%-10s %s\n%-10s %d\n", "Name: ", anim.name, "Type: ", anim.type, "Age: ", anim.age);
		((Dog)anim).bark();
	}
}
