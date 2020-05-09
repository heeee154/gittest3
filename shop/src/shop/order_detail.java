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

/**
 * Servlet implementation class order_detail
 */
@WebServlet("/order_detail")
public class order_detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public order_detail() {
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
		String ordernumber = request.getParameter("or_num");
		String query_select = "select * from shop.order where mem_num = "+mem_num+" and or_number = "+ ordernumber+";";
		String query_order = "select distinct (or_number) from shop.order where mem_num="+mem_num+";";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","1234");
			System.out.println("DB connect OK");
			stmt = conn.createStatement();
			
			String print_num = null;
			String print_pro = "";
			String print_pay = "";
			String print_amount = "";
			String print_type = "";
			String msg;
			msg = "<table class='board_table'><tr><td>주문번호	</td><td>상품명</td><td>금액</td><td>수량</td><td>지불방법</td></tr>";
			
				query_select = "select * from shop.order where mem_num = "+mem_num+" and or_number = "+ ordernumber+";";
				rs = stmt.executeQuery(query_select);
				while(rs.next()) {
					print_num = rs.getString("or_number");
					print_pro = rs.getString("pr_name")+"\n";
					print_pay = rs.getString("or_price")+"\n";
					print_amount = rs.getString("or_amount")+"\n";
					print_type=rs.getString("or_pay_type")+"\n";
					msg += "<tr><td>"+print_num+"</td><td>"+print_pro+"</td><td>"+print_pay+"</td><td>"+print_amount+"</td><td>"+print_type+"</td></tr>";
				}
				msg +="</table>";
			session.setAttribute("msg", msg);
		}catch(Exception e) {
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp?page=order_detail2");
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
