package dao;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import common.util.DBUtil;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.PersonalInfo;
import hibernate.ReimburseRequest;
import user.Account;

public class EmployeeDAO extends AccountDAO{
	
	public static EmployeeInfo getRequests(int id) {
		Session session = DBUtil.getInstance().getSession();
		LoginInfo l = session.find(LoginInfo.class, id);
		return l.getEmployeeInfo();
	}
	
	public static boolean addRequest(int id, String type, String description, String amount, Date timestamp) {
		Session session = DBUtil.getInstance().getSession();
		
		try {	
			ReimburseRequest r = new ReimburseRequest(type, description, Double.parseDouble(amount), timestamp, "pending");
			Transaction t = session.beginTransaction();
			try {
				LoginInfo l = session.find(LoginInfo.class, id);
				l.getEmployeeInfo().addReimburseRequest(r);
				r.setEmployeeInfo(l.getEmployeeInfo());
				session.save(r);
				t.commit();
				return true;
			} catch (HibernateException e) {
				if(t != null)
					t.rollback();
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
