package reimbursement;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dao.AccountDAO;
import dao.EmployeeDAO;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.ReimburseRequest;
import services.FinanceServices;


/*
 * A few Tests on functions in the dao package
 */
public class DAOTests {
	
	@Test
	public void findLoginTest() {
		String email = "alecpatt98@gmail.com";
		String password = "testing";
		LoginInfo l = AccountDAO.findLogin(email, password);
		assertEquals("Alec", l.getEmployeeInfo().getPersonalInfo().getFirstName());
	}
	
	@Test
	public void checkPasswordTest() {
		int id = 25;
		String password = "manager";
		boolean test = AccountDAO.checkPassword(id, password);
		assertEquals(true, test);
	}
	
	@Test
	public void getRequestsTest() {
		int loginId = 24;
		int employeeId = 21;
		int requestId = 1;
		EmployeeInfo e = EmployeeDAO.getRequests(loginId);
		assertEquals(e.getEmployeeId(), employeeId);
		boolean found = false;
		for(ReimburseRequest rr: e.getRequests()) {
			if(rr.getRequestId() == requestId) {
				found = true;
				break;
			}
		}
		assertEquals(true, found);
	}
	
	@Test
	public void getAllRequestsTest() {
		int id = 12;
		List<LoginInfo> l = FinanceServices.getRequests();
		boolean found = false;
		for(LoginInfo i: l) {
			for(ReimburseRequest rr: i.getEmployeeInfo().getRequests()) {
				if(rr.getRequestId() == id) {
					found = true;
					break;
				}
			}
			if(found == true) {
				break;
			}
		}
		assertEquals(true, found);
	}
}
