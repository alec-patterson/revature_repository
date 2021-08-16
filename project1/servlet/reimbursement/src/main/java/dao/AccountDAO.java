package dao;

import org.hibernate.Transaction;
import org.hibernate.Query;

import common.util.DBUtil;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.PersonalInfo;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import user.Account;

public class AccountDAO {

	public static LoginInfo findLogin(String email, String password) {
		Session session = DBUtil.getInstance().getSession();
		try {
			Query query = session.createQuery("FROM hibernate.LoginInfo WHERE email = :Email");
			query.setString("Email", email);
			
			LoginInfo temp = (LoginInfo)query.uniqueResult();
			if(temp.getPassword().equals(password)) {
				LoginInfo l = session.find(LoginInfo.class, temp.getId());
				return l;
			}
			return null;
		} catch (HibernateException e) {
			return null;
		}
	}
	
	public static LoginInfo addLogin(LoginInfo l, EmployeeInfo e, PersonalInfo p) {
		Session session = DBUtil.getInstance().getSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(l);
			session.save(e);
			session.save(p);
			t.commit();
			return l;
		} catch (HibernateException ex) {
			if(t != null)
				t.rollback();
			return null;
		}
	}
	
	public static LoginInfo getAccount(int id) {
		try {
			Session session = DBUtil.getInstance().getSession();
			LoginInfo l = session.find(LoginInfo.class, id);
			return l;
		} catch (HibernateException e) {
			return null;
		}
	}
	
	public static boolean updatePassword(int id, String password) {
		Session session = DBUtil.getInstance().getSession();
		Transaction t = session.beginTransaction();
		try {
			LoginInfo l = session.find(LoginInfo.class, id);
			l.setPassword(password);
			session.update(l);
			t.commit();
			return true;
		} catch (HibernateException e) {
			if(t != null) {
				t.rollback();
			}
			return false;
		}
	}
	
	public static boolean checkPassword(int id, String password) {
		Session session = DBUtil.getInstance().getSession();
		LoginInfo l = session.find(LoginInfo.class, id);
		if(l.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
