<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="shop.login_mem"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	login_mem test = (login_mem)session.getAttribute("mem_info");
%>
</head>
<body>
			<% String orderlist = (String)session.getAttribute("list"); 
			%>
				<%=orderlist %>
</body>
</html>