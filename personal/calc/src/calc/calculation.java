package calc;
import java.util.Scanner;

public class calculation {
	public static int add(int a, int b) {
		return a + b;
	}
	
	public static int div(int a, int b) {
		return a / b;
	}
	
	public static int sub(int a, int b) {
		return a - b;
	}
	
	public static int mult(int a, int b) {
		return a * b;
	}
	
	public static void main(String[] args) {
		Scanner i = new Scanner(System.in);
		System.out.print("Input for a: ");
		int a = Integer.parseInt(i.nextLine());
		System.out.print("Input for b: ");
		int b = Integer.parseInt(i.nextLine());
		
		System.out.println(a + " + " + b + " = " + calculation.add(a, b));
		System.out.println(a + " - " + b + " = " + calculation.sub(a, b));
		System.out.println(a + " / " + b + " = " + calculation.div(a, b));
		System.out.println(a + " * " + b + " = " + calculation.mult(a, b));
	}
}
