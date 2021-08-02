package accounts;

import java.sql.ResultSet;

import org.apache.logging.log4j.*;

import application.ApplicationHandler;

public class CustomerHandler {
	private static final Logger logger = LogManager.getLogger(CustomerHandler.class);
	
	private CustomerServices cs = null;
	private boolean loggedIn;
	
	
	/*
	 * CustomerHandler's Constructor initializes the CustomerServices object Handling user requests
	 * Boolean variable loggingIn is set to true 
	 */
	public CustomerHandler(ResultSet rs) {
		logger.info("Initializing CustomerHandler variables");
		cs = new CustomerServices(rs);
		loggedIn = true;
	}
	
	
	/*
	 * init() shows the user a menu for viewing different parts of their account information and actions that can be done such as
	 * Viewing the User's personal information
	 * Viewing the User's Bank account information
	 * Depositing money into User's bank accounts
	 * Withdrawing money from User's bank accounts
	 */
	public void init() {
		logger.info("Starting Customer Interface");
		while(loggedIn) {
			System.out.println("1. View Personal Information");
			System.out.println("2. View Bank Account Information");
			System.out.println("3. Deposit/Withdraw");
			System.out.println("4. logout");
			System.out.print("\n> ");
			String input = ApplicationHandler.scan.nextLine();
			System.out.println();
			
			switch(input) {
				case "1": 
					cs.personalInformation();
					cs.updateInfo();
					break;
				case "2":
					cs.bankAccountInformation();
					break;
				case "3":
					cs.transaction();
					break;
				case "4":
					logger.info("User logging out of account");
					System.out.println("See you again soon!\n");
					loggedIn = false;
					break;
				default:
					logger.info("Invalid option entered for Customer Interface");
					System.out.println("Error invalid option, please try again\n");
			}
		}
	}
}
