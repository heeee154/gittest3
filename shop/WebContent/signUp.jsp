<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<%
    
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
    
		ArrayList<String> id_arr = (ArrayList<String>) session.getAttribute("idArrayList");
	%>
	
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	var a = 1;
	function check(){
		theForm = document.sign;
		if(theForm.mem_id.value==""||theForm.mem_email.value==""||theForm.mem_name.value==""||theForm.mem_pw.value==""||theForm.pw_ok.value==""||theForm.mem_tel.value==""||theForm.mem_address.value==""||theForm.mem_pw.value != theForm.pw_ok.value){
			if(theForm.mem_id.value==""){
				alert("ID입력란이 비어있습니다. 확인해주세요.")
				return false;
			}else if(theForm.mem_email.value==""){
				alert("E-mail입력란이 비어있습니다. 확인해주세요.")
				return false;
			}else if(theForm.mem_name.value==""){
				alert("Name입력란이 비어있습니다. 확인해주세요.")
				return false;
			}else if(theForm.mem_pw.value==""){
				alert("Password입력란이 비어있습니다. 확인해주세요.")
				return false;
			}else if(theForm.pw_ok.value==""){
				alert("Confirm password입력란이 비어있습니다. 확인해주세요.")
				return false;
			}else if(theForm.mem_tel.value==""){
				alert("Phone_number입력란이 비어있습니다. 확인해주세요.")
				return false;
			}else if(theForm.mem_address.value==""){
				alert("Address입력란이 비어있습니다. 확인해주세요.")
				return false;
			}else if(theForm.mem_pw.value != theForm.pw_ok.value){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}else if(a!=1||a!=2){
				alter("중복확인이 필요합니다.");
			}
		}
		return true;
	}

	function check_id(){
		theForm = document.sign;
		var id_con = theForm.mem_id.value;
		var arr = new Array();
		<%
			String em =null;
			for(int i = 0 ; i < id_arr.size(); i ++){
				em = id_arr.get(i);
		%>
				arr.push('<%=em%>');
		<%
			}	
		%>
		
			for(var i = 0 ; i < arr.length; i ++){
				if(arr[i]==id_con){
					a++;
				}
			}
			if(a==2){
				alert("중복된 아이디가 존재합니다.");
			}else if(a==1){
				alert("사용가능한 아이디 입니다.");
			}
	}
	

</script>
</head>
<body>
	<div align="center">
	<form action="sign" name = "sign" onsubmit="return check()" method = "post">
		<table>
			<tr>
				<td>
				<h1>Sign up</h1>
				</td>
			</tr>
			<tr>
				<td>
				ID<br>
				<input type = "text" name = "mem_id"/>
				<input type = "button" value = "double_check" name = "enter" onclick="check_id()" class="btn green mini"/>
				</td>
			</tr>
			<tr>
				<td>
				E-mail<br>
				<input type = "text" name = "mem_email" />
				</td>
			</tr>
			<tr>
				<td>
				Name<br>
				<input type = "text" name = "mem_name"/>	
				</td>
			</tr>
			<tr>
				<td>
				Password<br>
				<input type = "password" name = "mem_pw"/>	
				</td>
			</tr>
			<tr>
				<td>
				Confirm Password<br>
				<input type = "password" name = "pw_ok"/>	
				</td>
			</tr>
			<tr>
				<td>
				Phone_number<br>
				<input type = "text" name = "mem_tel"/>
				</td>
			</tr>
			<tr>
				<td>
				Address<br>
				<input type = "text" name = "mem_address"/>
				</td>
			</tr>
			<tr>
				<td>
				<input type = "submit" value = "회원가입" name = "sign_ok" class="btn green mini"/>
				<input type = "submit" value = "취소" name = "cancel" class="btn green mini"/>
				</td>
			</tr>
		</table>
	</form>
	</div>
	

</body>
</html>