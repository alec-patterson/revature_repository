package dao;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import common.util.DBUtil;
import hibernate.LoginInfo;
import hibernate.ReimburseRequest;

public class FinanceManagerDAO extends AccountDAO{

	/*
	 * getRequests returns a list of all of the LoginInfo entries
	 * Used for displaying all requests for a Financial Manager
	 */
	public static List<LoginInfo> getRequests() {
		Session session = DBUtil.getInstance().getSession();
		List<LoginInfo> lList;
		Query<LoginInfo> query = session.createQuery("FROM hibernate.LoginInfo");
		lList = query.list();
		return lList;
	}
	
	
	/*
	 * updateStatus returns a list of all of the LoginInfo entries after updating 
	 * a user's reimbursement request to either approved or denied
	 */
	public static List<LoginInfo> updateStatus(int requestId, String status) {
		Session session = DBUtil.getInstance().getSession();
		Transaction t = session.beginTransaction();
		try {
			ReimburseRequest rr = session.find(ReimburseRequest.class, requestId);
			rr.setStatus(status);
			session.update(rr);
			t.commit();
		} catch (HibernateException e){
			return null;
		}
		return getRequests();
	}
	
}
