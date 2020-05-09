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
	<form action="searchPw">
			<table class="login_table1">
				<tbody>
				<tr>
					<td>
					Name:<br/>
					<input type="text" name = "name"/>
					</td>
				</tr>
				<tr>	
					<td>
					Phone_Number: <br/>
					<input type = "text"  name = "num"/>
					</td>
				</tr>
				<tr>
					<td>
					ID: <br/>
					<input type = "text"  name = "id"/>
					</td>
				</tr>
				<tr>
					<td>
					<input type = "submit"  value = "PW search" class="btn green mini"/>
					</td>
				</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>