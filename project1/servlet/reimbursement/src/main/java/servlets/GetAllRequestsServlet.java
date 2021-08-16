package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.util.Constants;
import common.util.HttpUtil;
import hibernate.LoginInfo;
import services.FinanceServices;


@WebServlet("/getAllRequests")
public class GetAllRequestsServlet extends HttpServlet{

	/*
	 * Communicates with the front end to retrieve all user information get every users requests
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = "";
			List<LoginInfo> lList = FinanceServices.getRequests();
			jsonString = mapper.writeValueAsString(lList);
			response.getWriter().print(jsonString);
			response.setStatus(Constants.HTTP_OK);
		} catch (Exception e) {
			response.setStatus(Constants.HTTP_ERROR);
		}
		response.setContentType(Constants.HTTP_JSON_CONTENT);
	}
	
	
	/*
	 * Communicates with the front end to change that status of a Reimbursement Request
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = "";
			Map<String, Object> map = mapper.readValue(HttpUtil.getJSONData(request), new TypeReference<Map<String, Object>>() {});
			System.out.println((int)map.get("requestId"));
			System.out.println((String)map.get("status"));
			List<LoginInfo> lList = FinanceServices.updateStatus((int)map.get("requestId"), (String)map.get("status"));
			jsonString = mapper.writeValueAsString(lList);
			response.getWriter().print(jsonString);
			response.setStatus(Constants.HTTP_OK);
		} catch (Exception e) {
			response.setStatus(Constants.HTTP_ERROR);
		}
		response.setContentType(Constants.HTTP_JSON_CONTENT);
	}
}
