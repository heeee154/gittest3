<%@page import="shop.login_mem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
		int mem_num = 0;
		String mem_email ="";
	
		login_mem mLogin_mem = (login_mem) session.getAttribute("mem_info");
		boolean check_login = true;
		if (mLogin_mem == null) {
			check_login = false;
		}
		else {
			mem_num = mLogin_mem.getMem_num();
			mem_email= mLogin_mem.getMem_email();
		}
		
		/////////////////////request.getAttribute////////////////////////////
		int bo_num = -1;
		if( request.getAttribute("bo_num") != null) {
			bo_num = (int)request.getAttribute("bo_num");
		}
		
		String bo_title = "";
		if(request.getAttribute("bo_title") != null) {
			bo_title = (String) request.getAttribute("bo_title");
		}
		
		String bo_content = "";
		if(request.getAttribute("bo_content") != null) {
			bo_content = (String) request.getAttribute("bo_content");
		}
		
		String bo_img = "";
		if(request.getAttribute("bo_img") != null) {
			bo_img = (String) request.getAttribute("bo_img");
		}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardForm.jsp</title>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
			var check_login = <%=check_login%>;
			if(!check_login) {
				alert("로그인하세요");
				location.href ="Main.jsp";
			}
	
			function check_form(){				
				if($("#bo_title").val().length==0){
					alert("제목을 입력하세요."); 
					$("#bo_title").focus();
					return false; 
				}
				if($("#bo_password").val().length != 4){ 
					alert("비밀번호를 입력하세요."); 
					$("#bo_password").focus(); 
					return false; 
				}
				if($("#bo_content").val().length==0){ 
					alert("내용을 입력하세요."); 
					$("#bo_content").focus();
					return false; 
				}
				if($("#bo_content").val().length > 1000){ 
					alert("내용 입력길이를 초과했습니다"); 
					$("#bo_content").focus();
					return false; 
				}
				if($("#bo_category").val().length==0){ 
					alert("카테고리를 선택하세요."); 
					$("#bo_category").focus(); 
					return false; 
				}
				return true;
			}
	</script>

</head>
<body>
	<div align="center">
		<form action="boardServlet" method="post" enctype="Multipart/form-data" onsubmit="return check_form()">
			<table>
				<tr>
					<td align="center"><h3> Board </h3></td>
				</tr>
				<tr>	
					<td> 
						category :  
						<select name="bo_category" id="bo_category" >
							<option value="">카테고리 선택</option>
							<option value="배송">배송</option>
							<option value="상품">상품</option>
							<option value="반품/교환">반품/교환</option>
							<option value="주문/결제">주문/결제</option>
							<option value="기타">기타</option>
						</select>
					</td>
				</tr>
				<tr>
					<td> title : <input type="text" id="bo_title" name="bo_title" style="width:400px" maxlength="20" value='<%=bo_title%>'/></td>
				</tr>
				<tr>
					<td> password : <input type="password" id="bo_password" name="bo_password" style="width:200px" maxlength="10"/></td>
				</tr>
				<tr>
					<td> <textarea rows="20" cols="80" id="bo_content" name="bo_content"><%=bo_content%></textarea> </td>
				</tr>
				<tr>
					<td> 첨부 이미지 <img src='<%=bo_img%>'/> </td>
				</tr>
				<tr>
					<td> image : <input type="file" name="bo_img"/></td>
				</tr>
				<tr>
					<td align="center">
						<input type="reset" value="reset" class="btn green mini"/>
						<input type="submit" name="modify" id="submit" value="수정" class="btn green mini"/>
					</td>
				</tr>
			</table>		
			<input type="hidden" name="bo_img" value='<%=bo_img %>'/>
			<input type="hidden" name="bo_num" value='<%=bo_num %>'/>
			<input type="hidden" name="mem_num" value="<%=mem_num%>"/>
			<input type="hidden" name="mem_email" value="<%=mem_email%>"/>
		</form>
	</div>	
</body>
</html>