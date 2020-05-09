<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
	<div align="center">
	<% 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String order = (String)session.getAttribute("msg"); 
			
	%>
				
				
	<%=order %>
	</div>
</body>
</html>