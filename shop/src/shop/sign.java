package shop;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class sign
 */
@WebServlet("/sign")
public class sign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sign() {
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
		
		HttpSession session = request.getSession();
		
		String mem_id = request.getParameter("mem_id");
		String mem_email = request.getParameter("mem_email");
		String mem_name = request.getParameter("mem_name");
		String mem_pw = request.getParameter("mem_pw");
		String pw_ok = request.getParameter("pw_ok");
		String mem_tel = request.getParameter("mem_tel");
		String mem_address = request.getParameter("mem_address");
		
		Connection conn = null;
    	Statement stmt = null;
    	PreparedStatement pstmp = null;
    	ResultSet rs = null;
    	Boolean connect = false;
    	
    	ArrayList<String> name = new ArrayList<String>();   
    	HttpSession session_id = request.getSession();
    	
;    	String query_select =  "select mem_id from shop.member";
    	String query_insert = "INSERT INTO shop.member VALUES(NULL,"
    			+ "'" +mem_id + "',"
    			+ "'"+mem_pw+"',"
    			+"'"+mem_name+"',"
    			+"'"+mem_address+"',"
    			+"'"+mem_tel+"',"
    			+"'"+mem_email+"');";
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","1234");
    		System.out.println("DB connect OK");
    		stmt = conn.createStatement();
        	
    		if(request.getParameter("sign_ok")!=null) {
    				//세션에 값 넣어주기
    				stmt.executeUpdate(query_insert);
    				session.setAttribute("id", mem_id);
    				session.setAttribute("email", mem_email);
    				session.setAttribute("name", mem_name);
    				session.setAttribute("pw", mem_pw);
    				session.setAttribute("phone_num", mem_tel);
    				session.setAttribute("address", mem_address);
    					
    				RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp");
    				dispatcher.forward(request, response);
    			}
    		if(request.getParameter("cancel")!=null){
    			//다시 메인화면으로 이동
    			RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp");
    			dispatcher.forward(request, response);
    		}
    		
    	}catch(Exception e){
    		System.out.println("error");
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
