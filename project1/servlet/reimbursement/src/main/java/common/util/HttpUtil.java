package common.util;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

//	public static String[] getPathVariables(HttpServletRequest req) {
//		String[] variables = null;
//		String path = req.getPathInfo();
//	}
	public static String getJSONData(HttpServletRequest req) throws Exception {
		StringBuffer jsonStrBuffer = new StringBuffer();
		String line = null;

		BufferedReader reader = req.getReader();
		while ((line = reader.readLine()) != null)
			jsonStrBuffer.append(line);

		return jsonStrBuffer.toString();
	}
}
