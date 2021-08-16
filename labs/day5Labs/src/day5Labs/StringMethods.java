package day5Labs;

// Day 5 Lab: Common String Methods
public class StringMethods {
	public static void main(String[] args) {
		String str = "Hello";
		
		//checks if the contents the str string is equivalent to the string "Hello"
		System.out.println(str.equals("Hello"));
		
		//use the length() method
		System.out.println(str.length());
		
		//use the indexOf() method
		System.out.println(str.indexOf('H'));
	}
}
