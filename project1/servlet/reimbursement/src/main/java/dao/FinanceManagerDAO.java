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

public class FinanceManagerDAO extends AccountDAO{

	public static List<LoginInfo> getRequests() {
		Session session = DBUtil.getInstance().getSession();
		List<LoginInfo> lList;
		Query<LoginInfo> query = session.createQuery("FROM hibernate.LoginInfo");
		lList = query.list();
		return lList;
	}
	
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
