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
import services.EmployeeServices;


@WebServlet("/addRequests")
public class AddRequestsServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
	
	/*
	 * Communicates with the front end to add a Reimbursement Request to the database
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(HttpUtil.getJSONData(request), new TypeReference<Map<String,Object>>(){});
			System.out.println((int)map.get("id"));
			System.out.println((String)map.get("type"));
			System.out.println((String)map.get("description"));
			System.out.println(map.get("amount"));
			EmployeeServices es = new EmployeeServices();
			boolean success = es.addRequest((int)map.get("id"), (String)map.get("type"), (String)map.get("description"), (String)map.get("amount"));
			response.getWriter().print("{\"success\":" + (success ? true : false) + "}");
			response.setStatus(Constants.HTTP_OK);
		} catch (Exception e) {
			response.setStatus(Constants.HTTP_ERROR);
		}
		response.setContentType(Constants.HTTP_JSON_CONTENT);
	}
	
}
