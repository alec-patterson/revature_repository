package day5Labs;

// Day 5 Lab: Static vs Instance Members
public class Test {
	public static void main(String[] args) {
		A.staticCount = 2494;
		System.out.println("class A staticCount: " + A.staticCount);
		
		A a = new A();
		
		System.out.println(a.instanceCount);
		System.out.println(A.staticCount);
		
		A a2 = new A();
		
		System.out.println(a2.instanceCount);
		System.out.println(A.staticCount);
		
		a.instanceCount = 15;
		
		System.out.println("object a instanceCount: " + a.instanceCount);
		System.out.println("object a2 instanceCount: " + a2.instanceCount);
	}
}

/*
 * Defining something as static doesn't just allow you to reference it without
 * having to create a specific object instance. It creates a single spot in memory
 * for the static variable that can be accessed/manipulated by any object instance or
 * without an object instance.
 * 
 * Where has non-static variables are specific to an object instance
 * Every time an object is instanciated the non-static variables get their own spot
 * in memory for that instance.
 */