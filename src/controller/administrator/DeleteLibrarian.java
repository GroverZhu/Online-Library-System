package controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LibrarianDAO;

/**
 * 通过Librarian的名字与密码删除一个librarian
 * 
 * @author GroverZhu
 *
 */
public class DeleteLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteLibrarian() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		int librarianId = Integer.valueOf(request.getParameter("librarianId"));
		String librarianName = request.getParameter("librarianName");

		LibrarianDAO libDao = new LibrarianDAO();

		int flag = libDao.deleteLibrarianByIdName(librarianId, librarianName);

		if (flag == 1) {
			String msg = "Delete the Librarian Successfully!";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);
		} else {
			String msg = "Delete the Librarian Failed! The Librarian May Has Some Operations In this Library Or the Name & ID Not Correct!";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);
		}
	}

}
