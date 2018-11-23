package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;

public class LibrarianDeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LibrarianDeleteBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int bookId = Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookDAO = new BookDAO();
		int tag = bookDAO.deleteBookById(bookId);
		if (tag == 1) {
			out.print("<script language='javascript'>" + "alert('Success to Delete Book!');"
					+ "window.location.href='librarianDeleteBook.jsp';" + "</script>");
		} else if (tag == 2) {
			out.print("<script language='javascript'>" + "alert('Fail to Delete Book! State is not inlib!');"
					+ "window.location.href='librarianDeleteBook.jsp';" + "</script>");
		} else if (tag == 3) {
			out.print("<script language='javascript'>" + "alert('Fail to Delete Book!This book doesn't EXIST');"
					+ "window.location.href='librarianDeleteBook.jsp';" + "</script>");
		} else if (tag == 4) {
			out.print("<script language='javascript'>" + "alert('Fail to Delete Book! Suffers Exceptoin!');"
					+ "window.location.href='librarianDeleteBook.jsp';" + "</script>");
		}

	}

}
