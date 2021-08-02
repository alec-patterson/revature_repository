package bankTest;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import common.pojo.*;

public class DAOTests {

	@Test
	public void testFindLogin() {
		// Testing ApplicationDAO findLogin(String, String)
		ApplicationDAO aDao = new ApplicationDAO();
		String username = "madcomrade98";
		ResultSet rs = aDao.findLogin(username);
		try {
			assertEquals(rs.next(), true);
			assertEquals(rs.getString("username"), username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testCheckUsername() {
		// Testing ApplicationDAO checkUsername(String)
		ApplicationDAO aDao = new ApplicationDAO();
		String username = "madcomrade98";
		boolean result = aDao.checkUsername(username);
		assertEquals(result, true);
	}
	
	@Test
	public void testCheckEmail() {
		// Testing ApplicationDAO checkEmail(String)
		ApplicationDAO aDao = new ApplicationDAO();
		String email = "alec.patterson@revature.net";
		boolean result = aDao.checkEmail(email);
		assertEquals(result, true);
	}
	
	@Test
	public void testCheckState() {
		// Testing ApplicationDAO checkState(String)
		ApplicationDAO aDao = new ApplicationDAO();
		String state = "NV";
		boolean result = aDao.checkState(state);
		assertEquals(result, true);
	}
	
	@Test
	public void testgetNeedsApproval() {
		// Testing EmployeeDAO getEmployeeInformation(String)
		EmployeeDAO eDao = new EmployeeDAO();
		ResultSet rs = eDao.getNeedsApproval();
		try {
			if(rs.next())
				assertEquals(rs.getBoolean("needs approval"), true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTransactions() {
		// Testing CustomerDAO getTransactions(int)
		CustomerDAO cDao = new CustomerDAO();
		int id = 1;
		ResultSet rs = cDao.getTransactions(id);
		try {
			assertEquals(rs.next(), true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCustomerGetInfo() {
		// Testing CustomerDAO getInfo(String)
		CustomerDAO cDao = new CustomerDAO();
		String username = "admin";
		ResultSet rs;
		try {
			rs = cDao.getInfo(username);
			assertEquals(rs.next(), false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDoesExist() {
		// Testing EmployeeDAO doesExist(String)
		EmployeeDAO eDao = new EmployeeDAO();
		String username = "AllMight";
		boolean result = eDao.doesExist(username);
		assertEquals(result, false);
	}
	
	@Test
	public void testGetCustomerInformation() {
		// Testing EmployeeDAO getCustomerInformation(String)
		EmployeeDAO eDao = new EmployeeDAO();
		String username = "alexm";
		ResultSet rs = eDao.getCustomerInformation(username);
		try {
			assertEquals(rs.next(), true);
			assertEquals(rs.getString("username"), username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmployeeGetInfo() {
		// Testing EmployeeDAO getInfo(String)
		EmployeeDAO eDao = new EmployeeDAO();
		String username = "bronzebucko";
		ResultSet rs = eDao.getEmployeeInformation(username);
		try {
			assertEquals(rs.next(), true);
			assertEquals(rs.getString("username"), username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
