package shop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class read_board_db
 */
@WebServlet("/read_board_db")
public class read_board_db extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public read_board_db() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		DB_Mgr mDBManager = DB_Mgr.getDB_Mgr();
		Boolean isConnected = false;
		HttpSession session = request.getSession();
		
		int board_index =1;
		if(request.getParameter("board_index") != null) {
			board_index = Integer.parseInt(request.getParameter("board_index"));
		}
		
		int board_jump_index = 0;
		if(request.getParameter("board_jump_index") != null) {
			board_jump_index = Integer.parseInt(request.getParameter("board_jump_index"));
		}
		
		int iptNUM = 20;
		if(request.getParameter("iptNUM") != null) {
			iptNUM = Integer.parseInt(request.getParameter("iptNUM"));
		}
		
		String search_string = null;
		if(request.getParameter("search_string") != null && request.getParameter("search_string").equals("")) {
			search_string = null;
		} else if (request.getParameter("search_string") != null){
			search_string = request.getParameter("search_string");
		}

		
		int db_count=0;
		String show_board_index = null;
		String show_table = null;
		String send_page = null;
//		String send_page = "Main.jsp?page=boardViewer2&board_index="+board_index + "&iptNUM="+ iptNUM;
		
		if(search_string == null) {
			show_board_index = "<div><table><tr>"; // table 형태로 수정
			
			isConnected = mDBManager.DBConnection();
			if(isConnected == true) {
				db_count = mDBManager.DBTable_Select_Count("shop.board");		// db_count == table안에 인스턴스 개수
				System.out.println("db_count : " + db_count);			// console log test	
				
				if(board_jump_index > 0 && db_count / (10* iptNUM) >= board_jump_index) {
					show_board_index += "<td><a href='Main.jsp?page=boardViewer&board_index=" + (1 + ((board_jump_index-1) * 10)) + "&board_jump_index=" + (board_jump_index-1) + "&iptNUM="+ iptNUM + "'>" + " << " + " </a></td>";
				}
				
				for(int i = 1 + (board_jump_index * 10) ; i <= (db_count/iptNUM)+1 && i <= 10 + (board_jump_index * 10) ; i++) {
					show_board_index += "<td><a href='Main.jsp?page=boardViewer&board_index=" + i + "&board_jump_index=" + board_jump_index + "&iptNUM="+ iptNUM + "'>" + i + " </a></td>";
				}			

				if( db_count / (10* iptNUM) > board_jump_index) {
					show_board_index += "<td><a href='Main.jsp?page=boardViewer&board_index=" + (1 + ((board_jump_index+1) * 10)) + "&board_jump_index=" + (board_jump_index+1) + "&iptNUM="+ iptNUM + "'>" + " >> " + " </a></td>";
				}
				
				
			}
			show_board_index += "</tr></table></div>";	// table 형태로 수정
			session.setAttribute("show_board_index", show_board_index);
			
			Board[] mBoard= null;
			show_table = "<table class='board_table'>" + 
									"<thead><tr>" + 
										"<td>No.</td>" + 
										"<td>Category</td>" + 
										"<td>Title (Subject)</td>" + 
										"<td>Writer</td>" + 
										"<td>Date</td>" + 
									"</tr></thead> <tbody>";
			
			isConnected = mDBManager.DBConnection();
			if(isConnected == true) {
				int LIMIT = (board_index-1) *iptNUM;
				mBoard = (Board[]) mDBManager.DBTable_Select("shop.board", LIMIT, iptNUM);			/// DB Select 

				for(int i = 0 ; i < mBoard.length ; i++) {
					show_table += "<tr>";
					show_table += "<td>" + mBoard[i].bo_num + "</td>";
					show_table += "<td>" + mBoard[i].bo_category + "</td>";
					show_table += "<td><a href='Main.jsp?page=boardViewer3&bo_num=" + mBoard[i].bo_num + "'>" + mBoard[i].bo_title + "</a></td>";
					show_table += "<td>" + mBoard[i].mem_num + "</td>";
					show_table += "<td>" + mBoard[i].bo_date + "</td>";
					show_table += "</tr>";
				}
				
				
			}
			show_table += "	</tbody></table>";
			send_page = "Main.jsp?page=boardViewer2&board_index="+board_index + "&board_jump_index=" + board_jump_index + "&iptNUM="+ iptNUM;
		}
		else {
			show_board_index = "<div><table><tr>"; // table 형태로 수정
			
			isConnected = mDBManager.DBConnection();
			if(isConnected == true) {
				db_count = mDBManager.DBTable_Select_Count("shop.board", search_string);
				System.out.println("db_count : " + db_count);
				
				if(board_jump_index > 0 && db_count / (10* iptNUM) >= board_jump_index) {
					show_board_index += "<td><a href=\"Main.jsp?page=boardViewer&board_index=" + (1 + ((board_jump_index-1) * 10)) + "&board_jump_index=" + (board_jump_index-1) + "&iptNUM="+ iptNUM + "&search_string=\'" + search_string + "'\" >" + " << " + " </a></td>";
				}
				
				for(int i = 1 + (board_jump_index * 10) ; i <= (db_count/iptNUM)+1 && i <= 10 + (board_jump_index * 10) ; i++) {
					show_board_index += "<td><a href=\"Main.jsp?page=boardViewer&board_index=" + i + "&board_jump_index=" + board_jump_index + "&iptNUM="+ iptNUM + "&search_string=\'" + search_string + "'\" >" + i + " </a></td>";
				}		
				
				if( db_count / (10* iptNUM) > board_jump_index) {
					show_board_index += "<td><a href=\"Main.jsp?page=boardViewer&board_index=" + (1 + ((board_jump_index+1) * 10)) + "&board_jump_index=" + (board_jump_index+1) + "&iptNUM="+ iptNUM + "&search_string=\'" + search_string + "'\">" + " >> " + " </a></td>";
				}
				
			}
			show_board_index += "</tr></table></div>";	// table 형태로 수정
			session.setAttribute("show_board_index", show_board_index);
			
			Board[] mBoard= null;
			show_table = "<table class='board_table'>" + 
									"<thead><tr>" + 
										"<td>No.</td>" + 
										"<td>Category</td>" + 
										"<td>Title (Subject)</td>" + 
										"<td>Writer</td>" + 
										"<td>Date</td>" + 
									"</tr></thead> <tbody>";
			
			isConnected = mDBManager.DBConnection();
			if(isConnected == true) {
				int LIMIT = (board_index-1) *iptNUM;
				mBoard = (Board[]) mDBManager.DBTable_Select("shop.board", LIMIT, iptNUM, search_string);			/// DB Select 

				for(int i = 0 ; i < mBoard.length ; i++) {
					show_table += "<tr>";
					show_table += "<td>" + mBoard[i].bo_num + "</td>";
					show_table += "<td>" + mBoard[i].bo_category + "</td>";
					show_table += "<td><a href='Main.jsp?page=boardViewer3&bo_num=" + mBoard[i].bo_num + "'>" + mBoard[i].bo_title + "</a></td>";
					show_table += "<td>" + mBoard[i].mem_num + "</td>";
					show_table += "<td>" + mBoard[i].bo_date + "</td>";
					show_table += "</tr>";
				}
				
				
			}
			show_table += "	</tbody></table>";
			
			send_page = "Main.jsp?page=boardViewer2&board_index="+board_index + "&board_jump_index=" + board_jump_index + "&iptNUM="+ iptNUM + "&search_string='"+ search_string + "'";
		}
		
		
		session.setAttribute("show_table", show_table);
		response.sendRedirect(send_page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
