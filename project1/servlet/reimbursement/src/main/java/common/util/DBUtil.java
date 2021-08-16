package common.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBUtil {
	
	// Singleton formatting for getting a new session
	private static DBUtil _instance = null;
	private SessionFactory factory = null;
	
	// Constructor will create a new Session Factory
	public DBUtil() {
		this.factory = new Configuration().configure().buildSessionFactory();
	}
	
	// Returns the instance of DBUtil, or creates one if one is not already instantiated
	public static DBUtil getInstance() {
		if(_instance == null) {
			_instance = new DBUtil();
		}
		return _instance;
	}
	
	// returns a new Session from the SessionFactory
	@SuppressWarnings("unused")
	public Session getSession() {
		return this.factory.openSession();
	}
}