package services;

import java.util.Date;

import dao.EmployeeDAO;
import hibernate.EmployeeInfo;

public class EmployeeServices {	
	
	/*
	 * getRequests called EmployeeDAO.getRequests(int)
	 * and returns the EmployeeInfo object associated with the Login Id
	 * provided
	 */
	public EmployeeInfo getRequests(int id) {
		return EmployeeDAO.getRequests(id);
	}
	
	
	/*
	 * addRequest calls EmployeeDAO.addRequest(int, String, String, String, Date)
	 * to create a new ReimbursementRequest to be added to the database
	 */
	public boolean addRequest(int id, String type, String description, String amount) {
		return EmployeeDAO.addRequest(id, type, description, amount, new Date());
	}
}
