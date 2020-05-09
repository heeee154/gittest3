<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
		String show_content ="";
		if(session.getAttribute("show_content") != null) {
			show_content = (String) session.getAttribute("show_content");
		}

		int bo_num = -1;
		if(request.getAttribute("bo_num") != null ) {
			bo_num = (int) request.getAttribute("bo_num");
		}
		
		String bo_password = "";
		if(request.getAttribute("bo_password") != null) {
			bo_password = (String) request.getAttribute("bo_password");
		}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>boardViewer4.jsp</title>
	<style type="text/css">
		td, tr {
			padding: 10px;
			text-align: center;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		
		$(document).ready(function(){
			$('#remove').click(function(){
				var bool = confirm('정말 삭제하시겠습니까?');
				if(bool) {
					var password = prompt('비밀번호를 입력하세요.', '');
					if(password == <%=bo_password%>) {
						var input = $("<input>")
			               .attr("type", "hidden")
			               .attr("name", "remove").val("remove");
						$('#myform').append(input);
						$('#myform').submit(); 
					}
					else{
						alert("비밀번호가 틀렸습니다");
					}
				} else{
					return bool;
				}
			});
			
			$('#modify').click(function(){
				var bool = confirm('정말 수정하시겠습니까?');
				if(bool) {
					var password = prompt('비밀번호를 입력하세요.', '');
					if(password == <%=bo_password%>) {
						var input = $("<input>")
			               .attr("type", "hidden")
			               .attr("name", "modify").val("modify");
						$('#myform').append(input);
						$('#myform').submit();
					}
					else{
						alert("비밀번호가 틀렸습니다");
					}
				} else{
					return bool;
				}
			});
		});
	</script>
</head>
<body>
	<div align="center">
		<%=show_content %>
	</div>
	<div style="text-align: center; padding: 20px; margin: 20px;">
		<form action="read_board_db3" name="myform" id="myform" method="post">
			<input type="hidden" name="bo_num" value='<%=bo_num %>' />
			<input type="hidden" name="bo_password" id="bo_password" value='<%=bo_password %>' />
			<input type="button" name="remove" id="remove" value="remove" class="btn green mini"/>
			<input type="button" name="modify" id="modify" value="modify" class="btn green mini"/>
		</form>
	</div>
</body>
</html>