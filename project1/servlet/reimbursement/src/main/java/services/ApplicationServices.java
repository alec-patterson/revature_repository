package services;

import java.util.Date;
import java.util.List;

import common.util.DBUtil;
import dao.AccountDAO;
import dao.EmployeeDAO;
import dao.FinanceManagerDAO;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.PersonalInfo;
import hibernate.ReimburseRequest;
import user.Account;

public class ApplicationServices {
	Account a = null;
	
	public ApplicationServices(String email, String password, String role, String firstName, String lastName, String address, String city, String state, String zipcode, String phonenumber) {
		this.a = new Account(email, password, role, firstName, lastName, address, city, state, zipcode, phonenumber);
	}
	
	public ApplicationServices(String email) {
		a = AccountDAO.getAccount(email);
	}
	
	public static String login(String email, String password) {
		return AccountDAO.findLogin(email, password);
	}
	
	public boolean addLogin() {
		return AccountDAO.addLogin(a);
	}
	
	public boolean updatePassword(String password) {
		boolean success = false;
		success = AccountDAO.updatePassword(a, password);
		return success;
	}
	
//		put in employee Services	
//	public List<ReimburseRequest> getRequests() {
//		List<ReimburseRequest> list = eDao.viewRequests(a);
//		return list;
//	}
	
	
//		Put in employee Services
//	public void addRequest(String type, String description, float amount) {
//		eDao.addRequest(a, type, description, amount, new Date());
//	}
	
	
// 		put in finance Services
//	public List<ReimburseRequest> filterRequests(String filter) {
//		List<ReimburseRequest> list = fDao.getRequests(a, filter);
//		return list;
//	}
//	
//	public boolean approveRequest(int id) {
//		boolean success = false;
//		success = fDao.approveRequest(a, id);
//		return success;
//	}
//	
//	public boolean rejectRequest(int id) {
//		boolean success = false;
//		success = fDao.rejectRequest(a, id);
//		return success;
//	}
	
}
