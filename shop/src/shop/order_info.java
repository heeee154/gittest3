package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.login_mem;

/**
 * Servlet implementation class order_info
 */
@WebServlet("/order_info")
public class order_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public order_info() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean connect = false;
		
		login_mem mem = (login_mem) session.getAttribute("mem_info");
		int mem_num = mem.getMem_num();
		ArrayList<String> order_number = new ArrayList<String>();
		String ordernumber = "";
		String query_select = "select * from shop.order where mem_num = "+mem_num+" and or_number = "+ ordernumber+";";
		String query_count = "select count(*) from shop.order where mem_num = "+mem_num+" and or_number = "+ ordernumber+";";
		String query_sum = "select sum(or_price) from shop.order where mem_num ="+mem_num+" and or_number = "+ ordernumber+";";
		String query_order = "select distinct (or_number) from shop.order where mem_num="+mem_num+";";
		int count = 0;
		int total = 0;
		String print_order = "<table class='board_table'><tr><td>주문번호	</td><td>상품명</td><td>총금액</td><td>	일자</td></tr>";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","1234");
			System.out.println("DB connect OK");
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query_order);
			while(rs.next()) {
				order_number.add(rs.getString(1));
			}
			for(int i = 0 ; i < order_number.size(); i ++) {
				ordernumber = order_number.get(i);
				query_count = "select count(*) from shop.order where mem_num = "+mem_num+" and or_number = "+ ordernumber+";";
				rs = stmt.executeQuery(query_count);
				while(rs.next()) {
					count = rs.getInt(1);
				}
				query_sum = "select sum(or_price) from shop.order where mem_num ="+mem_num+" and or_number = "+ ordernumber+";";
				rs = stmt.executeQuery(query_sum);
				while(rs.next()) {
					total = rs.getInt(1);
				}
				query_select = "select * from shop.order where mem_num = "+mem_num+" and or_number = "+ ordernumber+";";
				rs = stmt.executeQuery(query_select);
				
				if(count ==1) {
					while(rs.next()) {
						String datetime = rs.getString("or_datetime");
						String ordernum = rs.getString("or_number");
						String orderinfo = rs.getString("pr_name");
						print_order +=
								"<tr><td><a href= 'Main.jsp?page=order_detail&or_num="+ordernumber+"'>"+ordernum+"</a></td><td>"+orderinfo+"</td><td>"+total+"</td><td>"+datetime+"</td></tr>";
					}	
					session.setAttribute("list", print_order);
				}else if(count ==0) {
						print_order += 
								"<tr><td colspan='4'>주문내역이 없습니다.</td></tr>";
						session.setAttribute("list", print_order);
				}else {
					while(rs.next()) {
						String datetime = rs.getString("or_datetime");
						String ordernum = rs.getString("or_number");
						String orderinfo = rs.getString("pr_name");
						
						print_order += 
										"<tr><td><a href='Main.jsp?page=order_detail&or_num="+ordernumber+"'>"+ordernum+"</a></td><td>"+orderinfo+" 외 "+(count-1)+"개 </td><td>"+total+"</td><td>"+datetime+"</td></tr>";
						break;
					}
				}
			}
			print_order +="</table>";
			
			session.setAttribute("list", print_order);
			
		}catch(Exception e) {
			                             
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp?page=mypage");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
