<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<body>
<%
	String name = (String) request.getAttribute("name");
	%>
	
	<h2>Hello <%= name%>!</h2>
</body>
</html>


<!-- PrintWriter out = response.getWriter();
		out.println("<h1>Hello Servlet</h1>");
		String name = "alec";
		response.setContentType("text/html");
		request.setAttribute("name", name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
		dispatcher.forward(request, response); -->