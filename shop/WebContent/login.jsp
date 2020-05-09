<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	session.removeAttribute("mem_info");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center" >
		<form action="login" method="post">
			<table class="login_table1">
				<tbody>
				<tr>
					<td>
						<h3>로그인</h3>
					</td>
				</tr>
				<tr>
					<td>
						ID
						<br>
						<input type="text" name="id">
					</td>
				</tr>
				<tr>
					<td>
						Password
						<br>
						<input type="password" name="pw">
					</td>
				</tr>
				<tr>
					<td>
						<a href="Main.jsp?page=searchid">Forgot your ID?</a> /
						<a href="Main.jsp?page=searchpw">Forgot your password?</a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" name="login" value="Login" class="btn green mini"/>
						<br>
					</td>
				</tr>
				</tbody>
			</table>
		</form>
		<form action="idArraylist" method="post">
			<table class="login_table1">
				<tbody>
					<tr>
						<td>
							<input type="submit" name="signin" value="Sign_in" class="btn green mini"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>