package controller.administrator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LibrarianDAO;

/**
 * 增加一个librarian，根据name跟password，添加成功后，则返回数据库自动生成的ID
 * 
 * @author GroverZhu
 *
 */
public class AddLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddLibrarian() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("librarianName");
		String password = util.SecurityUtil.md5(request.getParameter("password"));
		LibrarianDAO lib = new LibrarianDAO();
		int libId = lib.addLibrarian(name, password, "unlock");
		if (libId != -1) {
			String msg = "Add Librarian Successfully! The Librarian ID is : " + libId;
			request.setAttribute("message", msg);
			request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);
		} else {

			String msg = "Add Librarian Failed, Please Try Again!";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);
		}
	}

}
