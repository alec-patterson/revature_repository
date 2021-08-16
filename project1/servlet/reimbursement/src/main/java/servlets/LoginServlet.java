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



@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
	/*
	 * Communicates with the front to log a user into their account
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> s = mapper.readValue(HttpUtil.getJSONData(request), new TypeReference<Map<String,Object>>(){});
			LoginInfo l = ApplicationServices.login((String)s.get("email"), (String)s.get("password"));
			if(l != null) {	
				response.getWriter().print("{\"success\":" + true + ", \"role\": \"" + l.getEmployeeInfo().getRole() + "\", \"loginId\":" + l.getId() +  "}");
			} else {
				response.getWriter().print("{\"success\":" + false + "}");
			}
			response.setStatus(Constants.HTTP_OK);
		} catch (Exception e) {
			response.setStatus(Constants.HTTP_ERROR);
		}
		response.setContentType(Constants.HTTP_JSON_CONTENT);
	}
}
