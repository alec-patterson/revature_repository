package sqlExample.sql_postgres_example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlEx {
	public static void main(String[] args) {
		try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Aljapa480512@")) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test.personel");
			System.out.printf("%-5s%-10s%s\n", "id", "name", "email");
			while(rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				String email = rs.getString("email");
				System.out.printf("%-5s%-10s%s\n", (id + ":"), name, email);
			}
			
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

/*
 * inserting into db
 * 		int inserted = stmt.executeUpdate( "INSERT INTO table (args) VALUES (values for args)")
 */
