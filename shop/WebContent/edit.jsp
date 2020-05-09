<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="shop.login_mem"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
// 회원가입한 사람의 정보를 가져와서 html value값으로 줘야한다.
// edit을 누르면 폰넘버를 기준으로 정보를 조회해서 해당 인덱스의 값으로 비교해서 달라진 정보가 있다면
// 그정보를 디비에 수정하고
// alert정보가 수정되었습니다. 창을 띄운다.
%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	login_mem test = (login_mem)session.getAttribute("mem_info");
	System.out.println(test.getMem_id());
%>
<body>
	<div align="center">
		<form  action = "editprofile">
		<table>
			<tr>
				<td>
				<h1>Edit Profile</h1>
				</td>
			</tr>
			<tr>
				<td>
				ID<br>
				<input type = "text" name = "mem_id" value = "<%=test.getMem_id() %>" readonly >
				</td>
			</tr>
			<tr>
				<td>
				PassWord<br>
				<input type = "password" name = "mem_pw" value = "<%=test.getMem_pw() %>" >
				</td>
			</tr>
			<tr>
				<td>
				Name<br>
				<input type = "text" name = "mem_name" value = "<%=test.getMem_name()%>">
				</td>
			</tr>
			<tr>
				<td>
				E-mail<br>
				<input type = "text" name = "mem_email" value = "<%=test.getMem_email()%>">
				</td>
			</tr>
			<tr>
				<td>
				Phone Number<br>
				<input type = "text" name = "mem_tel" value = "<%=test.getMem_tel()%>">
				</td>
			</tr>
			<tr>
				<td>
				Address<br>
				<input type = "text" name = "mem_address" value = "<%=test.getMem_address()%>">
				</td>
			</tr>
			<tr>
				<td>
				<input type = "submit" name = "edit"  value = "edit" class="btn green mini"/>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>