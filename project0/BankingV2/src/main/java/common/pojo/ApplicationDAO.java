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

public class ApplicationDAO {
	private static final Logger logger = LogManager.getLogger(ApplicationDAO.class);
	
	public String systemName = "";

	
	/*
	 * ApplicationDAO will get the database name that will be accessed and stored into the string
	 * systemName for ease of access
	 * The name is retrieved from the config.properties file
	 */
	public ApplicationDAO() {
		logger.info("Setting up DataBase system name");
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
	 * findLogin will return the login_information table to be crossed check with the username/email 
	 */
	public ResultSet findLogin(String user) {
		try {
			logger.info("Accessing Database for Login Information");
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from \"" + systemName + "\".login_information li inner join \"" + systemName + "\".customer_accounts ca on ca.login_id = li.id where username = ? or email = ?");
			pstmt.setString(1, user);
			pstmt.setString(2, user);
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			logger.warn("Issue with connecting to database trying to access Login Information");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * addAccount takes the passed information and inserts it to the appropriate tables
	 */
	public void addAccount(String name, String username, String email, String password, String address, String city, String state, String zipcode, String phoneNumber) {
		logger.info("Accessing Database to add a new User");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into \"" + systemName + "\".login_information (username, email, password) values (?, ?, ?)");
			pstmt.setString(1,  username);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("insert into \"" + systemName + "\".customer_accounts (login_id, date_joined) values ((select id from \"" + systemName + "\".login_information li where username = ?), now())");
			pstmt.setString(1, username);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("insert into \"" + systemName + "\".customer_personal_information (account_id, name, address, city, state, zip_code, phone_number) values ((select account_id from (select * from \"" + systemName + "\".login_information li inner join \"" + systemName + "\".customer_accounts ca on li.id = ca.login_id) as info where username = ?), ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, username);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, city);
			pstmt.setString(5, state);
			pstmt.setString(6, zipcode);
			pstmt.setString(7, phoneNumber);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("insert into \"" + systemName + "\".customer_bank_information (account_id) values ((select account_id from (select * from \"" + systemName + "\".login_information li inner join \"" + systemName + "\".customer_accounts ca on li.id = ca.login_id) as info where username = ?))");
			pstmt.setString(1, username);
			pstmt.executeUpdate();
			System.out.println("\nAccount sucessfully created.\n");
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database for creating a new User");
			e.printStackTrace();
		}
	}

	
	/*
	 * checkUsername will search the login_information table for the username that was passed
	 * if it is found then we will return true otherwise false
	 */
	public boolean checkUsername(String username) {
		logger.info("Accessing Database to check if Username is Unique");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from \"" + systemName + "\".login_information li where username = ?");
			pstmt.setString(1,  username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database when checking is Username is Unique");
			e.printStackTrace();
		}
		return false;
	}
	
	
	/*
	 * checkEmail will search the login_information table for the email that was passed
	 * if it is found then we will return true otherwise false
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
	 * checkState will search the states table for the state String that was passed
	 * if it is found then we will return true otherwise false
	 */
	public boolean checkState(String state) {
		logger.info("Accessing Database to check if State is correct");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Select * from \"" + systemName + "\".states where state = ?");
			pstmt.setString(1, state.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			logger.warn("Issue with connecting to Database when checking is State correct");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
