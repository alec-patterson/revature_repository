package accounts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.*;

import application.ApplicationHandler;

public class EmployeeHandler {
	private static final Logger logger = LogManager.getLogger(EmployeeHandler.class);
	
	private EmployeeServices es = null;
	private boolean loggedIn;
	private String role;
	
	
	/*
	 * EmployeeHandler constructor initializes the EmployeeServices object
	 * Sets loggedIn to true
	 * Keeps record of the User's role as an employee in the even the User is the 
	 * admin
	 */
	public EmployeeHandler(ResultSet rs) {
		logger.info("Initializing EmployeeHandler Variables");
		try {
			es = new EmployeeServices(rs);
			role = rs.getString("role");
			loggedIn = true;
		} catch (SQLException e) {
			logger.warn("Issue with ResultSet handling");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * init will call the appropriate interface depending on if the
	 * user is an employee or the admin
	 */
	public void init() {
		logger.info("Checking if Account is Employee or Admin");
		if(role.equals("employee")) {
			employeeMenu();
		}
		if(role.equals("admin")) {
			adminMenu();
		}
	}
	
	
	/*
	 * employeeMenu displays the options for the Employee such as
	 * Viewing the User's personal information
	 * Viewing the User's Bank account information
	 * Depositing money into the User's bank accounts
	 * Withdrawing money from the User's bank accounts
	 * Viewing Customer information
	 * Approving of new customer accounts
	 */
	public void employeeMenu() {
		logger.info("Starting Employee Interface");
		while(loggedIn) {
			System.out.println("1. View Personal Information");
			System.out.println("2. View Bank Account Information");
			System.out.println("3. Deposit/Withdraw");
			System.out.println("4. View Customer Information");
			System.out.println("5. Approve Accounts");
			System.out.println("6. logout");
			System.out.print("\n> ");
			String input = ApplicationHandler.scan.nextLine();
			System.out.println();
			
			switch(input) {
				case "1": 
					es.personalInformation();
					es.updateInfo();
					break;
				case "2":
					es.bankAccountInformation();
					break;
				case "3":
					es.transaction();
					break;
				case "4":
					es.viewCustomerInformation();
					break;
				case "5":
					es.approveAccount();
					break;
				case "6":
					System.out.println("See you again soon!\n");
					loggedIn = false;
					break;
				default:
					System.out.println("Error invalid option, please try again\n");
			}
		}
	}
	
	
	/*
	 * adminMenu displays the options for the Admin such as
	 * Viewing the User's personal information
	 * Viewing the User's Bank account information
	 * Depositing money into the User's bank accounts
	 * Withdrawing money from the User's bank accounts
	 * Viewing Customer information
	 * Viewing Employee information
	 * Approving of new customer accounts
	 * Hiring Employees/Creating an Employee Account
	 */
	public void adminMenu() {
		logger.info("Starting Admin Interface");
		while(loggedIn) {
			System.out.println("1. View Personal Information");
			System.out.println("2. View Bank Account Information");
			System.out.println("3. Deposit/Withdraw");
			System.out.println("4. View Customer Information");
			System.out.println("5. View Employee Information");
			System.out.println("6. Approve Accounts");
			System.out.println("7. Create Employee Account");
			System.out.println("8. logout");
			System.out.print("\n> ");
			String input = ApplicationHandler.scan.nextLine();
			System.out.println();
			
			switch(input) {
				case "1": 
					es.personalInformation();
					es.updateInfo();
					break;
				case "2":
					es.bankAccountInformation();
					break;
				case "3":
					es.transaction();
					break;
				case "4":
					es.viewCustomerInformation();
					break;
				case "5":
					es.viewEmployeeInformation();
					break;
				case "6":
					es.approveAccount();
					break;
				case "7":
					es.createEmployee();
					break;
				case "8":
					System.out.println("See you again soon!\n");
					loggedIn = false;
					break;
				default:
					logger.info("Invalid opetion for Admin Interface prompting to try again");
					System.out.println("Error invalid option, please try again\n");
			}
		}
	}
}
