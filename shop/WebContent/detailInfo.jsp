<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.sql.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String query_select = "select * from shop.product where pr_num=";
	query_select += request.getParameter("pr_num");

	String proNumber = request.getParameter("pr_num");
	int productNumber = Integer.parseInt(proNumber);

	HashMap<String, String> infoProduct = new HashMap<String, String>();

	try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("드라이버 성공");
		conn = DriverManager.getConnection("JDBC:mysql://localhost:3306/shop","root","1234");
		System.out.println("DB connect Ok");
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(query_select);
		
		while(rs.next()){
			infoProduct.put("번호",rs.getString("pr_num"));
			infoProduct.put("이름",rs.getString("pr_name"));
			infoProduct.put("카테고리",rs.getString("pr_category"));
			infoProduct.put("가격",rs.getString("pr_price"));
			infoProduct.put("판매량",rs.getString("pr_buy"));
			infoProduct.put("재고",rs.getString("pr_stock"));
			infoProduct.put("색상",rs.getString("pr_color"));
			infoProduct.put("추가정보",rs.getString("pr_info"));
			infoProduct.put("상태",rs.getString("pr_status"));
			infoProduct.put("이미지",rs.getString("pr_img"));
			infoProduct.put("상세정보",rs.getString("pr_detail_img"));
		}
		conn.close();
	} catch (SQLException | ClassNotFoundException e) {
		System.out.println("database input error!");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel= "stylesheet" type="text/css" href="shop_css.css">
<title>제품 상세 정보</title>
</head>
<body>
	<div align="center">
	<form action="BascketAdd.jsp" method="get">
		<table class="board_table">
			<tr>
				<td rowspan="5" width=50 style="word-break:break-all">
					<img alt=""  src="<%=infoProduct.get("이미지")%>" width="300" height="300">
				</td>
			</tr>
			<tr>	<td align="center" colspan="2">제품명 : <%=infoProduct.get("이름")%></td></tr>
			<tr>	<td align="center" colspan="2">제품 가격 : <%=infoProduct.get("가격")%></td></tr>
			<tr>	<td align="center" colspan="2">수량 : <input type="text" style="ime-mode:disabled;" name="orderAmount"></td></tr>
			<tr>
				<td align="center" width="60" style="word-break:break-all">
					<input type="button" name="" value="바로구매하기" class="btn green mini"/>
				</td>
				<td align="center" width=50 style="word-break:break-all">
					<input type="submit" value="장바구니 담기" class="btn green mini"/>		
					<input type="hidden"  name="bascketNumber" value="<%=productNumber%>"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<img src="<%=infoProduct.get("상세정보")%>">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>