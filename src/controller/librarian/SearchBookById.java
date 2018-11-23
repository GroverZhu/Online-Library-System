package controller.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import entity.Book;

/**
 * 该servlet用于通过book_id搜索一本书的信息
 * 
 * @author zengyaoNPU
 *
 */
public class SearchBookById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchBookById() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int bookId = Integer.parseInt(request.getParameter("book_id"));// 获取参数
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.searchByID(bookId);// 实例化一个book
		request.setAttribute("bookEntity", book);// 设置request属性
		request.getRequestDispatcher("librarianDeleteBook.jsp").forward(request, response);
	}

}
