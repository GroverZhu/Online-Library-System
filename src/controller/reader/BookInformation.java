package controller.reader;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import entity.Book;

/**
 * Servlet implementation class BookInformation
 */
public class BookInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookInformation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = (String) request.getParameter("isbn");
		List<Book> list = BookDAO.getBookListByIsbn(isbn);// 从book_in_library中获取Book列表，区分book_id，同一个isbn有多本书
		request.setAttribute("bookInformation", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("../BookInformation.jsp");
		List<Book> s = (List<Book>) request.getAttribute("bookInformation");
		for (Book i : s) {
			System.out.println(i.toString());
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
