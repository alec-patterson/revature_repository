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

	public static String findLogin(String email, String password) {
		Session session = DBUtil.getInstance().getSession();
		try {
			Query query = session.createQuery("FROM hibernate.LoginInfo WHERE email = :Email");
			query.setString("Email", email);
			
			LoginInfo l = (LoginInfo)query.uniqueResult();
			System.out.println(l);
			if(l != null) {
				if(l.getPassword().equals(password)) {
					query = session.createQuery("FROM hibernate.EmployeeInfo WHERE login_id = :lId");
					query.setInteger("lId", l.getId());
					return ((EmployeeInfo)query.uniqueResult()).getRole();
				}
			}
		} catch (HibernateException e) {
			return null;
		}
		return null;
	}
	
	public static boolean addLogin(Account a) {
		Session session = a.getSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(a.getLoginInfo());
			session.save(a.getEmployeeInfo());
			session.save(a.getPersonalInfo());
			t.commit();
			return true;
		} catch (HibernateException e) {
			if(t != null)
				t.rollback();
			return false;
		}
	}
	
	public static Account getAccount(String email) {
		try {
			Session session = DBUtil.getInstance().getSession();
			Query query = session.createQuery("FROM hibernate.LoginInfo WHERE email = :Email");
			query.setString("Email", email);
			LoginInfo l = (LoginInfo)query.uniqueResult();
			query = session.createQuery("FROM hibernate.EmployeeInfo WHERE login_id = :lId");
			query.setInteger("lId", l.getId());
			EmployeeInfo e = (EmployeeInfo)query.uniqueResult();
			query = session.createQuery("FROM hibernate.PersonalInfo WHERE employee_id = :eId");
			query.setInteger("eId", e.getEmployeeId());
			PersonalInfo p = (PersonalInfo)query.uniqueResult();
			Account a = new Account(l, e, p);
		return a;
		} catch (HibernateException e) {
			return null;
		}
	}
	
	public static boolean updatePassword(Account a, String password) {
		Session session = a.getSession();
		Transaction t = session.beginTransaction();
		try {
			Query query = session.createQuery("UPDATE hibernate.PersonalInfo SET password = :Pass WHERE personal_id = :Pid");
			query.setString("Pass", password);
			query.setInteger("Pid", a.getPersonalInfo().getId());
			t.commit();
			return true;
		} catch (HibernateException e) {
			if(t != null) {
				t.rollback();
			}
			return false;
		}
	}
	
}
