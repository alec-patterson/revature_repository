package common.pojo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.*;

import common.util.Constants;
import common.util.DBUtil;

public class CustomerDAO {
	private static final Logger logger = LogManager.getLogger(CustomerDAO.class);
	
	protected String systemName = "";
	
	
	/*
	 * CustomerDAO will get the database name that will be accessed and stored into the string
	 * systemName for ease of access
	 * The name is retrieved from the config.properties file
	 */
	public CustomerDAO() {
		String configFilePath = System.getProperty(Constants.CONFIG_FILE);
		FileInputStream fis;
		try {
			fis = new FileInputStream(configFilePath);
			Properties prop = new Properties();
			prop.load(fis);
			this.systemName = (String)prop.getProperty(Constants.DB_name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * getInfo will return all information pertaining to a customers account given the username
	 */
	public ResultSet getInfo(String username) throws SQLException{
		logger.info("Getting User Account Information");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select li.id, li.username, li.email, li.password, ca.account_id, ca.date_joined, ca.\"needs approval\", ca.\"role\", cpi.\"name\", cpi.address, "
					+ "cpi.city, cpi.state, cpi.zip_code , cpi.phone_number, cbi.bank_id, "
					+ "cbi.checking, cbi.saving "
					+ "from \"" + systemName + "\".login_information li "
					+ "full join \"" + systemName + "\".customer_accounts ca on li.id = ca.login_id "
					+ "full join \"" + systemName + "\".customer_personal_information cpi on ca.account_id = cpi.account_id "
					+ "full join \"" + systemName + "\".customer_bank_information cbi on cpi.account_id = cbi.account_id "
					+ "where li.username = ?;");
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch(SQLException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 *  checkUsername will go get the search for the username in the login_information
	 *  If there is an account with the username taken it will return true otherwise false
	 */
	public boolean checkUsername(String username) {
		logger.info("Accessing Database to checking Username is Unique");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from \"" + systemName + "\".login_information li where username = ?");
			pstmt.setString(1,  username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database");
			e.printStackTrace();
		}
		return false;
	}
	
	
	/*
	 * checkEmail will search through the login information table for the passed in email.
	 * if it is found then it will return true otherwise it will return false
	 */
	public boolean checkEmail(String email) {
		logger.info("Accessing Database to check if Email is Unique");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from \"" + systemName + "\".login_information li where email = ?");
			pstmt.setString(1,  email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database when checking is Email is Unique");
			e.printStackTrace();
		}
		return false;
	}
	
	
	/*
	 * updatePassword will check the accounts password that has the passed in username
	 */
	public void updatePassword(String username, String password) {
		logger.info("Accessing Database to Update Password");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".login_information SET password = ? WHERE username = ?");
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * updateEmail will check the accounts email that has the passed in username
	 */
	public void updateEmail(String username, String email) {
		logger.info("Accessing Database to Update Email");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".login_information SET email = ? WHERE username = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * updatePhonenumber will check the accounts phone number that has the passed in username
	 */
	public void updatePhonenumber(int account_id, String phoneNumber) {
		logger.info("Accessing Database to Update Phone Number");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".customer_personal_information SET phone_number = ? WHERE account_id = ?");
			pstmt.setString(1, phoneNumber);
			pstmt.setInt(2, account_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * updateAddress will update the address, city, state, and zipcode of then account with the account_id passed in
	 */
	public void updateAddress(int account_id, String address, String city, String state, String zipcode) {
		logger.info("Accessing Database to Update Address");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".customer_personal_information SET address = ? WHERE account_id = ?");
			pstmt.setString(1, address);
			pstmt.setInt(2, account_id);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".customer_personal_information SET city = ? WHERE account_id = ?");
			pstmt.setString(1, city);
			pstmt.setInt(2, account_id);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".customer_personal_information SET state = ? WHERE account_id = ?");
			pstmt.setString(1, state);
			pstmt.setInt(2, account_id);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".customer_personal_information SET zip_code = ? WHERE account_id = ?");
			pstmt.setString(1, zipcode);
			pstmt.setInt(2, account_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * checkState will check the states table to make sure the entered state is one of the 51 options
	 */
	public boolean checkState(String state) {
		logger.info("Accessing Database to confirm correct state");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select * from \"" + systemName + "\".states where state = ?");
			pstmt.setString(1, state.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	/*
	 * deposit will take the new account amount and update the table with the corresponding bank account id
	 * Then a transaction will be inserted into the transaction table
	 */
	public boolean deposit(int bankId, String account, float newAmount, float amount) {
		logger.info("Accessing Database to Deposit money into User Account");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".customer_bank_information SET " + account + " = ? where bank_id = ?");
			pstmt.setFloat(1, newAmount);
			pstmt.setInt(2, bankId);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("INSERT INTO \"" + systemName + "\".transactions (bank_account_id, description, account, operator, amount) values (?, ?, ?, ?, ?)");
			pstmt.setInt(1, bankId);
			pstmt.setString(2, "Deposit into " + account);
			pstmt.setString(3, account);
			pstmt.setString(4, "+");
			pstmt.setFloat(5, amount);
			pstmt.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			logger.warn("Issue with connecting to Database");
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * withdraw will take the new account amount and update the table with the corresponding bank account id
	 * Then a transaction will be inserted into the transaction table
	 */
	public boolean withdraw(int bankId, String account, float newAmount, float amount) {
		logger.info("Accessing Database to Withdraw money from User Account");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".customer_bank_information SET " + account + " = ? where bank_id = ?");
			pstmt.setFloat(1, newAmount);
			pstmt.setInt(2, bankId);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("INSERT INTO \"" + systemName + "\".transactions (bank_account_id, description, account, operator, amount) values (?, ?, ?, ?, ?)");
			pstmt.setInt(1, bankId);
			pstmt.setString(2, "Withdraw from " + account);
			pstmt.setString(3, account);
			pstmt.setString(4, "-");
			pstmt.setFloat(5, amount);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	
	/*
	 * getTransactions will return all transactions made by an account using the bank account id
	 */
	public ResultSet getTransactions(int id) {
		logger.info("Accessing Database to get User Transactions");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM \"" + systemName + "\".transactions WHERE bank_account_id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch(SQLException e) {
			logger.warn("Issue with connecting to Database");
			e.printStackTrace();
		}
		return null;
	}
}
