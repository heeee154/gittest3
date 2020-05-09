<%@page import="java.sql.*" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%//DB불러오기

// ArrayList<HashMap<String, String>> product = new ArrayList<HashMap<String, String>>();

HashMap<String, HashMap<String, String>> product = new HashMap<String, HashMap<String, String>>();

Connection conn = null;
Statement stmt = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

String query_select = "select * from shop.product";



	try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("드라이버 성공");
		conn = DriverManager.getConnection("JDBC:mysql://localhost:3306/shop","root","1234");
		System.out.println("DB connect Ok");
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(query_select);
		
		while(rs.next()){
			HashMap<String, String> infoProduct = new HashMap<String, String>();
			infoProduct.put("번호",rs.getString(1));
			infoProduct.put("이름",rs.getString(2));
			infoProduct.put("카테고리",rs.getString(3));
			infoProduct.put("가격",rs.getString(4));
			infoProduct.put("판매량",rs.getString(5));
			infoProduct.put("재고",rs.getString(6));
			infoProduct.put("색상",rs.getString(7));
			infoProduct.put("추가정보",rs.getString(8));
			infoProduct.put("상태",rs.getString(9));
			infoProduct.put("이미지",rs.getString(10));
			infoProduct.put("상세정보",rs.getString(11));
			
// 			product.add(infoProduct);
			product.put(rs.getString(1), infoProduct);
			session.setAttribute("product1", product);
		}
		conn.close();
	} catch (SQLException | ClassNotFoundException e) {
		System.out.println("database input error!");
	}

//    	product = (ArrayList)session.getAttribute("product1");
	product = (HashMap<String, HashMap<String, String>>)session.getAttribute("product1");
	System.out.print("db완료");

%>
<html>
<head>
<meta charset="UTF-8">
<link rel= "stylesheet" type="text/css" href="shop_css.css">
<title>Insert title here</title>
</head>
<body>
<p align="center" style="position: static; font-style: bold;">장바구니</p>
<div align="center">
	<table class="board_table">
		<tr>
			<td align="center">상품번호</td>
			<td align="center">상품사진</td>
			<td align="center">상품명</td>
			<td align="center">가격</td>
			<td align="center">주문수량</td>
			<td align="center">총금액</td>
		</tr>
		<% if(session.getAttribute("bascketNumber") == null) {%>
				<td colspan="6" align="center">장바구니가 비어있습니다.</td>
		<%}else{
			ArrayList<Integer> numberList;
			ArrayList<Integer> amountList;
			
			numberList = (ArrayList)session.getAttribute("bascketNumber");
			amountList = (ArrayList)session.getAttribute("orderAmount");
			for(int i = 0; i < numberList.size(); i++) {%>
				<tr>
					<td align="center"><%=numberList.get(i)%></td>
					<td align="center"><img src='<%=product.get(Integer.toString(numberList.get(i))).get("이미지")%>' width="100"></td>
					<td align="center"><%=product.get(Integer.toString(numberList.get(i))).get("이름")%></td>
					<td align="center"><%=product.get(Integer.toString(numberList.get(i))).get("가격")%></td>
					<td align="center"><%=amountList.get(i)%></td>
					<td align="center"><%=Integer.parseInt((product.get(Integer.toString(numberList.get(i))).get("가격")))*(amountList.get(i)) %></td>
				</tr>
			<%}
		}%>
	</table>
</div>
<div align="center" style="margin: 20px;">
<input type="submit" value="결제하기" class="btn green mini"/>
<form action="bascketEmpty.jsp" style="margin: 20px;">
<input type="submit" value="장바구니 비우기" class="btn green mini"/>
</form>
</div>
</body>
</html>