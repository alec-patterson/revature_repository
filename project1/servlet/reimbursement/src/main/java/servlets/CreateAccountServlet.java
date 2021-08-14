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
import services.ApplicationServices;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		try {
		Map<String, Object> map = mapper.readValue(HttpUtil.getJSONData(request), new TypeReference<Map<String,Object>>(){});
		String role = "employee";
		ApplicationServices as = new ApplicationServices((String)map.get("email"), (String)map.get("password"), role, (String)map.get("firstName"),(String)map.get("lastName"),(String)map.get("address"),(String)map.get("city"),(String)map.get("state"),(String)map.get("zipcode"),(String)map.get("phonenumber"));
		boolean success = as.addLogin();
		if(success == true) {
			response.getWriter().print("{\"success\":" + true + ", \"role\": \"" + role + "\"}");
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

	
	
	
	
	
//	ObjectMapper mapper = new ObjectMapper();
//	StringBuffer jsonStrBuffer = new StringBuffer();
//	String line = "";
//	BufferedReader reader = request.getReader();
////	while((line = reader.readLine()) != null)
////		System.out.println(line);
//	reader.readLine();
//	String loginString = "{" + reader.readLine() + reader.readLine() + reader.readLine() + reader.readLine() + reader.readLine() + reader.readLine() + reader.readLine() + "}";
//	loginString = loginString.substring(0, loginString.length()-2) + "}";
//	System.out.println(loginString);
//	PersonalInfo p = mapper.readValue(loginString, PersonalInfo.class);
//	System.out.println(p);
//	//LoginInfo l = mapper.readValue(HttpUtil.getJSONData(request), LoginInfo.class);
//	boolean success = true;
//	response.getWriter().print("{\"status\":" + (success ? true:false) + "}");
//	response.setStatus(Constants.HTTP_OK);
//} catch (Exception e) {
//	response.setStatus(Constants.HTTP_ERROR);
//}
//response.setContentType(Constants.HTTP_JSON_CONTENT);