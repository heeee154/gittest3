<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div align="center">
	<form action="searchId">
		<table class="login_table1">
			<tbody>
			<tr>
				<td> Name:</td>
			</tr>
			<tr>
				<td>
				<input type="text" name = "name">
				</td>
			</tr>
			<tr>
				<td>
				Phone_number: 
				</td>
			</tr>
			<tr>
				<td>
				<input type = "text"  name = "num">
				</td>
			</tr>
			<tr>
				<td>
				<input type = "submit"  value = "ID search" class="btn green mini"/>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
	</div>
</body>
</html>