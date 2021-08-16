package servlets;

import java.io.IOException;
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
import hibernate.EmployeeInfo;
import services.EmployeeServices;

@WebServlet("/getMyRequests")
public class GetMyRequests extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
	
	/*
	 * Communicates with the front end to get a Users own submitted Reimbursement Requests
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = "";
			
			Map<String, Object> map = mapper.readValue(HttpUtil.getJSONData(request), new TypeReference<Map<String,Object>>(){});
			EmployeeServices es = new EmployeeServices();
			EmployeeInfo e = es.getRequests((int)map.get("id"));
			jsonString = mapper.writeValueAsString(e);
			response.getWriter().print(jsonString);
			response.setStatus(Constants.HTTP_OK);
		} catch (Exception e) {
			response.setStatus(Constants.HTTP_ERROR);
		}
		response.setContentType(Constants.HTTP_JSON_CONTENT);
	}
}
