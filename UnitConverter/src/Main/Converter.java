package Main;
import java.util.Scanner;

public class Converter {
	private static Scanner ms = new Scanner(System.in);
	
	// takes in user input and converts Cups to Teaspoons
	public static void ConvertCupToTeaspoons() {
		System.out.print("Enter number of Cups: ");
		int cup = Integer.parseInt(ms.nextLine());
		int teaspoon = cup * 48;
		System.out.println("\n" + cup + " cups is equal to " + teaspoon + " teaspoons.\n");
	}
	
	
	// takes in user input and converts Miles to Kilometers
	public static void ConvertMilesToTeaspoons() {
		System.out.print("Enter number of Miles: ");
		int miles = Integer.parseInt(ms.nextLine());
		double kilometer = miles * 1.60934;
		System.out.println("\n" + miles + " miles is equal to " + kilometer + " kilometers.\n");
	}
	
	
	// takes in user input and converts US gallon to Imperial gallon
	public static void ConvertUsgToIg() {
		System.out.print("Enter number of US gallons: ");
		int usg = Integer.parseInt(ms.nextLine());
		double ig = usg * 0.832674;
		System.out.println("\n" + usg + " US gallons is equal to " + ig + " Imperial gallons.\n");
	}
	
	
	public static void main(String[] args) {
		
		// menu selection will be take from user input
		int menuSelection = 0;
		
		while(menuSelection != 4) {
			System.out.println("Please Select an option:");
			System.out.println("1. Cups to Teaspoons");
			System.out.println("2. Miles to Kilometers");
			System.out.println("3. US Gallons to Imperial Gallons");
			System.out.println("4. Quit");
			System.out.print("\n\n> ");
			menuSelection = Integer.parseInt(ms.nextLine());
			
			// selects the correct option based on the input
			switch(menuSelection) {
				case 1: 
					ConvertCupToTeaspoons();
					break;
				case 2:
					ConvertMilesToTeaspoons();
					break;
				case 3:
					ConvertUsgToIg();
					break;
				case 4:
					break;
				default:
					System.out.println("\nInvalue option, please try again.\n");
			}
		}
		ms.close();
	}
	
}
