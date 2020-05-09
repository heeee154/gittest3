package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class searchPw
 */
@WebServlet("/searchPw")
public class searchPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchPw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String tel = request.getParameter("num");
		String id = request.getParameter("id");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ArrayList<String> mem_name = new ArrayList<String>();
		ArrayList<String> mem_tel = new ArrayList<String>();
		ArrayList<String> mem_id = new ArrayList<String>();
		ArrayList<String> mem_pw = new ArrayList<String>();
		
		HttpSession session = request.getSession();
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean connect = false;
		String query_select_name = "select mem_name from shop.member order by mem_num";
		String query_select_tel = "select mem_tel from shop.member order by mem_num";
		String query_select_id = "select mem_id from shop.member order by mem_num";
		String query_select_pw = "select mem_pw from shop.member order by mem_num";
		mem_name.clear();
		mem_tel.clear();
		
		try{
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","1234");
    		System.out.println("DB connect OK");
    		try {
    			stmt = conn.createStatement();
    			rs = stmt.executeQuery(query_select_name);
    			while(rs.next()) {
    				mem_name.add(rs.getString(1));
    			}
    			rs = stmt.executeQuery(query_select_tel);
    			while(rs.next()) {
    				mem_tel.add(rs.getString(1));
    			}
    			rs = stmt.executeQuery(query_select_id);
    			while(rs.next()) {
    				mem_id.add(rs.getString(1));
    			}
    			rs = stmt.executeQuery(query_select_pw);
    			while(rs.next()) {
    				mem_pw.add(rs.getString(1));
    			}
    				
    		} catch(Exception e) {
    			System.out.println("error!");
    		}
    		
    		
    	}catch(Exception e){
    		System.out.println("error");
    	}
		if(name==""||id==""||tel=="") {
			if(name =="") {
				out.println("<script>");
				out.println("alert('Name�Է¶��� ����ֽ��ϴ�. Ȯ���� �ּ���.')");  
				out.println("location.href = 'Main.jsp?page=searchpw'"); 
				out.println("</script>");
			}
			else if(id=="") {
				out.println("<script>");
				out.println("alert('ID�Է¶��� ����ֽ��ϴ�. Ȯ���� �ּ���.')");  
				out.println("location.href = 'Main.jsp?page=searchpw'"); 
				out.println("</script>");
				
			}else if(tel=="") {
				out.println("<script>");
				out.println("alert('Phone number�Է¶��� ����ֽ��ϴ�. Ȯ���� �ּ���.')");  
				out.println("location.href = 'Main.jsp?page=searchpw'"); 
				out.println("</script>");
				
			}
		}else {
			for(int i= 0; i < mem_name.size(); i ++) {
				if(mem_name.get(i).equals(name)) {
					if(mem_tel.get(i).equals(tel)&&mem_id.get(i).equals(id)) {//�´� ��ġ
							String msg = "��й�ȣ�� "+mem_pw.get(i)+"�Դϴ�.";
							out.println("<script>");
							out.println("alert('"+msg+"')");  
							out.println("location.href = 'Main.jsp?page=login'"); 
							out.println("</script>");
							break;
							
						}
					//�̸��� ������ ��ȭ��ȣ�� Ʋ���� ���̵�� ����
					else if(!mem_tel.get(i).equals(tel) && mem_id.get(i).equals(id)) {
						out.println("<script>");
						out.println("alert('ID�� ��ġ���� �ʽ��ϴ�.')");  
						out.println("location.href = 'Main.jsp?page=searchpw'"); 
						out.println("</script>");
							break;
					}//�̸� ������ ���̵�� Ʋ���� ��ȣ�� ����
					else if(mem_tel.get(i).equals(tel) && !mem_id.get(i).equals(id)){
						out.println("<script>");
						out.println("alert('Phone number�� ��ġ���� �ʽ��ϴ�.')");  
						out.println("location.href = 'Main.jsp?page=searchpw'"); 
						out.println("</script>");
							break;
						}
					else {
						out.println("<script>");
						out.println("alert('ID�� Phone number�� ��ġ���� �ʽ��ϴ�.')");  
						out.println("location.href = 'Main.jsp?page=searchpw'"); 
						out.println("</script>");
							break;
						}
					}else if(!mem_name.get(i).equals(name)) {
						out.println("<script>");
						out.println("alert('�������� �ʴ� ȸ�������Դϴ�.')");  
						out.println("location.href = 'Main.jsp?page=login'"); 
						out.println("</script>");
							break;
					}
				}
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
