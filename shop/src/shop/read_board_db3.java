package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class read_board_db3
 */
@WebServlet("/read_board_db3")
public class read_board_db3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public read_board_db3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		DB_Mgr mDBManager = DB_Mgr.getDB_Mgr();
		Boolean isConnected = false;
		isConnected = mDBManager.DBConnection();
		Board mBoard= null;
		
		int bo_num = 0;
		bo_num = Integer.parseInt((String) request.getParameter("bo_num"));
		String page ="Main.jsp";
		
		try {
			if(request.getParameter("remove") != null && request.getParameter("remove").equals("remove")) {
				mDBManager.DBTable_Delete(bo_num);				
				page= "Main.jsp?page=boardViewer";
			}
			
			if(request.getParameter("modify") != null && request.getParameter("modify").equals("modify")) {
				if(isConnected == true) {
					mBoard = (Board) mDBManager.DBTable_Select(bo_num);
					request.setAttribute("bo_num", mBoard.bo_num);
					request.setAttribute("bo_title", mBoard.bo_title);
					request.setAttribute("bo_content", mBoard.bo_content);
					request.setAttribute("bo_img", mBoard.bo_img);					
					page= "Main.jsp?page=boardFormModify";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			System.out.println(e.getMessage());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
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
