<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.net.URLEncoder"%>
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
<title>boardViewer2.jsp</title>	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		function pageMove(x) {
	        location.href= x;
		}
		
		// enter 키를 통해 검색 event
		function enter_search() {
			if (window.event.keyCode == 13) {
				var search_string = $('#search_string').val();
				search_string = encodeURIComponent(search_string);
				var _url = "Main.jsp?page=boardViewer&board_index=" + 1 + "&iptNUM="+ <%=iptNUM%>+ "&search_string='"+ search_string +"'";
				location.href = _url;
	        }
		}
		
		$(function(){
			
			$('#iptNUM').change(function(){
				var iptNUM = $(this).val();
				var search_string = <%=search_string%>;
				var _url;
				
				if(search_string != null) {
					_url = "Main.jsp?page=boardViewer&board_index=" + 1 + "&iptNUM="+ iptNUM+ "&search_string='"+ search_string +"'";
				} else {
					_url = "Main.jsp?page=boardViewer&board_index=" + 1 + "&iptNUM="+ iptNUM;
				}
				location.href = _url;
			});
			
			$('#search_button').click(function(){
				var search_string = $('#search_string').val();
				search_string = encodeURIComponent(search_string);
				var _url = "Main.jsp?page=boardViewer&board_index=" + 1 + "&iptNUM="+ <%=iptNUM%>+ "&search_string='"+ search_string +"'";
				location.href = _url;
			});
		});
	</script>
</head>
<body>
	<div align="center">			
		<div style="text-align: right; padding: 20px; padding-right: 40px; margin: 40px;">
			<input type="button" value="새글작성" onclick="pageMove('Main.jsp?page=boardForm')" class="btn green mini"/>
			<select name="iptNUM" id="iptNUM">
				<option value="">보기 선택</option>
				<option value="10">10개씩 보기</option>
				<option value="20">20개씩 보기</option>
				<option value="30">30개씩 보기</option>
			</select>
		</div>
		
		
		<%
			String show_table ="";
			if(session.getAttribute("show_table") != null) {
				show_table = (String) session.getAttribute("show_table");
			}
		%>		
		<%=show_table %>

		
		<%
			String show_board_index ="";
			if(session.getAttribute("show_board_index") != null) {
				show_board_index = (String) session.getAttribute("show_board_index");
			}
		%>		
		<%=show_board_index %>
		
		<div align="center"> 
			<input type="text" name="search_string" id="search_string" onkeyup="enter_search()"/>
			<input type="button" name="search_button" id="search_button" value="검색" class="btn green mini"/>
		</div>
	</div>
</body>
</html>