package controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LibrarianDAO;
import entity.Librarian;

/**
 * 在把指定的Librarian的所有信息显示出来后将修改Librarian的信息
 * 
 * @author GroverZhu
 *
 */
public class AdminModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminModify() {
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
		response.setCharacterEncoding("UTF-8");

		int id = Integer.valueOf(request.getParameter("librarianId"));
		String name = request.getParameter("librarianName");
		String password = request.getParameter("password");
		String state = request.getParameter("state");

		Librarian librarian = new LibrarianDAO().getLibrarianById(id);
		// 判断密码是否被更改
		if (password == null || password.equals("")) {
			password = librarian.getPassword();
		} else {
			password = util.SecurityUtil.md5(password);
		}

		int flag = new LibrarianDAO().updateLibrarian(id, name, password, state);

		if (flag == 1) {
			String msg = "Modify The Librarian Successfully!";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);
		} else {
			String msg = "Modify the Librarian Failed!, Please Try Again!";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);

		}
	}

}
