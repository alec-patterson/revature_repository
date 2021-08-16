package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.util.Constants;
import common.util.DBUtil;
import common.util.HttpUtil;
import dao.AccountDAO;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.ReimburseRequest;
import services.ApplicationServices;
import services.EmployeeServices;

@WebServlet("/getMyRequests")
public class GetMyRequests extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
	
	
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
