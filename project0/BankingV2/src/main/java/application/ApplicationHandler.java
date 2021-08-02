package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.*;

import accounts.CustomerHandler;
import accounts.EmployeeHandler;

public class ApplicationHandler {
	
	private static final Logger logger = LogManager.getLogger(ApplicationHandler.class.getName());
	private boolean running = true;
	public static Scanner scan = new Scanner(System.in);
	private ApplicationServices as = null;

	
	/*
	 * start sets up the User Interface for logging into an account 
	 * or for creating a new one
	 */
	public void start() {

		while(running) {
			logger.info("At login menu");
			System.out.println("1. Login");
			System.out.println("2. Apply For Account");
			System.out.println("3. Quit");
			System.out.println();
			System.out.print("> ");
			String input = scan.nextLine();
			ResultSet loggedIn;
			switch(input) {
				case "1": 
					if(as == null)
						as = new ApplicationServices();
					loggedIn = loggingIn();
					if(loggedIn != null) {
						try {
							logger.info("User Login Successful");
							System.out.println("Welcome " + loggedIn.getString("username") + " you have logged in.\n");
							initAccount(loggedIn);
							as = null;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				case "2":
					as = new ApplicationServices();
					createAccount();
					as = null;
					break;
				case "3":
					running = false;
					System.out.println("Good bye!");
					break;
				default:
					System.out.println("Error invalid option please try again\n");
			}
	
		}
	}
	
	
	/*
	 * loggingIn takes in a username and a password and finds out if they are correct
	 * if not an error message is printed
	 */
	public ResultSet loggingIn() {
		logger.info("Attempting to login");
		ResultSet login = null;
		System.out.print("Enter username or email: ");
		String userName = scan.nextLine();
		System.out.print("Enter password: ");
		String password = scan.nextLine();
		System.out.println();
		try {
			login = as.findLogin(userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return login;
	}
	
	
	/*
	 * createAccount prompts the user to enter all the required information for creating a new bank account
	 */
	public void createAccount() {
		logger.info("User Applying for a new Account");
		System.out.println("Thank you for choosing Project 0 Bank.");
		System.out.println("We look forward to doing business with you.");
		System.out.println("Please fill out the required information for your account.");
		System.out.println("Upon approval of an employee your account will be created.\n");
		
		System.out.print("Name: ");
		String name = ApplicationHandler.scan.nextLine();
		
		String username = as.enterUsername();
		String email = as.enterEmail();
		String password = as.enterPassword();
		
		System.out.print("Address: ");
		String address = ApplicationHandler.scan.nextLine();
		
		System.out.print("City: ");
		String city = ApplicationHandler.scan.nextLine();
		
		String state = as.getState();
		
		System.out.print("Zip Code: ");
		String zipcode = ApplicationHandler.scan.nextLine();
		
		System.out.print("Phone Number (optional): ");
		String phoneNumber = ApplicationHandler.scan.nextLine();
		
		as.addAccount(name, username, email, password, address, city, state, zipcode, phoneNumber);
		
	}
	
	
	/*
	 * initAccount will send the user to the correct User interface for their account
	 * based on if their role is either a customer or an employee/admin
	 */
	public void initAccount(ResultSet rs) throws SQLException{
		try {
			if(rs.getString("role").equals("customer")) {
				CustomerHandler ch = new CustomerHandler(rs);
				ch.init();
			} else  {
				EmployeeHandler eh = new EmployeeHandler(rs);
				eh.init();
			}
		} catch(SQLException e) {
			throw e;
		}
	}
	
}
