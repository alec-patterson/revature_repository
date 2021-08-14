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

public class FinanceManagerDAO extends AccountDAO{

	public List<ReimburseRequest> getRequests(Account a, String filter) {
		Session session = a.getSession();
		Query query;
		List<ReimburseRequest> list;
		if(filter.toLowerCase().equals("lodging") || filter.toLowerCase().equals("travel") || filter.toLowerCase().equals("Food") || filter.toLowerCase().equals("other")) {
			query = session.createQuery("FROM hibernate.ReimburseRequest where status = :Stat");
			query.setString("Stat", filter);
			list = query.list();
		} else {
			query = session.createQuery("FROM hibernate.ReimburseRequest");
			list = query.list();
		}
		return list;
	}
	
	public boolean approveRequest(Account a, int requestId) {
		Session session = a.getSession();
		Transaction t = session.beginTransaction();
		try {
			Query query = session.createQuery("UPDATE hibernate.ReimburseRequest SET status = approved WHERE request_id = :Id");
			query.setInteger("Id", requestId);
			query.executeUpdate();
			t.commit();
			return true;
		} catch (HibernateException e) {
			if(t != null)
				t.rollback();
			return false;
		}
	}
	
	public boolean rejectRequest(Account a, int requestId) {
		Session session = a.getSession();
		Transaction t = session.beginTransaction();
		try {
			Query query = session.createQuery("UPDATE hibernate.ReimburseRequest SET status = rejected WHERE request_id = :Id");
			query.setInteger("Id", requestId);
			query.executeUpdate();
			t.commit();
			return true;
		} catch (HibernateException e) {
			if(t != null)
				t.rollback();
			return false;
		}
	}
}
