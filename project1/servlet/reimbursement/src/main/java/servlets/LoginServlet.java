package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.util.Constants;
import common.util.DBUtil;
import common.util.HttpUtil;
import dao.AccountDAO;
import hibernate.LoginInfo;
import services.ApplicationServices;
import user.Account;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			LoginInfo l = mapper.readValue(HttpUtil.getJSONData(request), LoginInfo.class);
			String role = ApplicationServices.login(l.getEmail(), l.getPassword());
			if(role != null) {	
				response.getWriter().print("{\"success\":" + true + ", \"role\": \"" + role + "\"}");
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
