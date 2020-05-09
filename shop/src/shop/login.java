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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("id");
		String mem_pw = request.getParameter("pw");
		login_mem mem = new login_mem();
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();	
		
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<String> pw = new ArrayList<String>();
		HttpSession session = request.getSession();
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean connect = false;
		String query_select_id = "select mem_id from shop.member  order by mem_num";
		String query_select_pw = "select mem_pw from shop.member order by mem_num";
		id.clear();
		pw.clear();
		
		try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","1234");
    		System.out.println("DB connect OK");
    		try {
    			stmt = conn.createStatement();
    			rs = stmt.executeQuery(query_select_id);
    			while(rs.next()) {
    				id.add(rs.getString(1));
    			}
    			rs = stmt.executeQuery(query_select_pw);
    			while(rs.next()) {
    				pw.add(rs.getString(1));
    			}
    				
    		} catch(Exception e) {
    			System.out.println("error!");
    		}
    		
    		
    	}catch(Exception e){
    		System.out.println("error");
    	}
		int a = 0;
		for(int i = 0; i < id.size(); i ++) {
			if(id.get(i).equals(mem_id) ) {
				a++;
				if(pw.get(i).equals(mem_pw)) {
					a++;
					String query_selectid = "select * from shop.member where mem_id='"+id.get(i)+"';";
					try {
						stmt = conn.createStatement();
						rs = stmt.executeQuery(query_selectid);
						while(rs.next()) {
							mem.mem_num = rs.getInt("mem_num");
							mem.mem_id = rs.getString("mem_id");
							mem.mem_pw = rs.getString("mem_pw");
							mem.mem_name = rs.getString("mem_name");
							mem.mem_address = rs.getString("mem_address");
							mem.mem_tel = rs.getString("mem_tel");
							mem.mem_email = rs.getString("mem_email");
						}
						session.setAttribute("mem_info", mem);
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
				}
			}else {
			}
		}
		
		if(a == 2) {
			out.println("<script>");
			out.println("alert('로그인되었습니다.')"); 
			out.println("location.href = 'Main.jsp'"); 
			out.println("</script>");
		}
		else if(a == 1) {
			out.println("<script>");
			out.println("alert('비밀번호를 확인하여 주세요.')"); 
			out.println("location.href = ' Main.jsp?page=login'"); 
			out.println("</script>");
		}
		else if(a == 0) {
			out.println("<script>");
			out.println("alert('가입되지 않은 아이디 입니다.');"); 
			out.println("location.href = ' Main.jsp?page=login'"); 
			out.println("</script>");
		}				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
