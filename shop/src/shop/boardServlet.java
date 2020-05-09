package shop;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.lang.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


/**
 * Servlet implementation class boardServlet
 */
@WebServlet("/boardServlet")
public class boardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String bo_title;
		String bo_content;
		int mem_num;
		String mem_email;
		String bo_img = "";
		String bo_category;
		String bo_password;
		
		Boolean isConnected = false;
		DB_Mgr mDB_Mgr= DB_Mgr.getDB_Mgr();
		isConnected = mDB_Mgr.DBConnection();
		
		////////////////////////// 첨부파일 서버 저장	///////////////////////////////////
		String realFolder ="";
		// 업로드용 폴더 이름
		String saveFolder ="upload";
		String engType ="UTF-8";
		int maxSize =5*1024*1024;	// 5MByte
		
		// jspMain2-context 라고 한다. 서버에서 (서블릿) 어디에  어느 폴더에서 서블릿으로 변환 되나?
		ServletContext context = this.getServletContext();
		
		// 서블릿상의 upload폴더 경로를 알아온다.
		//realFolder = context.getRealPath(saveFolder);
		//realFolder = "image";
		String path = request.getSession().getServletContext().getRealPath(saveFolder);
		realFolder = path;
		
		// 콘솔/ 브라우저에 실제 경로를 출력
		System.out.println("실제 서블릿 상 경로 : " +realFolder);
		
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(
					request,
					realFolder,
					maxSize,
					engType,
					new DefaultFileRenamePolicy()
				);
			
			bo_title = (String) multi.getParameter("bo_title");		
			bo_content = (String) multi.getParameter("bo_content");
			bo_password = (String) multi.getParameter("bo_password");
			bo_category =  (String) multi.getParameter("bo_category");
			mem_num = Integer.parseInt((String)multi.getParameter("mem_num"));
			mem_email = (String) multi.getParameter("mem_email");
			
			try {				
				// 전송됨 파일이름 fileName1, fileName2 를 가져온다
				Enumeration en = multi.getFileNames();
				
				while(en.hasMoreElements()) {
					String name = (String) en.nextElement();
					// name 파라미터에는 file의 이름이 들어있다.
					// 그 이름을 주면 실제 값(업로드 "할" file)을 가져온다.
					String originFile = multi.getOriginalFileName(name);
								
					// 만약 업로드 폴더에 똑같은 파일이 있으면 현재 올리는 파일 이름은 바뀐다. (중복정책)
					// 그래서 시스템에 있는 이름을 알려준다.
					String systemFile = multi.getFilesystemName(name);
								
					// 전송된 파일의 타입 - MIME 타입 (기계어, image, HTML, text,...)
					String fileType = multi.getContentType(name);
								
					// 문자열 "파일 이름"이 name에 들어온 상태
					// 문자열 파일 이름을 통해 실제 파일 객체를 가져온다								
					File file = multi.getFile(name);		// java.io
								
					if(file != null) {
						System.out.println("크기 : " + file.length() + "byte");
					}
					System.out.println("file.getPath : " + file.getPath());
					bo_img = saveFolder + "\\\\" + systemFile;
				}
			} catch (Exception e) {
				System.out.println("error : " + e.getMessage());
				System.out.println("파일 처리 간 문제 발생");
				if(multi.getParameter("bo_img") != null) {
					bo_img = multi.getParameter("bo_img");
					bo_img = bo_img.replace("\\", "\\\\");
				}
			}
			
			if(isConnected) {
				if(multi.getParameter("submit") != null && multi.getParameter("submit").equals("등록")) {
					String [] stringfieldName = {"bo_title", "bo_content", "mem_email", "bo_img", "bo_category", "bo_password"};
					String [] stringfieldValue = {bo_title, bo_content, mem_email, bo_img, bo_category, bo_password};
					String [] intfieldName = {"mem_num"};
					int [] intfieldValue = {mem_num};
					mDB_Mgr.DBTable_Insert("shop.board", stringfieldName, stringfieldValue, intfieldName, intfieldValue);
//					mDB_Mgr.DBTable_Insert(bo_title, bo_content, mem_num, mem_email, bo_img, bo_category, bo_password);
				}
				
				if(multi.getParameter("modify") != null && multi.getParameter("modify").equals("수정")) {
					int bo_num = Integer.parseInt((String) multi.getParameter("bo_num"));
					mDB_Mgr.DBTable_Update(bo_num, bo_title, bo_content, mem_num, mem_email, bo_img, bo_category, bo_password);
				}
			}

			
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
		}

		response.sendRedirect("Main.jsp?page=boardViewer");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
