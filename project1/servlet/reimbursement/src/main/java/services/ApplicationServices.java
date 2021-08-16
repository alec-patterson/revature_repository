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
	
	public static LoginInfo getAccount(int id) {
		return AccountDAO.getAccount(id);
	}
	
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
	
	public static LoginInfo login(String email, String password) {
		return AccountDAO.findLogin(email, password);
	}

	public static boolean updatePassword(int id, String cPassword, String nPassword) {
		if(AccountDAO.checkPassword(id, cPassword)) {
			return AccountDAO.updatePassword(id, nPassword);
		}
		return false;
	}
	
}
