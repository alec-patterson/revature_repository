package day4Labs;

public class OverloadedMethods {

	public static void main(String[] args) {
		//create instance here
		OverloadedMethods om = new OverloadedMethods();
		om.print();
		
		om.print("Second print method");
		om.print("Second", "Third");
		
	}
	
	//overload methods here
	public void print() {
		System.out.println("original print method.");
	}
	
	public void print(String str) {
		System.out.println(str);
	}

	public void print(String str1, String str2) {
		System.out.println(str1 + " " + str2);
	}
}
