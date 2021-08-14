<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<body>
<%
	String name = (String) request.getAttribute("name");
	%>
	
	<h2>Hello <%= name%>!</h2>
</body>
</html>