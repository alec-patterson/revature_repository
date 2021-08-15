package dao;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import common.util.DBUtil;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.PersonalInfo;
import hibernate.ReimburseRequest;
import user.Account;

public class EmployeeDAO extends AccountDAO{
	public static List<ReimburseRequest> viewRequests(String email) {
		Session session = a.getSession();
		Query query = session.createQuery("FROM hibernate.ReimburseRequest where employee_id = :Id");
		query.setInteger("Id", a.getEmployeeInfo().getEmployeeId());
		List<ReimburseRequest> list = query.list();
		return list;
	}
	
	public static void addRequest(Account a, String type, String description, float amount, Date timestamp) {
		Session session = a.getSession();
		ReimburseRequest r = new ReimburseRequest(type, description, amount, timestamp, "pending");
		Transaction t = session.beginTransaction();
		try {
			r.setEmployeeInfo(a.getEmployeeInfo());
			a.getEmployeeInfo().addReimburseRequest(r);
			session.save(r);
			t.commit();
		} catch (HibernateException e) {
			if(t != null)
				t.rollback();
			throw e;
		}
	}
}
