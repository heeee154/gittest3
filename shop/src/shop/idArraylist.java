package shop;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class arraylist
 */
@WebServlet("/idArraylist")
public class idArraylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public idArraylist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ArrayList<String> name = new ArrayList<String>();
		
		HttpSession session = request.getSession();
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean connect = false;
		String query_select = "select mem_id from shop.member";
		
		name.clear();
		try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","1234");
    		System.out.println("DB connect OK");
    		try {
    			stmt = conn.createStatement();
    			rs = stmt.executeQuery(query_select);
    			while(rs.next()) {
    				name.add(rs.getString(1));
    			}
    				
    		} catch(Exception e) {
    			System.out.println("error!");
    		}
    		
    		
    	}catch(Exception e){
    		System.out.println("error");
    	}
		
		session.setAttribute("idArrayList", name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp?page=signUp");
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
