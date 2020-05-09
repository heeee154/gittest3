package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class editprofile
 */
@WebServlet("/editprofile")
public class editprofile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editprofile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("mem_name");
		String tel = request.getParameter("mem_tel");
		String pw = request.getParameter("mem_pw");
		String id = request.getParameter("mem_id");
		String address = request.getParameter("mem_address");
		String email = request.getParameter("mem_email");
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
		
		String query_update = "update shop.member set mem_pw ='"+pw
								+"', mem_name = '"+name
								+"', mem_address = '"+address
								+"', mem_tel = '"+tel
								+"', mem_email = '"+email 
								+ "' where mem_id = '"+id+"';";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","1234");
			System.out.println("DB connect OK");
			stmt = conn.createStatement();
			stmt.executeUpdate(query_update);
		
		}catch(Exception e) {
			
		}
		out.println("<script>");
		out.println("alert('정보가 변경되었습니다.')"); 
		out.println("location.href = 'Main.jsp'"); 
		out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
