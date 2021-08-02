package users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends Customer{
	private float salary;
	private int employeeId;
	 
	
	/*
	 * Employee constructor takes in a ResultSet that will initialize all Employee 
	 * variables
	 */
	public Employee(ResultSet rs) {
		super(rs);
		try {
			this.salary = rs.getFloat("salary");
			this.employeeId = rs.getInt("employee_id");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Getters and Setters for all Customer variables
	 */
	
	public float getSalary() {
		return salary;
	}
	
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
}
