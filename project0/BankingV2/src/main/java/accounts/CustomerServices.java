package accounts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.*;

import application.ApplicationHandler;
import common.pojo.CustomerDAO;
import users.Customer;

public class CustomerServices {
	private static final Logger logger = LogManager.getLogger(CustomerServices.class);
	
	private CustomerDAO cDao = null;
	private Customer c = null;
	
	
	/*
	 * CustomerService constructor initializes our Customer object that will store all user information for easy access
	 * And also initialize the CustomerDAO object which will handle all DataBase access
	 */
	public CustomerServices(ResultSet rs) {
		cDao = new CustomerDAO();
		try {
			c = new Customer(cDao.getInfo(rs.getString("username")));
		} catch (SQLException e) {
			logger.warn("Error Initializing Customer in CustomerServices");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * personalInformation() will take all the information about the User
	 * pertaining to who they are and display it on screen
	 */
	public void personalInformation() {
		logger.info("Printing Customer information");
		System.out.println("Name: " + c.getName());
		System.out.println("\nAccount Id: " + c.getAccountId());
		System.out.println("Date Joined: " + c.getJoinDate());
		System.out.println("\nUsername: " + c.getUserName());
		System.out.println("Email: " + c.getEmail());
		System.out.println("Phone Number: " + c.getPhoneNumber());
		System.out.println("\nAddress: " + c.getFullAddress() + "\n");
	}
	
	
	/*
	 * bankAccountInformation() will take all the information pertaining to the Users bank accounts
	 * such as Bank Account Number, Checking Account Balance, Saving Account Balance, and all transactions that have 
	 * been made by the user.
	 */
	public void bankAccountInformation() {
		logger.info("Printing Customer Bank Infomation");
		System.out.println("Bank Account Information\n");
		System.out.println("Bank Account ID: " + c.getBankId());
		System.out.printf("Checking Account Balance: $%.2f\n", c.getChecking());
		System.out.printf("Saving Account Balance: $%.2f\n\n", c.getSaving());
		ResultSet rs = cDao.getTransactions(c.getBankId());
		System.out.printf("%-15s%-30s%15s%10s\n\n", "Transaction #", "Description", "Amount", "Account");
		try {
			while(rs.next()) {
				System.out.printf("%-15d%-30s%15s%10s\n", rs.getInt("transaction_id"), rs.getString("description"), rs.getString("operator") + rs.getFloat("amount"), rs.getString("account"));
			}
			System.out.println();
			System.out.println("Press Enter to continue");
			ApplicationHandler.scan.nextLine();
		} catch (SQLException e) {
			logger.warn("Error getting Transaction information from CustomerDAO");
			// TODO Auto-generated catch block
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
		logger.info("Prompting User if They want to update their Personal Information");
		System.out.println("Would you like to update your information?");
		System.out.print("y/n/yes/no > ");
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
		if(currPassword.equals(c.getPassword())) {
			System.out.print("Enter new Password: ");
			String npass = ApplicationHandler.scan.nextLine();
			System.out.print("Confirm new Password: ");
			String cpass = ApplicationHandler.scan.nextLine();
			if(npass.equals(cpass)) {
				cDao.updatePassword(c.getUserName(), npass);
				c.setPassword(npass);
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
		if(cDao.checkEmail(email1) != true) {
			System.out.print("Confirm new Email: ");
			String email2 = ApplicationHandler.scan.nextLine();
			if(email1.equals(email2)) {
				cDao.updateEmail(c.getUserName(), email1);
				c.setEmail(email1);
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
			cDao.updatePhonenumber(c.getAccountId(), phonenumber);
			c.setPhoneNumber(phonenumber);
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
			if(cDao.checkState(state) == false) {
				System.out.println("Error Invalid state, try again\n");
			} else
				break;
		}
		
		System.out.print("Enter Zip Code: ");
		String zipcode = ApplicationHandler.scan.nextLine();
		cDao.updateAddress(c.getAccountId(), address, city, state, zipcode);
		c.setAddress(address, city, state, zipcode);
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
				if(amount > 0) {
					boolean t = false;
					if(account.equals("checking")) {
						t = cDao.deposit(c.getBankId(), account, c.getChecking()+amount, amount);
						c.setChecking(c.getChecking() + amount);
					} else if(account.equals("saving")) {
							t = cDao.deposit(c.getBankId(), account, c.getSaving()+amount, amount);
							c.setSaving(c.getSaving() + amount);
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
			if((account.equals("checking") && c.getChecking() == 0f) || (account.equals("saving") && c.getSaving() == 0f)) {
				System.out.println("Error, you cannot withdraw money from an empty account.\n");
			} else {
				System.out.print("Enter amount to withdraw: ");
				String input = ApplicationHandler.scan.nextLine();
				System.out.println();
				try {
					float amount = Float.parseFloat(input);
					if(amount >= 0) {
						if(account.equals("checking")) {
							if(amount < c.getChecking()) {
								float newAmount = c.getChecking()-amount;
								boolean w = cDao.withdraw(c.getBankId(), account, newAmount, amount);
								if(w == true) {
									c.setChecking(newAmount);
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
							if(amount < c.getSaving()) {
								float newAmount = c.getSaving()-amount;
								boolean w = cDao.withdraw(c.getBankId(), account, newAmount, amount);
								if(w == true) {
									c.setSaving(newAmount);
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
}
