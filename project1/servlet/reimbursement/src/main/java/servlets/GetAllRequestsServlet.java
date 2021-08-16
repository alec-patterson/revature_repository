package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import services.EmployeeServices;
import services.FinanceServices;


@WebServlet("/getAllRequests")
public class GetAllRequestsServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = "";
			FinanceServices fs = new FinanceServices();
			List<LoginInfo> lList = fs.getRequests();
			jsonString = mapper.writeValueAsString(lList);
			response.getWriter().print(jsonString);
			response.setStatus(Constants.HTTP_OK);
		} catch (Exception e) {
			response.setStatus(Constants.HTTP_ERROR);
		}
		response.setContentType(Constants.HTTP_JSON_CONTENT);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = "";
			Map<String, Object> map = mapper.readValue(HttpUtil.getJSONData(request), new TypeReference<Map<String, Object>>() {});
			System.out.println((int)map.get("requestId"));
			System.out.println((String)map.get("status"));
			FinanceServices fs = new FinanceServices();
			List<LoginInfo> lList = fs.updateStatus((int)map.get("requestId"), (String)map.get("status"));
			jsonString = mapper.writeValueAsString(lList);
			response.getWriter().print(jsonString);
			response.setStatus(Constants.HTTP_OK);
		} catch (Exception e) {
			response.setStatus(Constants.HTTP_ERROR);
		}
		response.setContentType(Constants.HTTP_JSON_CONTENT);
	}
}
