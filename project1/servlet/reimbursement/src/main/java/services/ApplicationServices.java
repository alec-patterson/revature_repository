package services;

import dao.AccountDAO;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.PersonalInfo;

public class ApplicationServices {
	
	/*
	 * getAccount calls AccountDAO.getAccount(int) 
	 * returns the LoginInfo object with the associated Login Id
	 */
	public static LoginInfo getAccount(int id) {
		return AccountDAO.getAccount(id);
	}
	
	
	/*
	 * addLogin creates the LoginInfo, EmployeeInfo, and PersonalInfo objects with the given Parameters
	 * Then they three objects are linked via One-To-One relationships
	 * Then they are passed to AccountDAO.addLogin(LoginInfo, EmployeeInfo, PersonalInfo) to be stored in the database
	 */
	public static LoginInfo addLogin(String email, String password, String role, String firstName, String lastName, String address, String city, String state, String zipcode, String phonenumber) {
		LoginInfo l = new LoginInfo(email, password);
		EmployeeInfo e = new EmployeeInfo(role);
		PersonalInfo p = new PersonalInfo(firstName, lastName, address, city, state, zipcode, phonenumber);
		l.setEmployeeInfo(e);
		e.setLoginInfo(l);
		e.setPersonalInfo(p);
		p.setEmployeeInfo(e);
		return AccountDAO.addLogin(l, e, p);
	}
	
	
	/*
	 * login takes in an email and password string and returns the LoginInfo object
	 * that is returned from calling AccountDAO.findLogin(String, String)
	 */
	public static LoginInfo login(String email, String password) {
		return AccountDAO.findLogin(email, password);
	}

	
	/*
	 * updatePassword will check if the user entered the correct current password
	 * If so then the user's password will be updated with the new password
	 */
	public static boolean updatePassword(int id, String cPassword, String nPassword) {
		if(AccountDAO.checkPassword(id, cPassword)) {
			return AccountDAO.updatePassword(id, nPassword);
		}
		return false;
	}
	
}
