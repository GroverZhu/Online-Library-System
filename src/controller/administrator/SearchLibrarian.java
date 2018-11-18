package controller.administrator;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LibrarianDAO;
import entity.Librarian;

/**
 * Servlet implementation class SearchLibrarian
 */
public class SearchLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchLibrarian() {
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

		String option = request.getParameter("style");
		if (option.equals("librarianId")) {
			int id = Integer.valueOf(request.getParameter("name"));
			ArrayList<Librarian> libs = new LibrarianDAO().getLibrarianListById(id);
			if (libs != null && libs.size() != 0) {
				request.setAttribute("libs", libs);
				request.getRequestDispatcher("adminSearchLibrarian.jsp").forward(request, response);
			} else {
				String msg = "The Librarian ID Not Exist, Please Try Other Librarian ID!";
				request.setAttribute("message", msg);
				request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);
			}

		}

		if (option.equals("librarianName")) {
			String name = request.getParameter("name");
			ArrayList<Librarian> libs = new LibrarianDAO().getLibrarianListByName(name);
			if (libs != null && libs.size() != 0) {
				request.setAttribute("libs", libs);
				request.getRequestDispatcher("adminSearchLibrarian.jsp").forward(request, response);
			} else {
				String msg = "The Librarian Name Not Exist, Please Try Other Librarian Name!";
				request.setAttribute("message", msg);
				request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);
			}

		}
	}
}
