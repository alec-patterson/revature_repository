package dogSim;

public class dogSim {
	public static void main(String[] args) {
		dog d = new dog("queenie", 13);
		d.getInfo();
	}
}

class dog {
	String name;
	int age;
	
	public dog(String n, int a) {
		name = n;
		age = a;
	}
	
	public void bark() {
		System.out.println("Woof Woof");
	}
	
	public String toString() {
		return "Name: " + name + "\nAge: " + age;
	}
	
	public void getInfo() {
		System.out.printf("%-6s %s\n%-6s %d", "Name:", name, "Age:", age);
	}
}
