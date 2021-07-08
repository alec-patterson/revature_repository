package day6;

import java.util.Scanner;

public class ClosingScanner {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Give me input: ");
		String value = scan.next();
		System.out.println(value);
		/*
		 * when closing a scanner, we do not close the 
		 * scanner object, we close the resource associated 
		 * with the scanner, in this case, we would close
		 * the static inputStream of our System class
		 */
		System.out.print("Give me number: ");
		int number = scan.nextInt();
		System.out.println(number);
		
		scan.close(); // make sure to close the scanner at the end
	}
}