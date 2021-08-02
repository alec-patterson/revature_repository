package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.pojo.ApplicationDAO;

public class ApplicationServices {
	private static final Logger logger = LogManager.getLogger(ApplicationHandler.class.getName());
	
	private ApplicationDAO aDao;
	
	
	/*
	 * ApplicationServices constructor initializes the ApplicationDAO object that 
	 * will handle all database access for this class
	 */
	public ApplicationServices() {
		aDao = new ApplicationDAO();
	}
	
	
	/*
	 * findLogin will check if there is an existing account with the username or email provided
	 * if there is one found then the password will be checked
	 * if everything matches then the user will be logged in
	 * otherwise an error message will be printed
	 */
	public ResultSet findLogin(String user, String password) {
		ResultSet rs = aDao.findLogin(user);
		logger.info("Checking User Credentials");
		try {
			if(rs.next()) {
				if(password.equals(rs.getString("password"))) {
					if(rs.getBoolean("needs approval") == true) {
						System.out.println("\nError, cannot login until employee approves of your account.\n");
						return null;
					} else {
						return rs;
					}
				}
			}
		} catch(SQLException e) {
			logger.warn("Issue with ResultSet from login retrival");
			System.out.println("System Error, unable to connect.");
			e.printStackTrace();
		}
		
		logger.info("Incorrect login returning to menu");
		System.out.println("Error Username/email or password is incorrect");
		return null;
	}


	/*
	 * enterUsername will prompt the user to enter a username
	 * if the username is already taken then they will be re-prompted to
	 * enter a new username until one is entered that is not taken
	 */
	public String enterUsername() {
		logger.info("Prompting User to Enter Username");
		String username;
		while(true) {
			System.out.print("Username: ");
			username = ApplicationHandler.scan.nextLine();
			boolean isTaken = aDao.checkUsername(username);
			if(isTaken == false) {
				break;
			}
			logger.info("Entered Username is already taken redirecting to try again");
			System.out.println("Username " + username + " is taken.");
		}
		return username;
	}
	
	
	/*
	 * enterEmail will prompt the user to enter an email
	 * if the email is already taken then they will be re-prompted to
	 * enter a new email until one is entered that is not taken
	 */
	public String enterEmail() {
		logger.info("Prompting User to Enter Email");
		String email;
		while(true) {
			System.out.print("email: ");
			email = ApplicationHandler.scan.nextLine();
			boolean isTaken = aDao.checkEmail(email);
			if(isTaken == false) {
				break;
			}
			logger.info("Entered Email is already taken redirecting to try again");
			System.out.println("Email " + email + " is already in use.\n");
		}
		return email;
	}
	
	
	/*
	 * enterPassword the user will be prompted to enter the password for their account
	 * Then they will be prompted to enter the same one again. If both passwords match
	 * then it will be set to the accounts password. If they do not match then the user will
	 * have to try again
	 */
	public String enterPassword() {
		logger.info("Prompting User to Enter Password");
		String password1;
		String password2;
		while(true) {
			System.out.print("Password: ");
			password1 = ApplicationHandler.scan.nextLine();
			System.out.print("Confirm Password: ");
			password2 = ApplicationHandler.scan.nextLine();
			if(password1.equals(password2))
				break;
			logger.info("Entered Password was not correctly confirmed redirecting to try again");
			System.out.println("Error, Passwords do not match Try again\n");
		}
		return password1;
	}
	
	
	/*
	 * getState will prompt the user to enter their state they live in. There are 51 options
	 * (one being the District of Columbia) that the user must select one of. 
	 */
	public String getState() {
		logger.info("Prompting User to Enter their State");
		String state = "";
		boolean valid = false;
		while(!valid) {
			System.out.print("State (Ex. NV): ");
			state = ApplicationHandler.scan.nextLine();
			if(aDao.checkState(state))
				valid = true;
			else {
				logger.info("Entered State does not match Any State in the US redirecting to try again");
				System.out.println("Invalid state, try again.\n");
			}
		}		
		return state;
	}
	
	
	/*
	 * addAccount will take all the given information and store it into the appropriate databases the reside in
	 */
	public void addAccount(String name, String username, String email, String password, String address, String city, String state, String zipcode, String phoneNumber) {
		aDao.addAccount(name, username, email, password, address, city, state.toUpperCase(), zipcode, phoneNumber);
	}

}
