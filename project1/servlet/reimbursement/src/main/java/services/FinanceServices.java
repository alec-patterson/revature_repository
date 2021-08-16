package services;

import java.util.List;

import dao.FinanceManagerDAO;
import hibernate.LoginInfo;

public class FinanceServices {
	
	/*
	 * getRequests calls FinanceManagerDAO.getRequests()
	 * to return a List of all of the user accounts in the 
	 * database
	 */
	public static List<LoginInfo> getRequests() {
		return FinanceManagerDAO.getRequests();
	}
	
	
	/*
	 * updateStatus calls FinanceManagerDAO.updateStatus(int, String)
	 * and returns a List of all of the user accounts in the 
	 * database
	 */
	public static List<LoginInfo> updateStatus(int requestId, String status) {
		return FinanceManagerDAO.updateStatus(requestId, status);
	}
	
}
