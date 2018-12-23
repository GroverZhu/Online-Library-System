package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import entity.Book;

/**
 * 该servlet用于获取librarianSearchBook.jsp的请求，返回图书的信息
 * 
 * @author zengyaoNPU
 *
 */
public class LibrarianSearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO = new BookDAO();

	public LibrarianSearchBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		BookDAO bookDAO = new BookDAO();
		HttpSession session = request.getSession();
		Collection<Book> books = new ArrayList<Book>();

		// 分页功能
		int start = 0;
		int count = 5;

		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		int next = start + count;
		int pre = start - count;
		String isbn = request.getParameter("isbn");
		if (isbn == null) {// isbn为空，说明是从details.jsp发来的请求
			isbn = request.getParameter("bookInfoISBN");// 从url中获取参数
		}
		int total = bookDAO.getTotal(isbn);// 获取书的总数

		if (total == 0) {
			out.print(
					"<script>alert('This kind of book is not available!');window.location='librarianSearchBook.jsp';</script>");
			return;
		}

		int last;
		if (0 == total % count)
			last = total - count;
		else
			last = total - total % count;

		pre = pre < 0 ? 0 : pre;
		next = next > last ? last : next;

		request.setAttribute("next", next);
		request.setAttribute("pre", pre);
		request.setAttribute("last", last);

		// 转发到页面，展示图书详细信息
		List<Book> list = (List<Book>) bookDAO.getBookListByIsbn(isbn, start, count);// 通过ISBN获取书籍信息，区分ID
		Book book = bookDAO.getBookByIsbn(isbn);// 获取书本实例，不区分id

		request.setAttribute("information", book);
		request.setAttribute("library", list);
		request.getRequestDispatcher("details.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String searchBy = request.getParameter("searchBy");
		String keyword = request.getParameter("keyword");
		System.out.println("关键词keyword=" + keyword);
		System.out.println(request.getCharacterEncoding());
		if (searchBy.equals("Book Name")) {
			System.out.println("By Book Name");
			List<Book> list = (List<Book>) bookDAO.getBookByAlikeTitle(keyword);
			if (list == null || list.isEmpty()) {
				out.print("<script language='javascript'>" + "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';" + "</script>");
			} else {
				// 不知道为什么，这里用requestDispatcher的时候，第二次进入jsp页面会中文乱码
				HttpSession session = request.getSession();
				session.setAttribute("bookList", list);
				session.removeAttribute("bookEntity");
				response.sendRedirect("librarianSearchBook.jsp");
			}
		} else if (searchBy.equals("ISBN")) {
			System.out.println("By ISBN");
			Book book = bookDAO.getBookByIsbn(keyword);
			if (book == null) {
				out.print("<script language='javascript'>" + "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';" + "</script>");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("bookEntity", book);
				session.removeAttribute("bookList");
				response.sendRedirect("librarianSearchBook.jsp");
			}
		} else if (searchBy.equals("Author")) {
			System.out.println("By Author");
			List<Book> list = (List<Book>) bookDAO.getBookByAuthor(keyword);
			if (list == null || list.isEmpty()) {
				out.print("<script language='javascript'>" + "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';" + "</script>");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("bookList", list);
				session.removeAttribute("bookEntity");
				response.sendRedirect("librarianSearchBook.jsp");
			}
		} else if (searchBy.equals("Publisher")) {
			List<Book> list = (List<Book>) bookDAO.getBookByPublisher(keyword);
			if (list == null || list.isEmpty()) {
				out.print("<script language='javascript'>" + "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';" + "</script>");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("bookList", list);
				session.removeAttribute("bookEntity");
				response.sendRedirect("librarianSearchBook.jsp");
			}

		}
	}

}
