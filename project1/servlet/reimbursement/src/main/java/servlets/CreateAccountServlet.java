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
import hibernate.LoginInfo;
import services.ApplicationServices;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
	
	/*
	 * Communicates with the front end to add new Account to the Database
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> map = mapper.readValue(HttpUtil.getJSONData(request), new TypeReference<Map<String,Object>>(){});
			String role = "employee";
			LoginInfo l = ApplicationServices.addLogin((String)map.get("email"), (String)map.get("password"), role, (String)map.get("firstName"),(String)map.get("lastName"),(String)map.get("address"),(String)map.get("city"),(String)map.get("state"),(String)map.get("zipcode"),(String)map.get("phonenumber"));
			if(l != null) {
				response.getWriter().print("{\"success\":" + true + ", \"role\": \"" + l.getEmployeeInfo().getRole() + "\", \"loginId\":" + l.getId() +  "}");
			} else {
				response.getWriter().print("{\"success\":" + false + "}");
			}
			response.setStatus(Constants.HTTP_OK); 
		} catch(Exception e) {
			e.printStackTrace();
			response.setStatus(Constants.HTTP_ERROR);
		}
		response.setContentType(Constants.HTTP_JSON_CONTENT);
	}
}