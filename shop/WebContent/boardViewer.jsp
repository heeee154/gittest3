<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	String board_index = request.getParameter("board_index");
	if(board_index == null){
		board_index = "1";
	}
	
	String board_jump_index = request.getParameter("board_jump_index");
	if(board_jump_index == null) {
		board_jump_index = "0";
	}
	
	String iptNUM = request.getParameter("iptNUM");
	if(iptNUM == null){
		iptNUM = "20";
	}
	
	String search_string = null;
	if(request.getParameter("search_string") != null){
		search_string = request.getParameter("search_string");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardViewer</title>
	<script type="text/javascript">
		function read_board_db(){
			var form = document.createElement("form");
			form.setAttribute("action", "read_board_db");
			form.setAttribute("method", "post");
			
			var board_indexfield = document.createElement("input");
			board_indexfield.setAttribute("type", "hidden");
			board_indexfield.setAttribute("name", "board_index");
			board_indexfield.setAttribute("value", <%=board_index%>);
			form.appendChild(board_indexfield);
			
			var board_jump_indexfield = document.createElement("input");
			board_jump_indexfield.setAttribute("type", "hidden");
			board_jump_indexfield.setAttribute("name", "board_jump_index");
			board_jump_indexfield.setAttribute("value", <%=board_jump_index%>);
			form.appendChild(board_jump_indexfield);
			
			var iptNUMfield = document.createElement("input");
			iptNUMfield.setAttribute("type", "hidden");
			iptNUMfield.setAttribute("name", "iptNUM");
			iptNUMfield.setAttribute("value", <%=iptNUM%>);
			form.appendChild(iptNUMfield);
			
			if(<%=search_string%> != null) {
				var search_stringfield = document.createElement("input");
				search_stringfield.setAttribute("type", "hidden");
				search_stringfield.setAttribute("name", "search_string");
				search_stringfield.setAttribute("value", <%=search_string%>);
				form.appendChild(search_stringfield);
			}
		
			document.body.appendChild(form);	// body 에 추가
			form.submit();						// form 을 실행
		}
	</script>
</head>
<body onload="read_board_db()">
</body>
</html>