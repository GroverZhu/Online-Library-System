package controller.reader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.BorrowCartDAO;
import entity.Book;
import entity.Reader;

/**
 * Servlet implementation class AddBookToCart
 * 
 * @author Huyuxi
 */
public class AddBookToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookToCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		BorrowCartDAO borrowcartdao = new BorrowCartDAO();
		HttpSession session = request.getSession();
		Reader reader = (Reader) session.getAttribute("ReaderEntity");
		int reader_id = reader.getId();
		Collection<Book> books = new ArrayList<Book>();

		int id = Integer.valueOf(request.getParameter("id"));
		borrowcartdao.addBorrowCart(id, reader_id);
		out.print("<script>alert('Add successfully');window.history.back();</script>");
	}

}
