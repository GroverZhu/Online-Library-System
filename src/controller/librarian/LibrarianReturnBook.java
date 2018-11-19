package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.BorrowCartDAO;
import dao.BorrowItemDAO;
import entity.Book;
import entity.Librarian;
import entity.Reader;

/**
 * librarian处理还书
 * 
 * @author zengyaoNPU
 *
 */
public class LibrarianReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LibrarianReturnBook() {
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
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		BorrowCartDAO dao = new BorrowCartDAO();
		HttpSession session = request.getSession();
		Librarian librarian = (Librarian) session.getAttribute("librarianEntity");
		BorrowItemDAO d = new BorrowItemDAO();
		Reader reader = d.getReaderInBorrowItemByBookID(bookId);
		if (reader == null) {
			out.print("<script language='javascript'>"
					+ "alert('No Reader borrowed this book or reader information are loss!');"
					+ "window.location.href='librarianReturnBook.jsp';" + "</script>");
		}
		BookDAO bDAO = new BookDAO();
		Book book = bDAO.searchByID(bookId);
		if (book != null) {
			if (dao.returnBook(bookId, librarian.getId())) {
				request.setAttribute("borrowerEntity", reader);
				request.setAttribute("bookEntity", book);
				request.getRequestDispatcher("librarianReturnBook.jsp").forward(request, response);
			} else {
				out.print("<script language='javascript'>" + "alert('This Book is not borrowed!');"
						+ "window.location.href='librarianReturnBook.jsp';" + "</script>");
			}
		} else {
			out.print("<script language='javascript'>" + "alert('Error Book ID! There is no book ID= '" + bookId + "!);"
					+ "window.location.href='librarianReturnBook.jsp';" + "</script>");
		}
	}

}
