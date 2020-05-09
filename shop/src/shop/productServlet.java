package shop;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class productServlet
 */
@WebServlet("/productServlet")
public class productServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String pr_name;
		String pr_category;
		int pr_price;
		int pr_stock;
		String pr_color;
		String pr_info;
		String pr_status;
		String pr_img ="";
		String pr_detail_img ="";
		
		Boolean isConnected = false;
		DB_Mgr mDB_Mgr= DB_Mgr.getDB_Mgr();
		isConnected = mDB_Mgr.DBConnection();
		
		/////////////////////÷������ ���� ����///////////////////////
		String realFolder ="";
		// ���ε�� ���� �̸�
		String saveFolder ="product";
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
		System.out.println("���� ���� �� ��� : " + realFolder);

		MultipartRequest multi = null;

		try {
			multi = new MultipartRequest(
					request,
					realFolder,
					maxSize,
					engType,
					new DefaultFileRenamePolicy()
				);
			
			pr_name = (String) multi.getParameter("pr_name");
			pr_category = (String) multi.getParameter("pr_category");
			pr_price = Integer.parseInt((String)multi.getParameter("pr_price"));
			pr_stock = Integer.parseInt((String)multi.getParameter("pr_stock"));
			pr_color = (String) multi.getParameter("pr_color");
			pr_info = (String) multi.getParameter("pr_info");
			pr_status = (String) multi.getParameter("pr_status");
			
			try {
				
				Enumeration files = multi.getFileNames();
				
				String file1 = (String) files.nextElement();
				String systemFile1 = multi.getFilesystemName(file1);
				pr_img = saveFolder + "\\\\\\\\" + systemFile1;	// ��ǰ �̹���
				
				String file2 = (String) files.nextElement();
				String systemFile2 = multi.getFilesystemName(file2);
				pr_detail_img = saveFolder + "\\\\\\\\" + systemFile2;	// ��ǰ �� �̹���
				
			} catch (Exception e) {
				System.out.println("error : " + e.getMessage());
				System.out.println("���� ó�� �� ���� �߻�");
			}
			
			if(isConnected) {
				String [] stringfieldName = {"pr_name", "pr_category", "pr_color", "pr_info", "pr_img", "pr_detail_img"};
				String [] stringfieldValue = {pr_name, pr_category, pr_color, pr_info, pr_img, pr_detail_img};
				String [] intfieldName = {"pr_price", "pr_stock"};
				int [] intfieldValue = {pr_price, pr_stock};
				mDB_Mgr.DBTable_Insert("shop.product", stringfieldName, stringfieldValue, intfieldName, intfieldValue);
//				mDB_Mgr.DBTable_Insert(pr_name, pr_category, pr_price, pr_stock, pr_color, pr_info, pr_status, pr_img);
			}
			
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
		}
		
		response.sendRedirect("Main.jsp");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
