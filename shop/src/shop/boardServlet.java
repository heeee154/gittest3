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
		
		////////////////////////// ÷������ ���� ����	///////////////////////////////////
		String realFolder ="";
		// ���ε�� ���� �̸�
		String saveFolder ="upload";
		String engType ="UTF-8";
		int maxSize =5*1024*1024;	// 5MByte
		
		// jspMain2-context ��� �Ѵ�. �������� (����) ���  ��� �������� �������� ��ȯ �ǳ�?
		ServletContext context = this.getServletContext();
		
		// �������� upload���� ��θ� �˾ƿ´�.
		//realFolder = context.getRealPath(saveFolder);
		//realFolder = "image";
		String path = request.getSession().getServletContext().getRealPath(saveFolder);
		realFolder = path;
		
		// �ܼ�/ �������� ���� ��θ� ���
		System.out.println("���� ���� �� ��� : " +realFolder);
		
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
				// ���۵� �����̸� fileName1, fileName2 �� �����´�
				Enumeration en = multi.getFileNames();
				
				while(en.hasMoreElements()) {
					String name = (String) en.nextElement();
					// name �Ķ���Ϳ��� file�� �̸��� ����ִ�.
					// �� �̸��� �ָ� ���� ��(���ε� "��" file)�� �����´�.
					String originFile = multi.getOriginalFileName(name);
								
					// ���� ���ε� ������ �Ȱ��� ������ ������ ���� �ø��� ���� �̸��� �ٲ��. (�ߺ���å)
					// �׷��� �ý��ۿ� �ִ� �̸��� �˷��ش�.
					String systemFile = multi.getFilesystemName(name);
								
					// ���۵� ������ Ÿ�� - MIME Ÿ�� (����, image, HTML, text,...)
					String fileType = multi.getContentType(name);
								
					// ���ڿ� "���� �̸�"�� name�� ���� ����
					// ���ڿ� ���� �̸��� ���� ���� ���� ��ü�� �����´�								
					File file = multi.getFile(name);		// java.io
								
					if(file != null) {
						System.out.println("ũ�� : " + file.length() + "byte");
					}
					System.out.println("file.getPath : " + file.getPath());
					bo_img = saveFolder + "\\\\" + systemFile;
				}
			} catch (Exception e) {
				System.out.println("error : " + e.getMessage());
				System.out.println("���� ó�� �� ���� �߻�");
				if(multi.getParameter("bo_img") != null) {
					bo_img = multi.getParameter("bo_img");
					bo_img = bo_img.replace("\\", "\\\\");
				}
			}
			
			if(isConnected) {
				if(multi.getParameter("submit") != null && multi.getParameter("submit").equals("���")) {
					String [] stringfieldName = {"bo_title", "bo_content", "mem_email", "bo_img", "bo_category", "bo_password"};
					String [] stringfieldValue = {bo_title, bo_content, mem_email, bo_img, bo_category, bo_password};
					String [] intfieldName = {"mem_num"};
					int [] intfieldValue = {mem_num};
					mDB_Mgr.DBTable_Insert("shop.board", stringfieldName, stringfieldValue, intfieldName, intfieldValue);
//					mDB_Mgr.DBTable_Insert(bo_title, bo_content, mem_num, mem_email, bo_img, bo_category, bo_password);
				}
				
				if(multi.getParameter("modify") != null && multi.getParameter("modify").equals("����")) {
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
