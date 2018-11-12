package controller.reader;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import entity.Book;

/**
 * 展示所有图书
 */
public class BookList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> list = BookDAO.getBookList();

		HttpSession session = request.getSession();
		String readerName = (String) session.getAttribute("readerName");
		session.setAttribute("bookList", list);
		response.sendRedirect("../BookList.jsp");
	}

}
