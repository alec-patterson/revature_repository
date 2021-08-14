package bankTest;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import common.pojo.*;

public class DAOTests {

	@Test
	public void testFindLogin() {
		// Testing ApplicationDAO findLogin(String, String)
		ApplicationDAO aDao = new ApplicationDAO();
		String username = "madcomrade98";
		String password = "admin";
		ResultSet rs = aDao.findLogin(username, password);
		assertEquals(rs.next(), true);
		assertEquals(rs.getString("username"), username);
	}
	
	@Test
	public void testCheckUsername() {
		// Testing ApplicationDAO checkUsername(String)
		ApplicationDAO aDao = new ApplicationDAO();
		String username = "madcomrade98";
		ResultSet rs = aDao.checkUsername(username);
		assertEquals(rs.next(), true);
		assertEquals(rs.getString("username"), username);
	}
	
	@Test
	public void testCheckEmail() {
		// Testing ApplicationDAO checkEmail(String)
		ApplicationDAO aDao = new ApplicationDAO();
		String email = "alec.patterson@revature.net";
		ResultSet rs = aDao.checkEmail(email);
		assertEquals(rs.next(), true);
		assertEquals(rs.getString("email"), email);
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
	public void testCheckPassword() {
		// Testing CustomerDAO checkPassword(String, String)
		CustomerDAO cDao = new CustomerDAO();
		String username = "madcomrade98";
		String password = "admin1";
		boolean result = cDao.testCheckPassword(username, password);
		assertEquals(result, false);
	}
	
	@Test
	public void testGetTransactions() {
		// Testing CustomerDAO getTransactions(int)
		CustomerDAO cDao = new CustomerDAO();
		int id = 1;
		ResultSet rs = cDao.getTransactions(id);
		assertEquals(rs.next(), true);
	}
	
	@Test
	public void testCustomerGetInfo() {
		// Testing CustomerDAO getInfo(String)
		CustomerDAO cDao = new CustomerDAO();
		String username = "admin";
		ResultSet rs = cDao.getInfo(username);
		assertEquals(rs.next(), false);
	}
	
	@Test
	public void testDoesExist() {
		// Testing EmployeeDAO doesExist(String)
		EmployeeDAO eDao = new EmployeeDAO();
		String username = "AllMight";
		ResultSet rs = eDao.doesExist(username);
		assertEquals(rs.next(), false);
	}
	
	@Test
	public void testGetCustomerInformation() {
		// Testing EmployeeDAO getCustomerInformation(String)
		EmployeeDAO eDao = new EmployeeDAO();
		String username = "alexm";
		ResultSet rs = eDao.getCustomerInformation(username);
		assertEquals(rs.next(), true);
		assertEquals(rs.getString("username"), username);
	}
	
	@Test
	public void testEmployeeGetInfo() {
		// Testing EmployeeDAO getInfo(String)
		EmployeeDAO eDao = new EmployeeDAO();
		String username = "rj";
		ResultSet rs = eDao.getEmployeeInformation(username);
		assertEquals(rs.next(), true);
		assertEquals(rs.getString("username"), username);
	}
	
}
