package dao;

import org.hibernate.Transaction;

import common.util.DBUtil;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.PersonalInfo;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class AccountDAO {

	/* 
	 * findLogin will get the account that is connected to the
	 * email parameter. The the password will be hashed using
	 * the email and the salt of the user account and checked if it matches
	 * If the login in verified then the account is returned
	 */
	public static LoginInfo findLogin(String email, String password) {
		Session session = DBUtil.getInstance().getSession();
		try {
			Query query = session.createQuery("FROM hibernate.LoginInfo WHERE email = :Email");
			query.setString("Email", email);
			
			LoginInfo temp = (LoginInfo)query.uniqueResult();
			String hashedPass = hashPassword(password, email, temp.getSalt());
			if(temp.getPassword().equals(hashedPass)) {
				LoginInfo l = session.find(LoginInfo.class, temp.getId());
				return l;
			}
			return null;
		} catch (HibernateException e) {
			return null;
		}
	}
	
	
	/*
	 * addLogin takes in a LoginInfo, EmployeeInfo, and PersonalInfo class
	 * (Each is instanciated with the appropriate information and properly mapped in ApplicationServices)
	 * A salt will be generated and the password of the account will then be hashed 
	 * and set as the LoginInfo class's password
	 */
	public static LoginInfo addLogin(LoginInfo l, EmployeeInfo e, PersonalInfo p) {
		Session session = DBUtil.getInstance().getSession();
		Transaction t = session.beginTransaction();
		try {
			l.setSalt(generateSalt());
			String hashedPass = hashPassword(l.getPassword(), l.getEmail(), l.getSalt());
			l.setPassword(hashedPass);
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
	
	
	/*
	 * getAccount will return the account with the corresponding Login Id
	 */
	public static LoginInfo getAccount(int id) {
		try {
			Session session = DBUtil.getInstance().getSession();
			LoginInfo l = session.find(LoginInfo.class, id);
			return l;
		} catch (HibernateException e) {
			return null;
		}
	}
	
	
	/*
	 * updatePassword will get the account with the corresponding Login Id
	 * generate a new salt, hash the new password, and update the table
	 */
	public static boolean updatePassword(int id, String password) {
		Session session = DBUtil.getInstance().getSession();
		Transaction t = session.beginTransaction();
		try {
			LoginInfo l = session.find(LoginInfo.class, id);
			l.setSalt(generateSalt());
			String hashPass = hashPassword(password, l.getEmail(), l.getSalt());
			l.setPassword(hashPass);
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
	
	
	/*
	 * checkPassword checks to verify that the password is correct
	 * Used in ApplicationServices in pairing with updatePassword
	 */
	public static boolean checkPassword(int id, String password) {
		Session session = DBUtil.getInstance().getSession();
		LoginInfo l = session.find(LoginInfo.class, id);
		String hashPass = hashPassword(password, l.getEmail(), l.getSalt());
		if(l.getPassword().equals(hashPass)) {
			return true;
		}
		return false;
	}
	
	
	/*
	 * Hashes the provided password using the email and the salt and SHA-256 encryption
	 */
	public static String hashPassword(String password, String email, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String newPass = password + email + salt;
			BigInteger hash =  new BigInteger(1,md.digest(newPass.getBytes(StandardCharsets.UTF_8)));
			StringBuilder hashedPass = new StringBuilder(hash.toString(16));
			while(hashedPass.length() < 32) {
				hashedPass.insert(0, '0');
			}
			return hashedPass.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	
	/*
	 * Generates a random string with a length of 10
	 * Used for Hashing the password
	 */
	public static String generateSalt() {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < 10; i++) {
			s.append((char)((int)(65 + 26 * Math.random())));
		}
		return s.toString();
	}
}
