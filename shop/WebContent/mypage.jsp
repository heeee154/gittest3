<%@page import="shop.login_mem"%>
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
	<table>
	<tr>
	<td height = 1000 width = 400>
	<jsp:include page="order.jsp"/>
	</td>
	<td height = 1000 width = 400>
	<jsp:include page="edit.jsp"/>
	</td>
	</tr>
	</table>
	</div>
</body>
</html>