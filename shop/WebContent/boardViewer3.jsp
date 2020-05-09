<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardViewer3</title>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
		int bo_num = -1;
		if(request.getParameter("bo_num") != null ) {
			bo_num = Integer.parseInt((String)request.getParameter("bo_num"));
		}
	%>
	<script type="text/javascript">
		function read_board_db(){
			var form = document.createElement("form");
			form.setAttribute("action", "read_board_db2");
			form.setAttribute("method", "post");
			
			var bo_numfield = document.createElement("input");
			bo_numfield.setAttribute("type", "hidden");
			bo_numfield.setAttribute("name", "bo_num");
			bo_numfield.setAttribute("value", <%=bo_num%>);
			form.appendChild(bo_numfield);
			
			document.body.appendChild(form);
			form.submit();
		}
	</script>
</head>
<body onload="read_board_db()">
</body>
</html>