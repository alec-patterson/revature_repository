// Alec Patterson	Email: alec.patterson@revature.net
package Main;

import java.util.Scanner;

public class Converter2 {
	private static Scanner ms = new Scanner(System.in);
	/*
	 * This function handles four different conversions depending on the selected option
	 * from volumeConversion
	 * 
	 * The conversion selected is based on the string passed
	 * This function specifically handles teaspoon-tablespoon and teaspoon-cup conversion
	 */
	public static void teaConvert(String conversion) {
		if(conversion == "teaTable") {
			System.out.print("Enter number of Teaspoons: ");
			double teaspoon = Double.parseDouble(ms.nextLine());
			double tablespoon = teaspoon * 0.333333;
			System.out.println("\n" + teaspoon + " teaspoons is equal to " + tablespoon + " tablespoons.\n");
		} else if(conversion == "tableTea") {
			System.out.print("Enter number of Tablespoons: ");
			double tablespoon = Double.parseDouble(ms.nextLine());
			double teaspoon = tablespoon / 0.333333;
			System.out.println("\n" + tablespoon + " tablespoons is equal to " + teaspoon + " teaspoons.\n");
		} else if(conversion == "teaCup") {
			System.out.print("Enter number of Teaspoons: ");
			double teaspoon = Double.parseDouble(ms.nextLine());
			double cup = teaspoon / 48;
			System.out.println("\n" + teaspoon + " teaspoons is equal to " + cup + " cups.\n");
		} else if(conversion == "cupTea") {
			System.out.print("Enter number of Cups: ");
			double cup = Double.parseDouble(ms.nextLine());
			double teaspoon = cup * 48;
			System.out.println("\n" + cup + " cups is equal to " + teaspoon + " teaspoons.\n");
		}
	}
	
	
	/*
	 * This function handles two different conversions depending on the selected option
	 * from volumeConversion
	 * 
	 * The conversion selected is based on the string passed
	 * This function specifically handles US gallon-Imperial gallon
	 */
	public static void gallonConvert(String conversion) {
		if(conversion == "usImperial") {
			System.out.print("Enter number of US gallons: ");
			double usg = Double.parseDouble(ms.nextLine());
			double ig = usg * 0.832674;
			System.out.println("\n" + usg + " US gallons is equal to " + ig + " Imperial gallons.\n");
		} else {
			System.out.print("Enter number of Imperial gallons: ");
			double ig = Double.parseDouble(ms.nextLine());
			double usg = ig / 0.832674;
			System.out.println("\n" + ig + " Imperial gallons is equal to " + usg + " US gallons.\n");
		}
		
	}
	
	
	/*
	 * Second menu that will ask you why type of volume conversion
	 * you want to do. 
	 */
	public static void volumeConversion() {
		int vcSelection = 0;
		System.out.println("Please Select a Volume Conversion option:");
		System.out.println("1. Teaspoons to Tablespoons");
		System.out.println("2. Tablespoons to Teaspoons");
		System.out.println("3. Teaspoons to Cups");
		System.out.println("4. Cups to Teaspoons");
		System.out.println("5. US Gallons to Imperial Gallons");
		System.out.println("6. Imperial Gallons to US Gallons");
		System.out.println("7. Back");
		System.out.print("\n> ");
		
		vcSelection = Integer.parseInt(ms.nextLine());
		System.out.println();
		
		
		// selects the correct option based on user input
		switch(vcSelection) {
			case 1:
				teaConvert("teaTable");
				break;
			case 2:
				teaConvert("tableTea");
				break;
			case 3:
				teaConvert("teaCup");
				break;
			case 4:
				teaConvert("cupTea");
				break;
			case 5:
				gallonConvert("usImperial");
				break;
			case 6:
				gallonConvert("imperialUS");
				break;
			case 7:
				break;
			default:
				System.out.println("Invalid input, back to main menu\n");
		}
	}
	
	
	/*
	 * This function handles four different conversions depending on the selected option
	 * from volumeConversion
	 * 
	 * The conversion selected is based on the string passed
	 * This function specifically handles feet-meter and mile-kilometer conversion
	 */
	public static void disConvert(String conversion) {
		if(conversion == "feetMeter") {
			System.out.print("Enter number of Feet: ");
			double feet = Double.parseDouble(ms.nextLine());
			double meter = feet * 0.3048;
			System.out.println("\n" + feet + " feet is equal to " + meter + " meters.\n");
		} else if(conversion == "meterFeet") {
			System.out.print("Enter number of Meters: ");
			double meter = Double.parseDouble(ms.nextLine());
			double feet = meter / 0.3048;
			System.out.println("\n" + meter + " meters is equal to " + feet + " feet.\n");
		} else if(conversion == "mileKilo") {
			System.out.print("Enter number of Miles: ");
			double mile = Double.parseDouble(ms.nextLine());
			double kilometer = mile * 1.60934;
			System.out.println("\n" + mile + " miles is equal to " + kilometer + " kilometers.\n");
		} else if(conversion == "kiloMile") {
			System.out.print("Enter number of Kilometers: ");
			double kilometer = Double.parseDouble(ms.nextLine());
			double mile = kilometer / 1.60934;
			System.out.println("\n" + kilometer + " kilometers is equal to " + mile + " miles.\n");
		}
	}
	
	
	/*
	 * Second menu that will ask you why type of Distance conversion
	 * you want to do. 
	 */
	public static void distanceConversion() {
		int dcSelection = 0;
		System.out.println("Please Select a distance Conversion option:");
		System.out.println("1. Feet to Meter");
		System.out.println("2. Meters to feet");
		System.out.println("3. Miles to Kilometers");
		System.out.println("4. Kilometers to Miles");
		System.out.println("5. Back");
		System.out.print("\n> ");
		
		dcSelection = Integer.parseInt(ms.nextLine());
		System.out.println();	
		
		switch(dcSelection) {
			case 1:
				disConvert("feetMeter");
				break;
			case 2:
				disConvert("meterFeet");
				break;
			case 3:
				disConvert("mileKilo");
				break;
			case 4:
				disConvert("kiloMile");
				break;
			case 5:
				break;
			default:
				System.out.println("Invalid input, back to main menu\n");
		}
	}
	
	
	public static void main(String[] args) {
		
		// menu selection taken by user input
		int menuSelection = 0;
	
		while(menuSelection != 3) {
			System.out.println("Please Select a Conversion option:");
			System.out.println("1. Volume Conversions");
			System.out.println("2. Distance Conversions");
			System.out.println("3. Quit");
			System.out.print("\n> ");
			
			menuSelection = Integer.parseInt(ms.nextLine());
			System.out.println();
			
			//	selects the correct option based on the input
			switch(menuSelection) {
				case 1: 
					volumeConversion();
					break;
				case 2:
					distanceConversion();
					break;
				case 3:
					break;
				default:
					System.out.println("\nInvalue option, please try again.\n");
			}
		}
		ms.close();
	}
}

