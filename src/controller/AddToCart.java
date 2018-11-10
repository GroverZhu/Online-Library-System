package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.BorrowCartDAO;
import model.Book;
import model.Reader;

/**
 * Servlet implementation class AddToCart
 */
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddToCart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s = request.getParameter("book_id");
		int bookId = Integer.parseInt(s);
		HttpSession session = request.getSession();
		List<Book> borrowCart = (List<Book>) session.getAttribute("borrowCart");
		if (borrowCart == null) {
			borrowCart = new ArrayList<Book>();
		}
		Book book = BookDAO.getBookById(bookId);// 获取book实体（完整信息）
		borrowCart.add(book);
		session.setAttribute("borrowCart", borrowCart);// 加入到session属性中，方便下次登录直接查看？？？
		Reader reader = (Reader) session.getAttribute("reader");// 获取session属性
		if (reader == null) {
			System.out.println("reader is null");
		} else {
			System.out.println(reader.toString());
		}
		System.out.println(book.toString());
		// 在book_in_library中修改status_
		// 在borrow_cart中添加一行数据
		// 在borrow_record中添加一行数据

		BorrowCartDAO.addToBorrowCart(book, reader);// 加入借阅车

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
