package day5Labs;

// Day 5 Lab: Static vs Instance Members
public class A {
	// static member
	public static int staticCount = 0;
	
	// instance member
	public int instanceCount= 0;
	
	public A() {
		staticCount++;
		this.instanceCount++;
	}

}
