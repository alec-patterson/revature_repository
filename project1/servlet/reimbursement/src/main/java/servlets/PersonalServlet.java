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



@WebServlet("/personalInfo")
public class PersonalServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = "";
			Map<String, Object> map = mapper.readValue(HttpUtil.getJSONData(request), new TypeReference<Map<String, Object>>() {});
			LoginInfo l = ApplicationServices.getAccount((int)map.get("id"));
			jsonString = mapper.writeValueAsString(l);
			response.getWriter().print(jsonString);
			response.setStatus(Constants.HTTP_OK);
		} catch (Exception e) {
			response.setStatus(Constants.HTTP_ERROR);
		}
		response.setContentType(Constants.HTTP_JSON_CONTENT);
	}
}
