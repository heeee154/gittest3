<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</script>
</head>
<body>
	<%
	session.removeAttribute("bascketNumber");
	session.removeAttribute("orderAmount");
	%>
	<script type="text/javascript">
		alert("장바구니를 비웠습니다.");
		location.href = "Main.jsp?page=bascket";
		
	</script>
	
</body>
</html>