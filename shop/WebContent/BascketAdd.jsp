<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	
		ArrayList<Integer> numberList;
		ArrayList<Integer> amountList;
	
		String a = request.getParameter("orderAmount");
		int orderAmount = Integer.parseInt(a);
		String b = request.getParameter("bascketNumber");
		int bascketNumber = Integer.parseInt(b);
		
		//장바구니 상품 수
		if(session.getAttribute("orderAmount") == null){
			amountList = new ArrayList<Integer>();
		} else {
			amountList = (ArrayList<Integer>) session.getAttribute("orderAmount");
		}
		
		amountList.add(orderAmount);
		session.setAttribute("orderAmount", amountList);
		
		//장바구니 상품 번
		if(session.getAttribute("bascketNumber") == null){
			numberList = new ArrayList<Integer>();
		} else {
			numberList = (ArrayList<Integer>) session.getAttribute("bascketNumber");
		}
		
		numberList.add(bascketNumber);
		session.setAttribute("bascketNumber", numberList);
	%>
	<script type="text/javascript">
		alert("장바구니에 추가되었습니다.");
		history.back();
	</script>
</body>
</html>