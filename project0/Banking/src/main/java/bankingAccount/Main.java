package bankingAccount;

import java.util.Scanner;

public class Main {
	public static boolean run = true;
	public static Scanner scan = new Scanner(System.in);
	private static AccountManager manager;
	
	public static void login() {
		System.out.println();
		System.out.print("Enter Username or Email: ");
		String id = scan.nextLine();
		System.out.print("");
		
	}
	
	public static void main(String[] args) {
		while(run) {
			System.out.println("1. Login");
			System.out.println("2. Create an Account");
			System.out.println();
			System.out.print("> ");
			try {
				String n = scan.nextLine();
				int option = Integer.parseInt(n);
				System.out.println();
				switch(option) {
					case 1:
						login();
						break;
					case 2:
						applyForAccount();
						break;
				}
			} catch(NumberFormatException e) {
				System.out.println("Error, Wrong input try again.\n");
			}
		}
	}

	private static void applyForAccount() {
		// TODO Auto-generated method stub
		
	}
}
