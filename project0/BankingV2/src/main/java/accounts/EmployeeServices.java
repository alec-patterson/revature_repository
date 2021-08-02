package accounts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import application.ApplicationHandler;
import common.pojo.EmployeeDAO;
import users.Employee;

public class EmployeeServices {
	private static final Logger logger = LogManager.getLogger(EmployeeServices.class);
	
	private EmployeeDAO eDao = null;
	private Employee e = null;
	
	
	/*
	 * EmployeeServices initializes the EmployeeDAO object which handles all database access for employees
	 * Initializes the employee object for storing Account information
	 */
	public EmployeeServices(ResultSet rs) {
		eDao = new EmployeeDAO();
		try {
			e = new Employee(eDao.getInfo(rs.getString("username")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Prints out personal information about the Employee
	 */
	public void personalInformation() {
		logger.info("Getting Employee personal information");
		System.out.println("Name: " + e.getName());
		System.out.println("\nAccount ID: " + e.getAccountId());
		System.out.println("Date Joined: " + e.getJoinDate());
		System.out.println("\nUsername: " + e.getUserName());
		System.out.println("Email: " + e.getEmail());
		System.out.println("Phone Number: " + e.getPhoneNumber());
		System.out.println("\nAddress: " + e.getFullAddress() + "\n");
		System.out.println("Employee ID: " + e.getEmployeeId());
		System.out.println("Employee Salary: " + e.getSalary() + "\n");
	}
	
	
	/*
	 * bankAccountInformation Prints out all information pertaining to the employee's bank account
	 * Such as bank account id, checking account, saving account, all transactions made by employee.
	 */
	public void bankAccountInformation() {
		logger.info("Getting Employee Bank Information");
		System.out.println("Bank Account Information\n");
		System.out.println("Bank Account ID: " + e.getBankId());
		System.out.printf("Checking Account Balance: $%.2f\n", e.getChecking());
		System.out.printf("Saving Account Balance: $%.2f\n\n", e.getSaving());
		ResultSet rs = eDao.getTransactions(e.getBankId());
		System.out.printf("%-15s%-30s%15s%10s\n\n", "Transaction #", "Description", "Amount", "Account");
		try {
			if(rs.next() == false)
				System.out.println("No transactions to display\n");
			else {
				do {
					System.out.printf("%-15d%-30s%15s%10s\n", rs.getInt("transaction_id"), rs.getString("description"), rs.getString("operator") + rs.getFloat("amount"), rs.getString("account"));
				} while(rs.next());
				System.out.println();
				System.out.println("Press Enter to continue");
				ApplicationHandler.scan.nextLine();
			}
		} catch (SQLException e) {
			logger.warn("Issue with ResultSet handling");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Update info will present a menu of the available information that a User can update about their account
	 * this includes:
	 * Password
	 * Email
	 * Phone Number
	 * Address
	 */
	public void updateInfo() {
		logger.info("Prompting Employee if They want to update their Personal Information");
		System.out.println("Would you like to update your information?");
		System.out.print("y/n/yes/no > ");
		System.out.println();
		String input = ApplicationHandler.scan.nextLine();
		if(input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
			System.out.println("1. Password");
			System.out.println("2. Email");
			System.out.println("3. Phone Number");
			System.out.println("4. Address");
			System.out.println("5. Back");
			System.out.print("\n> ");
			String change = ApplicationHandler.scan.nextLine();
			switch(change) {
				case "1":
					updatePassword();
					break;
				case "2": 
					updateEmail();
					break;
				case "3":
					updatePhoneNumber();
					break;
				case "4":
					updateAddress();
					break;
				case "5":
				default:
					logger.info("No information was Changed");
					System.out.println("No information updated\n");
			}
		}
	}
	
	
	/*
	 * Will prompt the user to enter their current password. If their current password is confirmed to be correct
	 * Then the User will enter a new password, then confirm it by typing it again.
	 */
	public void updatePassword() {
		System.out.print("Enter current Password: ");
		String currPassword = ApplicationHandler.scan.nextLine();
		if(currPassword.equals(e.getPassword())) {
			System.out.print("Enter new Password: ");
			String npass = ApplicationHandler.scan.nextLine();
			System.out.print("Confirm new Password: ");
			String cpass = ApplicationHandler.scan.nextLine();
			if(npass.equals(cpass)) {
				eDao.updatePassword(e.getUserName(), npass);
				System.out.println("Password Successfully updated\n");
			} else {
				logger.info("New Passwords did not match");
				System.out.println("Error, Passwords do not match\n");
			}
		} else {
			logger.info("User current password was not correct");
			System.out.println("Error incorrect password");
		}
	}
	
	
	/*
	 * User will be prompted to enter the new email
	 * If the email the entered is associated with another account
	 * an error will be printed. Otherwise the email will be updated 
	 */
	public void updateEmail() {
		System.out.print("Enter new Email: ");
		String email1 = ApplicationHandler.scan.nextLine();
		if(eDao.checkEmail(email1) != true) {
			System.out.print("Confirm new Email: ");
			String email2 = ApplicationHandler.scan.nextLine();
			if(email1.equals(email2)) {
				eDao.updateEmail(e.getUserName(), email1);
				e.setEmail(email1);
				System.out.println("Email Successfully updated\n");
				logger.info("Email Sucessfully updated");
			} else {
				System.out.println("Error emails do not match\n");
				logger.info("Email not sucessfully updated");
			}
		} else {
			logger.info("Entered Email already exists in another account");
			System.out.println("Error, Email already exists");
		}
	}
	
	
	/*
	 * User will be prompted to enter the new phone number
	 * Then to confirm it. If the two match then the phone number will 
	 * be updated
	 */
	public void updatePhoneNumber() {
		System.out.print("Enter new Phone Number: ");
		String phonenumber = ApplicationHandler.scan.nextLine();
		System.out.print("Confirm new Phone Number: ");
		String phonenumber1 = ApplicationHandler.scan.nextLine();
		if(phonenumber.equals(phonenumber1)) {
			eDao.updatePhonenumber(e.getAccountId(), phonenumber);
			e.setPhoneNumber(phonenumber);
			System.out.println("Phone Number Successfully updated\n");
		} else {
			logger.info("Entered Phone Number was not properly confirmed");
			System.out.println("Error Phone Numbers do not match\n"); 
		}
	}

	
	/*
	 * User will enter in the new street address,
	 * city, state, and zip code to be set to their new address
	 * State is specific to the two letter abbreviation of the state 
	 */
	public void updateAddress() {
		System.out.print("Enter Street Address: ");
		String address = ApplicationHandler.scan.nextLine();
		System.out.print("Enter City: ");
		String city = ApplicationHandler.scan.nextLine();
		String state = "";
		while(true) {
			System.out.print("Enter State (2 characters): ");
			state = ApplicationHandler.scan.nextLine();
			if(eDao.checkState(state) == false) {
				System.out.println("Error Invalid state, try again\n");
			}
			else
				break;
		}
		
		System.out.print("Enter Zip Code: ");
		String zipcode = ApplicationHandler.scan.nextLine();
		eDao.updateAddress(e.getAccountId(), address, city, state, zipcode);
		e.setAddress(address, city, state, zipcode);
		System.out.println("Phone Number Successfully updated\n");
	}
	
	
	/*
	 * Transaction will prompt the user to decide if they want to 
	 * deposit money into an account or withdraw money from an account
	 */
	public void transaction() {
		logger.info("Entering Transaction Menu");
		System.out.println("1. Deposit Money");
		System.out.println("2. Withdraw Money");
		System.out.println("3. Back");
		System.out.print("\n> ");
		String input = ApplicationHandler.scan.nextLine();
		System.out.println();
		
		switch(input) {
			case "1":
				deposit();
				break;
			case "2":
				withdraw();
				break;
			case "3":
				break;
			default:
				logger.info("Invalid input in Transaction Menu");
				System.out.println("Error invalid input\n");
		}
	}

	
	/*
	 * Asks what account to deposit into
	 * Asks for an amount
	 * The amount must be greater than 0
	 * Then the amount will be deposited into the account
	 */
	public void deposit() {
		logger.info("Entering Deposit");
		System.out.println("Deposit into Checking or Saving Account?");
		String account = ApplicationHandler.scan.nextLine();
		account = account.toLowerCase();
		if(account.equals("checking") || account.equals("saving")) {
			System.out.println();
			System.out.print("Enter amount to deposit: ");
			String input = ApplicationHandler.scan.nextLine();
			System.out.println();
			try {
				float amount = Float.parseFloat(input);
				if(amount >= 0) {
					boolean t = false;
					if(account.equals("checking")) {
						t = eDao.deposit(e.getBankId(), account, e.getChecking()+amount, amount);
						e.setChecking(e.getChecking() + amount);
					} else if(account.equals("saving")) {
							t = eDao.deposit(e.getBankId(), account, e.getSaving()+amount, amount);
							e.setSaving(e.getSaving() + amount);
					}
					if(t == true) {
						System.out.printf("$%.2f successfully deposited into %s account\n\n", amount, account);
					} else {
						System.out.println("Error transaction not completed\n");
					}
				} else {
					logger.info("Negative input not accepted");
					System.out.println("Error negative numbers are not accepted");
				}
			} catch (NumberFormatException e) {
				logger.info("Invalid Input in deposit");
				System.out.println("Error invalid input");
			}
		} else {
			logger.info("Account not correctly specified");
			System.out.println("Error invalid input\n");
		}
	}
	
	
	/*
	 * User must enter which account to withdraw money from
	 * User must then enter the amount
	 * The amount entered must be greater than 0
	 * and must not be more than what is currently in the 
	 * account you are trying to take money out of 
	 */
	public void withdraw() {
		logger.info("Entering Withdraw");
		System.out.println("Deposit into Checking or Saving Account?");
		String account = ApplicationHandler.scan.nextLine();
		account = account.toLowerCase();
		if(account.equals("checking") || account.equals("saving")) {
			System.out.println();
			if((account.equals("checking") && e.getChecking() == 0f) || (account.equals("saving") && e.getSaving() == 0f)) {
				System.out.println("Error, you cannot withdraw money from an empty account.\n");
			} else {
				System.out.print("Enter amount to withdraw: ");
				String input = ApplicationHandler.scan.nextLine();
				System.out.println();
				try {
					float amount = Float.parseFloat(input);
					if(amount >= 0) {
						if(account.equals("checking")) {
							if(amount < e.getChecking()) {
								float newAmount = e.getChecking()-amount;
								boolean w = eDao.withdraw(e.getBankId(), account, newAmount, amount);
								if(w == true) {
									e.setChecking(newAmount);
									System.out.printf("$%.2f successfully withdrawn from %s account\n\n", amount, account);
									logger.info("Amount successfully withdrawn from checking account");
								} else {
									logger.info("Issue with widthdrawing money from Database");
									System.out.println("Error, transaction not completed\n");
								}
							} else {
								logger.info("Atempted to widthdraw more than they have");
								System.out.println("Error, you cannot withdraw more than you have in your account\n");
							}
						}
							
						if(account.equals("saving")) {
							if(amount < e.getSaving()) {
								float newAmount = e.getSaving()-amount;
								boolean w = eDao.withdraw(e.getBankId(), account, newAmount, amount);
								if(w == true) {
									e.setSaving(newAmount);
									System.out.printf("$%.2f successfully withdrawn from %s account\n\n", amount, account);
									logger.info("Amount successfully withdrawn from saving account");
								} else {
									logger.info("Issue with widthdrawing money from Database");
									System.out.println("Error, transaction not completed\n");
								}
							} else {
								System.out.println("Error, you cannot withdraw more than you have in your account\n");
								logger.info("Atempted to widthdraw more than they have");
							}	
						}
					} else {
						System.out.println("Error negative numbers are not accepted");
						logger.info("Error, Negative numbers not accepted");
					}
				} catch (NumberFormatException e) {
					logger.info("Invalid input for widthdrawal amount");
					System.out.println("\nError invalid input\n");
				}
			}
		} else {
			logger.info("Amount successfully withdrawn from checking account");
			System.out.println("Error, invalid response\n");
		}
	}
	
	
	/*
	 * viewCustomerInformation allows the employee to look up information on 
	 * a customer based on their username
	 * This function will print out all personal information as well as 
	 * all banking information of the customer account
	 */
	public void viewCustomerInformation() {
		logger.info("Prompting Employee to enter username of customer they want to view");
		try {
			System.out.print("Enter the Username: ");
			String name = ApplicationHandler.scan.nextLine();
			ResultSet rs = eDao.getCustomerInformation(name);
			if(rs.next() == false) {
				System.out.println("Error Customer not found\n");
			} else {
				System.out.println("Customer Personal Infomation\n");
				System.out.println("Name: " + rs.getString("name"));
				System.out.println("\nAccount ID: " + rs.getInt("account_id"));
				System.out.println("Date Joined: " + rs.getString("date_joined"));
				System.out.println("\nUsername: " + rs.getString("username"));
				System.out.println("Email: " + rs.getString("email"));
				System.out.println("Phone Number: " + rs.getString("phone_number"));
				System.out.println("\nAddress: " + rs.getString("address") + " " + rs.getString("city") + ", " + rs.getString("state") + " " + rs.getString("zip_code") + "\n\n");
				
				System.out.println("Customer Bank Information\n");
				System.out.println("Bank Account ID: " +rs.getInt("bank_id"));
				System.out.printf("Checking Account Balance: $%.2f\n", rs.getFloat("checking"));
				System.out.printf("Saving Account Balance: $%.2f\n\n", rs.getFloat("saving"));
				
				System.out.println("Customer Transactions\n");
				ResultSet cBank = eDao.getTransactions(rs.getInt("bank_id"));
				System.out.printf("%-15s%-30s%15s%10s\n\n", "Transaction #", "Description", "Amount", "Account");
				while(cBank.next()) {
					System.out.printf("%-15d%-30s%15s%10s\n", cBank.getInt("transaction_id"), cBank.getString("description"), cBank.getString("operator") + cBank.getFloat("amount"), cBank.getString("account"));
				}
				System.out.println("\nPress Enter to continue");
				ApplicationHandler.scan.nextLine();
				
			}
		} catch (SQLException e) {
			logger.warn("Issue with ResultSet handling");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * viewEmplooyeeInformation is specific to the admin user
	 * It does the same functionality as viewCustomerInformation but with
	 * the employees. 
	 * This was done to prevent employees from looking at other employee information
	 */
	public void viewEmployeeInformation() {
		logger.info("Prompting Admin to enter username of employee they want to view");
		try {
			System.out.print("Enter the Username: ");
			String username = ApplicationHandler.scan.nextLine();
			ResultSet rs = eDao.getEmployeeInformation(username);
			if(rs.next() == false) {
				System.out.println("Error Customer not found\n");
			} else {
				System.out.println("Employee Personal Infomation\n");
				System.out.println("Name: " + rs.getString("name"));
				System.out.println("\nAccount ID: " + rs.getInt("account_id"));
				System.out.println("Date Joined: " + rs.getString("date_joined"));
				System.out.println("\nUsername: " + rs.getString("username"));
				System.out.println("Email: " + rs.getString("email"));
				System.out.println("Phone Number: " + rs.getString("phone_number"));
				System.out.println("\nAddress: " + rs.getString("address") + " " + rs.getString("city") + ", " + rs.getString("state") + " " + rs.getString("zip_code") + "\n\n");
				
				System.out.println("Employee Bank Information\n");
				System.out.println("Bank Account ID: " + rs.getInt("bank_id"));
				System.out.printf("Checking Account Balance: $%.2f\n", rs.getFloat("checking"));
				System.out.printf("Saving Account Balance: $%.2f\n\n", rs.getFloat("saving"));
				
				System.out.println("Employee Information");
				System.out.println("Employee ID: " + rs.getInt("employee_id"));
				System.out.println("Salary: " + rs.getFloat("salary") + "\n");
				
				System.out.println("Employee Transactions\n");
				ResultSet cBank = eDao.getTransactions(rs.getInt("bank_id"));
				System.out.printf("%-15s%-30s%15s%10s\n\n", "Transaction #", "Description", "Amount", "Account");
				while(cBank.next()) {
					System.out.printf("%-15d%-30s%15s%10s\n", cBank.getInt("transaction_id"), cBank.getString("description"), cBank.getString("operator") + cBank.getFloat("amount"), cBank.getString("account"));
				}
				System.out.println("\nPress Enter to continue");
				ApplicationHandler.scan.nextLine();
				
			}
		} catch (SQLException e) {
			logger.warn("Issue with ResultSet handling");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * approveAccount give the user the ability to see all accounts that need to be approved of
	 * And they can then choose to approve of that account
	 * from their a customer will be able to login
	 */
	public void approveAccount() {
		logger.info("Checking if accounts need to be approved");
		try {
			ResultSet rs = eDao.getNeedsApproval();
			List<String> user = new ArrayList<String>();
			List<Integer> id = new ArrayList<Integer>();
			if(rs.next() == false) {
				System.out.println("No new accounts need approval\n");
			} else {
				System.out.println("Accounts that need approval");
				do {
					System.out.printf("%-15s%-15s%-15s\n", rs.getInt("account_id"), rs.getString("name"), rs.getString("username"));
					user.add(rs.getString("username"));
					id.add(rs.getInt("account_id"));
				} while(rs.next());
				System.out.print("\nEnter The Username of the Account: ");
				String username = ApplicationHandler.scan.nextLine();
				if(user.contains(username)) {
					eDao.approveAccount(id.get(user.indexOf(username)));
				} else {
					logger.info("Incorrect username given that needs to be approved");
					System.out.println("Error, input must be one of the accounts that needs\napproval listed above\n");
				}
			}
		} catch (SQLException e) {
			logger.warn("Issue with ResultSet handling");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * createEmployee is exclusive to the admin user. It give the admin the ability to hire
	 * An employee from scratch and create all the necessary accounts
	 * or take an existing customer account and make them an employee.
	 */
	public void createEmployee() {
		logger.info("Creating a new employee");
		System.out.println("Create Brand new Employee or make from existing? (\"new\", \"existing\")");
		String option = ApplicationHandler.scan.nextLine();
		if(option.toLowerCase().equals("existing")) {
			System.out.print("Enter Username of the Account: ");
			String user = ApplicationHandler.scan.nextLine();
			boolean userExists = eDao.doesExist(user);
			if(userExists == true) {
				while(true) {
					try {
						System.out.print("Enter Salary for Employee: ");
						float salary = Float.parseFloat(ApplicationHandler.scan.nextLine());
						eDao.updateToEmployee(user, salary);
						break;
					} catch (NumberFormatException e) {
						System.out.println("Error invalid Salary try again\n");
					}
				}
			}
		} else if (option.toLowerCase().equals("new")) {
			System.out.println("Enter New Employee Information\n");
			System.out.print("Name: ");
			String name = ApplicationHandler.scan.nextLine();
			
			System.out.print("Username: ");
			String username = ApplicationHandler.scan.nextLine();
			
			System.out.print("Email: ");
			String email = ApplicationHandler.scan.nextLine();
			
			System.out.print("Password: ");
			String password = ApplicationHandler.scan.nextLine();
			
			System.out.print("Address: ");
			String address = ApplicationHandler.scan.nextLine();
			
			System.out.print("City: ");
			String city = ApplicationHandler.scan.nextLine();
			
			System.out.print("State: ");
			String state = ApplicationHandler.scan.nextLine();
			
			System.out.print("Zip Code: ");
			String zipcode = ApplicationHandler.scan.nextLine();
			
			System.out.print("Phone Number (optional): ");
			String phoneNumber = ApplicationHandler.scan.nextLine();
			
			float salary = 0;
			while(true) {
				try {
					System.out.print("Enter Salary: ");
					salary = Float.parseFloat(ApplicationHandler.scan.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("Error invalid Salary input try again\n");
				}
			}
			eDao.addEmployeeAccount(name, username, email, password, address, city, state, zipcode, phoneNumber, salary);
		} else {
			System.out.println("\nError invalid option\n");
		}
		
	}
	
}
