package services;

import java.util.Date;
import java.util.List;

import dao.EmployeeDAO;
import hibernate.ReimburseRequest;

public class EmployeeServices {	
	
	public List<ReimburseRequest> getRequests() {
		return list;
	}

	public void addRequest(String type, String description, float amount) {
		EmployeeDAO.addRequest(a, type, description, amount, new Date());
	}
}
