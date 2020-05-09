package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class read_board_db2
 */
@WebServlet("/read_board_db2")
public class read_board_db2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public read_board_db2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		DB_Mgr mDBManager = DB_Mgr.getDB_Mgr();
		Boolean isConnected = false;
		isConnected = mDBManager.DBConnection();

		int bo_num = 0;
		bo_num = Integer.parseInt((String) request.getParameter("bo_num"));
		String bo_password = null;

		Board mBoard = null;
		String show_content = "<table>";
		try {
			if (isConnected == true) {
				mBoard = (Board) mDBManager.DBTable_Select(bo_num);
				bo_password = mBoard.bo_password;

				show_content += "<tr><td>" + mBoard.bo_num + "</td></tr>";
				show_content += "<tr><td>" + mBoard.bo_title + "</td></tr>";
				show_content += "<tr><td>" + mBoard.mem_num + "</td></tr>";
				show_content += "<tr><td>" + mBoard.bo_date + "</td></tr>";
				String tmp = mBoard.bo_content.replace("\r\n", "<br>");
				show_content += "<tr><td>" + tmp + "</td></tr>";
				show_content += "<tr><td><img src='" + mBoard.bo_img + "'/></td><tr><td>";
				show_content += "</tr>";
			}
		} catch (Exception e) {
			e.getMessage();
			System.out.println(e);
		}
		show_content += "	</table>";
		HttpSession session = request.getSession();
		session.setAttribute("show_content", show_content);

		RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp?page=boardViewer4");
		request.setAttribute("bo_num", bo_num);
		request.setAttribute("bo_password", bo_password);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
