<%-- <%@page import="java.util.HashMap"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	// 데이터베이스에서 상품 정보 불러와 리스트에 담기
	ArrayList<HashMap<String, String>> product = new ArrayList<HashMap<String, String>>();
	
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
				
				product.add(infoProduct);
				session.setAttribute("product1", product);
			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("database input error!");
		}
	
	   	product = (ArrayList<HashMap<String, String>>)session.getAttribute("product1");
		System.out.println("db완료");

		%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Store</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
	
		function productNumber(productNumber) {
			var xx = productNumber;
			var str = "detailInfo.jsp?pr_num=";
			return str+xx;

// 			location.href="Main.jsp?page=detailInfo&pr_num="+xx;
		}

		function hairPlus() {	
			<%
			String productNum = "";
			int tdNum = 0;
			for(int k = product.size()-1; 0 <= k; k--){
			%>
				var pagename = '_prtd_' + <%=k%>;
				document.write("<td align='center' id='" + pagename + "'>");
				
				var input = document.createElement("input");
				input.setAttribute("type", "image");
				input.setAttribute("style", "width: 300px;");
				input.setAttribute("src", '<%=product.get(k).get("이미지")%>');
				input.setAttribute("onclick", "window.open(productNumber(<%=product.get(k).get("번호")%>), '제품 설명', 'width=600,height=800,status=no,scrollbars=yes')");
				document.getElementById(pagename).appendChild(input);
				document.write("<br/>");
				
				var p_tag = document.createElement("input");
				p_tag.setAttribute("type", "button");
				p_tag.setAttribute("class", "nameview");
				p_tag.setAttribute("value", '<%=product.get(k).get("이름")%>');
				document.getElementById(pagename).appendChild(p_tag);
// 				document.write("</td>");
				
				<%
				tdNum += 1;
				if(tdNum==3){
				%>
					document.write("<tr>");
				<%
					tdNum = 0;
				}
				%>	
			<%}%>
			
		}

	</script>
</head>
<body>
<div align="center">
<table>
	<tr>
	<script type="text/javascript">
			hairPlus();
	</script>
	<tr>
</table>
</div>
</html>
</body> --%>