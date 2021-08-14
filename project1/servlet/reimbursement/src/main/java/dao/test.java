package dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import common.util.DBUtil;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.PersonalInfo;
import hibernate.ReimburseRequest;
import services.ApplicationServices;
import user.Account;

public class test {
	public static void main(String[] args) {
//		ApplicationServices a = new ApplicationServices();
//		
//		LoginInfo l = new LoginInfo("alexm@gmail.com", "test");
//		EmployeeInfo e = new EmployeeInfo("employee");
//		PersonalInfo p = new PersonalInfo("alex", "mcphail", "323948 some street", "las vegas", "nv", "32535", "702-345-7890");
//		l.setEmployeeInfo(e);
//		e.setLoginInfo(l);
//		e.setPersonalInfo(p);
//		p.setEmployeeInfo(e);
//		
//		a.addLogin(l, e, p);
		
		
		
		EmployeeInfo e = AccountDAO.getRequests();
		Set<ReimburseRequest> rr = e.getRequests();
		
		for(ReimburseRequest r: rr) {
			System.out.println(r);
		}
	}
}
