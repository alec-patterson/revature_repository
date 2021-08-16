package day5Labs;

// Day 5 Lab: Writing Composite Classes
public class ClassB {
	ClassA classA = new ClassA();
	
	public static void main(String[] args) {
		ClassB b = new ClassB();
		b.classA.setName("Taylor");
		
		System.out.println(b.classA.getName());
	}
}
