<%@page import="shop.login_mem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	login_mem mLogin_mem = (login_mem) session.getAttribute("mem_info");
	boolean check_admin = true;
	if (mLogin_mem == null || !mLogin_mem.getMem_id().equals("admin")) {
		check_admin = false;
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productForm.jsp</title>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		var check_admin = <%=check_admin%>;
		if(!check_admin) {
			alert("관리자만 사용가능합니다.");
			location.href ="Main.jsp";
		}
			
		function check_form(){					
			if($("#pr_name").val().length==0){
				alert("상품 이름을 입력하세요."); 
				$("#pr_name").focus();
				return false; 
			}
			if($("#pr_category").val().length==0){ 
				alert("카테고리를 선택하세요."); 
				$("#pr_category").focus(); 
				return false; 
			}
			if($("#pr_price").val().length==0){ 
				alert("가격을 입력하세요."); 
				$("#pr_price").focus();
				return false; 
			}
			if( isNaN(parseInt($("#pr_price").val()))){ 
				alert("가격을 숫자로 입력하세요.")
				$("#pr_price").focus();
				return false; 
			}
			
			if( isNaN(parseInt($("#pr_stock").val()))){ 
				alert("수량을 숫자로 입력하세요.")
				$("#pr_stock").focus();
				return false; 
			}
			if($("#pr_img").val().length==0){ 
				alert("이미지를 등록하세요."); 
				$("#pr_img").focus();
				return false; 
			}
			if($("#pr_info").val().length > 1000){ 
				alert("상세정보 입력 길이를 초과했습니다."); 
				$("#pr_info").focus();
				return false; 
			}
			return true;
		}	
	</script>
</head>
<body>
	<div align="center">
		<form action="productServlet" method="post" enctype="Multipart/form-data" onsubmit="return check_form()">
			<table>
				<tr>
					<td>상품이름 : </td>
					<td>
						<input type="text" name="pr_name" id="pr_name" />
					</td>
				</tr>				
				<tr>
					<td>카테고리 : </td>
					<td>
						<select name="pr_category" id="pr_category" >
							<option value="">카테고리 선택</option>
							<option value="가요">가요</option>
							<option value="pop">pop</option>
							<option value="classic">classic</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>
						<input type="text" name="pr_price" id="pr_price"/>
					</td>
				</tr>
				<tr>
					<td>재고수량 : </td>
					<td>
						<input type="text" name="pr_stock" id="pr_stock"/>
					</td>
				</tr>
				<tr>
					<td>색상 : </td>
					<td>
						<select name="pr_color" id="pr_color" >
							<option value="">색상 선택</option>
							<option value="red">red</option>
							<option value="green">green</option>
							<option value="blue">blue</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>상세 정보 : </td>
					<td>
						<textarea rows="20" cols="80" id="pr_info" name="pr_info"></textarea>
					</td>
				</tr>
				<tr>
					<td>제품 상태 : </td>
					<td>
						<select name="pr_status" id="pr_status" >
							<option value="">제품 상태 선택</option>
							<option value="default">default</option>
							<option value="pre_order">pre_order</option>
							<option value="sale">sale</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>상품 이미지 첨부 : </td>
					<td><input type="file" name="pr_img" id="pr_img"/></td>
				</tr>
				<tr>
					<td>상품 상세정보 이미지 첨부 : </td>
					<td><input type="file" name="pr_detail_img" id="pr_detail_img"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="reset" value="reset" class="btn green mini"/>
						<input type="submit" name="submit" id="submit" value="등록" class="btn green mini"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>