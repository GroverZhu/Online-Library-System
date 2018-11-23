package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthorDAO;
import dao.BookDAO;
import entity.Book;

public class LibrarianEditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO = new BookDAO();
	private AuthorDAO authorDAO = new AuthorDAO();

	public LibrarianEditBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int bookId = Integer.parseInt(request.getParameter("book_id"));
		Book book = bookDAO.searchByID(bookId);
		List<Book> list = (List<Book>) bookDAO.getBookListByIsbn(book.getISBN());
		request.setAttribute("bookEditList", list);
		request.setAttribute("bookEdit", book);
		request.setAttribute("bookEditId", bookId);
		request.getRequestDispatcher("librarianEditBook.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int bookId = Integer.parseInt(request.getParameter("Book ID"));
		String location = request.getParameter("Location");
		String state = request.getParameter("State");
		boolean tag = bookDAO.updateBookInfoById(bookId, location, state);
		if (tag) {
			out.print("<script language='javascript'>" + "alert('Congratulation! You have modify book Successfully!');"
					+ "window.location.href='librarianSearchBook.jsp';" + "</script>");
		} else {
			out.print("<script language='javascript'>" + "alert('Nothing have been changed!');"
					+ "window.location.href='librarianSearchBook.jsp';" + "</script>");
		}

	}

}
