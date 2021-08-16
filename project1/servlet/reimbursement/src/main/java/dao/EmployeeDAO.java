package dao;

import org.hibernate.Transaction;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import common.util.DBUtil;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.ReimburseRequest;

public class EmployeeDAO extends AccountDAO{
	
	/*
	 * getRequests returns the employee account of the account with the associated Login Id
	 */
	public static EmployeeInfo getRequests(int id) {
		Session session = DBUtil.getInstance().getSession();
		LoginInfo l = session.find(LoginInfo.class, id);
		return l.getEmployeeInfo();
	}
	
	
	/*
	 * addRequest will create a ReimbursementRequest object and map it to the corresponding EmployeeInfo account
	 * based on the provided Login Id and then save it to the database
	 */
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
