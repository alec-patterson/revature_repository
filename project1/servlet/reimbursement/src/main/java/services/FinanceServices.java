package services;

import java.util.List;

import dao.FinanceManagerDAO;
import hibernate.LoginInfo;

public class FinanceServices {
	public List<LoginInfo> getRequests() {
		return FinanceManagerDAO.getRequests();
	}
	
	public List<LoginInfo> updateStatus(int requestId, String status) {
		return FinanceManagerDAO.updateStatus(requestId, status);
	}
}
