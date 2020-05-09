<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");


	String or_num ="" ;
	if(request.getParameter("or_num") != null ) {
		or_num = (String)request.getParameter("or_num");
	}
%>
function page(){
	var form = document.createElement("form");
	form.setAttribute("action", "order_detail");
	form.setAttribute("method", "post");
	
	var bo_numfield = document.createElement("input");
	bo_numfield.setAttribute("type", "hidden");
	bo_numfield.setAttribute("name", "or_num");
	bo_numfield.setAttribute("value", '<%=or_num%>');
	form.appendChild(bo_numfield);
	
	document.body.appendChild(form);
	form.submit();
}
</script>
</head>
<body onload="page()">

</body>
</html>