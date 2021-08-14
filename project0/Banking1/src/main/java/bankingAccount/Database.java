package bankingAccount;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.DriverManager;

public class Database {
	Connection conn;
	
	public Database() {
		try(FileInputStream fs = new FileInputStream("C:\\Users\\Alec Patterson\\OneDrive\\Desktop\\revature_repository\\project0\\Banking\\src\\main\\resources\\config.properties")) {
			Properties prop = new Properties();
			prop.load(fs);
			String URL = (String) prop.getProperty("db_url");
			String USERNAME = (String) prop.getProperty("db_username");
			String PASSWORD = (String) prop.getProperty("db_password");
			System.out.println(URL);
			System.out.println(USERNAME);
			System.out.println(PASSWORD);
			
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch(SQLException e ) {
				Main.run = false;
				e.printStackTrace();
			}
		} catch(IOException e) {
			Main.run = false;
			e.printStackTrace();
		}
	}
	
	public ResultSet getUserInformation(String user) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_login WHERE username = " + user);
			if(rs == null) {
				rs = stmt.executeQuery("SELECT * FROM user_login WHERE email = " + user);
			}
			return rs;
		} catch (SQLException e) {
			Main.run = false;
			e.printStackTrace();
		}
		return null;
	}
}
