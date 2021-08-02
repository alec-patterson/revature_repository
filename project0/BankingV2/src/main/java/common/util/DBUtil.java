package common.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

	private static DBUtil _instance;
	private Connection conn = null;
	
	public static DBUtil getInstance() {
		if(_instance == null) {
			_instance = new DBUtil();
		}
		return _instance;
	}
	
	public Connection getConnection() {
		if(this.conn == null) {
			String configFilePath = System.getProperty(Constants.CONFIG_FILE);
			try(FileInputStream fis = new FileInputStream(configFilePath)) {
				Properties prop = new Properties();
				prop.load(fis);
				
				this.conn = DriverManager.getConnection(prop.getProperty(Constants.DB_URL), prop.getProperty(Constants.DB_USERNAME), prop.getProperty(Constants.DB_PASSWORD));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return this.conn;
	}
	
}
