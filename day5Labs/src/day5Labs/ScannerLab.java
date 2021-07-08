package day5Labs;
import java.util.Scanner;

// Day 5 Lab: Scanner
public class ScannerLab {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Please type in a line and hit Enter.");
		String line = scanner.nextLine();
		System.out.println("You have typed: " + line);
		scanner.close();
		
		String numbers = "1 2 3 5 7 9";
		scanner = new Scanner(numbers);
		while(scanner.hasNext()) {
			System.out.println(scanner.nextInt());
		}
		scanner.close();
	}
}

/*
 * nextLine() will grab the entire line given
 * next() will grab the next item up until it detects white space
 * nextInt() has the same functionality has next() but converts the 
 * String into an integer. 
 */