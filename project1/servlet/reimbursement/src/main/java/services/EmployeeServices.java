package services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import dao.EmployeeDAO;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.ReimburseRequest;

public class EmployeeServices {	
	
	public EmployeeInfo getRequests(int id) {
		return EmployeeDAO.getRequests(id);
	}
	
	public boolean addRequest(int id, String type, String description, String amount) {
		return EmployeeDAO.addRequest(id, type, description, amount, new Date());
	}
}
