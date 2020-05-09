<%@page import="shop.login_mem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%	
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	boolean check_login = true;
	String button_value = "로그인";
	if (session.getAttribute("mem_info") != null){
		button_value = "로그아웃";
	}
	else{
		check_login = false;
	}
	

	String pagefile = request.getParameter("page");
	 if (pagefile == null) {
		 pagefile = "Page3"; 
	}
/* 	if (pagefile.equals("login")) {
		button_value = "로그인";
	} */
%>

<html>
<head>
<meta charset="UTF-8">
<title>쉬구마</title>	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		function pageMove(x) {
	        location.href= x;
		}
		
		function pageMove_log(){
			if($('#login_button').val() == "로그아웃") {
				var check = confirm("정말 로그아웃 하시겠습니까?")
				if(check) {
					location.href= 'Main.jsp?page=login';
				}
			}
			else{
				location.href= 'Main.jsp?page=login';
			}
		}
		
		function pageMove_my(){
			var check_login = <%=check_login%>;
			if(!check_login) {
				alert("로그인하세요");
				location.href ="Main.jsp";
			}
			else{
				var form = document.createElement("form");
				form.setAttribute("action", "order_info");
				form.setAttribute("method", "post");
				
				document.body.appendChild(form);
				form.submit();
			}
		}
		
		
		$(document).ready(function() {
			// 기존 css에서 플로팅 배너 위치(top)값을 가져와 저장한다.
			var floatPosition = parseInt($("#floatMenu").css('top'));
			// 250px 이런식으로 가져오므로 여기서 숫자만 가져온다. parseInt( 값 );
	
			$(window).scroll(function() {
				// 현재 스크롤 위치를 가져온다.
				var scrollTop = $(window).scrollTop();
				var newPosition = scrollTop + floatPosition + "px";
	
				$("#floatMenu").stop().animate({
					"top" : newPosition
				}, 500);
	
			}).scroll();
		});
	</script>
	<link rel= "stylesheet" type="text/css" href="shop_css.css">
</head>
<body>
	<div>
	<div id="main_table">
		<table width="1000" align="center">
			<tr height="200" >
				<td colspan="2"> 
					<img src="C:/Users/user/Desktop/imggggg.png">
				</td>
			</tr>
			<tr height="40">
				<td align="center">
					<!-- <input type="button" value="Store" onclick="pageMove('Main.jsp?page=Page3')" class="main_button">  -->
					<input type="button" value="Board" onclick="pageMove('Main.jsp?page=boardViewer')" class="main_button">
					<input type="button" value="Admin" onclick="pageMove('Main.jsp?page=productForm')" class="main_button">
				</td>
				
				<td width="300" align="right">
					<input type="button" id="login_button" value='<%= button_value %>' onclick="pageMove_log()" class="main_button">
					<input type="button" value="장바구니" onclick="pageMove('Main.jsp?page=bascket')" class="main_button">
					<input type ="button" value ="마이페이지" onclick ="pageMove_my()" class="main_button">
				</td>
			</tr>
			<tr height="1000">
				<td colspan="2">
					<jsp:include page='<%=pagefile + ".jsp"%>'></jsp:include> 
				</td>
			</tr>
			<tr height="180">
				<td colspan="2" align="center"  >
					                                  이용 약관 개인정보처리방침 사업자정보확인<br>
					상호: 쉬구마 | 대표: 이지연 | 이메일: shuiguma@naver.com<br>
					주소: 경기도 성남시 분당구 판교로 | 사업자등록번호: 180-54-00314 | 통신판매: 제 2018-성남분당-0231 | 호스팅제공자: (주)식스샵<br>
					COPYRIGHT © 2019 쉬구마. ALL RIGHTS RESERVED
				</td>
			</tr>
		</table>
	</div>

	<div id="floatMenu">
		<jsp:include page='chat_addon.jsp'></jsp:include> 
	</div>
	</div>
</body>
</html>