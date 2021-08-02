package common.pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.*;

import common.util.DBUtil;

public class EmployeeDAO extends CustomerDAO{
	private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);
	
	
	/*
	 * EmployeeDAO will invoke the parent class CustomerDAO
	 */
	public EmployeeDAO() {
		super();
	}

	
	/*
	 * getInfo will return all information pertaining to an employee account given the username
	 */
	public ResultSet getInfo(String username) throws SQLException{
		logger.info("Getting info of Employee");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select li.id, li.username, li.email, li.password, ca.account_id, ca.date_joined, ca.\"needs approval\", ca.\"role\", cpi.\"name\", cpi.address, "
					+ "cpi.city, cpi.state, cpi.zip_code , cpi.phone_number, cbi.bank_id, "
					+ "cbi.checking, cbi.saving, ei.employee_id, ei.salary "
					+ "from \"" + systemName + "\".login_information li "
					+ "full join \"" + systemName + "\".customer_accounts ca on li.id = ca.login_id "
					+ "full join \"" + systemName + "\".customer_personal_information cpi on ca.account_id = cpi.account_id "
					+ "full join \"" + systemName + "\".customer_bank_information cbi on cpi.account_id = cbi.account_id "
					+ "full join \"" + systemName + "\".employee_information ei on li.id = ei.login_id "
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
	 * will return all information (personal and banking) pertaining to the customer 
	 * with the provided username
	 */
	public ResultSet getCustomerInformation(String username) {
		logger.info("Getting Customer Infomation");
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
		} catch (SQLException e) {
			logger.warn("Issue Accessing Database");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * (Admin Only)
	 * will return all information (personal and banking) pertaining to the Employee 
	 * with the provided username
	 */
	public ResultSet getEmployeeInformation(String username) {
		logger.info("Getting Customer Information");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select li.id, li.username, li.email, li.password, ca.account_id, ca.date_joined, ca.\"needs approval\", ca.\"role\", cpi.\"name\", cpi.address, "
					+ "cpi.city, cpi.state, cpi.zip_code , cpi.phone_number, cbi.bank_id, "
					+ "cbi.checking, cbi.saving, ei.employee_id, ei.salary "
					+ "from \"" + systemName + "\".login_information li "
					+ "full join \"" + systemName + "\".customer_accounts ca on li.id = ca.login_id "
					+ "full join \"" + systemName + "\".customer_personal_information cpi on ca.account_id = cpi.account_id "
					+ "full join \"" + systemName + "\".customer_bank_information cbi on cpi.account_id = cbi.account_id "
					+ "full join \"" + systemName + "\".employee_information ei on li.id = ei.employee_id "
					+ "where li.username = ?;");
			
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			logger.warn("Issue Accessing Database");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * getNeedsApproval will return all accounts that needs to be approved before they can user their account
	 */
	public ResultSet getNeedsApproval() {
		logger.info("Getting accounts that need approval");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT li.username, ca.account_id, cpi.\"name\" FROM \"" + systemName + "\".login_information li "
					+ "INNER JOIN \"" + systemName + "\".customer_accounts ca ON ca.login_id = li.id "
					+ "INNER JOIN \"" + systemName + "\".customer_personal_information cpi ON cpi.account_id = ca.account_id "
					+ "WHERE ca.\"needs approval\" = true;");
			return rs;
		} catch (SQLException e) {
			logger.warn("Issue Accessing Database");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * approveAccount will update the "needs approval" column from customer_accounts
	 * to false for the account with the matching accountId
	 */
	public void approveAccount(int accountId) {
		logger.info("Approving Account");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE \"" + systemName + "\".customer_accounts SET \"needs approval\" = false WHERE account_id = ?;");
			pstmt.setInt(1, accountId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.warn("Issue Accessing Database");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * (Admin Only)
	 * addEmployeeAccount will create a new employee account based on the passed information 
	 */
	public void addEmployeeAccount(String name, String username, String email, String password, String address, String city, String state, String zipcode, String phoneNumber, float salary) {
		logger.info("Adding Employee Account");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into \"" + systemName + "\".login_information (username, email, password) values (?, ?, ?)");
			pstmt.setString(1,  username);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("insert into \"" + systemName + "\".customer_accounts (login_id, date_joined, role, \"needs approval\") values ((select id from \"BankingSystem\".login_information li where username = ?), now(), ?, false)");
			pstmt.setString(1, username);
			pstmt.setString(2, "employee");
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("insert into \"" + systemName + "\".customer_personal_information (account_id, name, address, city, state, zip_code, phone_number) values ((select account_id from (select * from \"BankingSystem\".login_information li inner join \"BankingSystem\".customer_accounts ca on li.id = ca.login_id) as info where username = ?), ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, username);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, city);
			pstmt.setString(5, state);
			pstmt.setString(6, zipcode);
			pstmt.setString(7, phoneNumber);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("insert into \"" + systemName + "\".customer_bank_information (account_id) values ((select account_id from \"" + systemName + "\".customer_accounts ca INNER JOIN \"" + systemName + "\".login_information li ON li.id = ca.login_id WHERE username = ?))");
			pstmt.setString(1, username);

			
			pstmt = conn.prepareStatement("insert into \"" + systemName + "\".employee_information (login_id, salary) VALUES ((SELECT id FROM \"" + systemName + "\".login_information WHERE username = ?), ?)");
			pstmt.setString(1, username);
			pstmt.setFloat(2, salary);
			pstmt.executeUpdate();
			
			System.out.println("\nEmployee Account sucessfully created.\n");
		} catch (SQLException e) {
			logger.warn("Issue Accessing Database");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * doesExist will check if the account with the passed username has employee as its role
	 */
	public boolean doesExist(String username) {
		logger.info("checking if Account is already an Employee");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM \"" + systemName + "\".login_information li "
					+ "INNER JOIN \"" + systemName + "\".customer_accounts ca ON ca.login_id = li.id "
					+ "WHERE li.username = ? AND ca.\"role\" = 'customer'");
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			logger.warn("Issue Accessing Database");
			e.printStackTrace();
		}
		return false;
	}
	
	
	/*
	 * updateToEmployee will change an existing customer account that matches the passed username to an employee account 
	 */
	public void updateToEmployee(String username, float salary) {
		logger.warn("Updating account to an Employee");
		try {
			Connection conn = DBUtil.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into \"" + systemName + "\".employee_information (login_id, salary) VALUES ((SELECT id FROM \"" + systemName + "\".login_information WHERE username = ?), ?);");
			pstmt.setString(1, username);
			pstmt.setFloat(2, salary);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("update \"" + systemName + "\".customer_accounts ca set \"role\" = 'employee' where ca.account_id = (select account_id from \"" + systemName + "\".customer_accounts ca inner join \"" + systemName + "\".login_information li on ca.login_id = li.id where li.username = ?);");
			pstmt.setString(1, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.warn("Issue Accessing Database");
			e.printStackTrace();
		}
	}
	
}
