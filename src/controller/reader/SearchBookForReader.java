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

import dao.*;
import entity.*;

/**
 * Servlet implementation class SearchBookForReader
 * 
 * @author Hu Yuxi
 * @date 2018-11-14 20:00
 */
public class SearchBookForReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchBookForReader() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		BookDAO bookDAO = new BookDAO();
		HttpSession session = request.getSession();
		Collection<Book> books = new ArrayList<Book>();

		String style = request.getParameter("searchBy");
		String name = request.getParameter("name");
		name = name.trim();
		// name=name.toLowerCase();//删除该行，否则英文无法查询
		if (name == "" || name.isEmpty()) {
			out.print(
					"<script>alert('Please enter the full keyword, the keyword cannot null!');window.location='readerSearchBook.jsp';</script>");
		}
		if (style.equals("Book Name")) {
			books = bookDAO.getBookByAlikeTitle(name);
			System.out.println("--SearchBookForReader--,books.size()=" + books.size());

			if (books.isEmpty()) {
				out.print(
						"<script>alert('No related books!Please try a new one!');window.location='readerSearchBook.jsp';</script>");
			} else {
				session.setAttribute("bookList", books);
				session.removeAttribute("bookEntity");
				request.getRequestDispatcher("readerSearchBook.jsp").forward(request, response);
			}
		} else if (style.equals("Author")) {
			books = bookDAO.getBookByAuthor(name);
			System.out.println("--SearchBookForReader--,books.size()=" + books.size());

			if (books.isEmpty()) {
				out.print(
						"<script>alert('No related books!Please try a new one!');window.location='readerSearchBook.jsp';</script>");
			} else {
				session.setAttribute("bookList", books);
				session.removeAttribute("bookEntity");
				request.getRequestDispatcher("readerSearchBook.jsp").forward(request, response);
			}
		} else if (style.equals("Publisher")) {
			books = bookDAO.getBookByPublisher(name);
			System.out.println("--SearchBookForReader--,books.size()=" + books.size());

			if (books.isEmpty()) {
				out.print(
						"<script>alert('No related books!Please try a new one!');window.location='readerSearchBook.jsp';</script>");
			} else {
				session.setAttribute("bookList", books);
				session.removeAttribute("bookEntity");
				request.getRequestDispatcher("readerSearchBook.jsp").forward(request, response);
			}
		} else if (style.equals("ISBN")) {
			Book book = bookDAO.getBookByIsbn(name);
			System.out.println(book.toString());
			if (book == null) {
				out.print(
						"<script>alert('No related books!Please try a new one!');window.location='readerSearchBook.jsp';</script>");
			}
			session.setAttribute("bookEntity", book);
			session.removeAttribute("bookList");
			request.getRequestDispatcher("readerSearchBook.jsp").forward(request, response);

		} else {
			out.print("<script>alert('Please select a search type!');window.location='readerSearchBook.jsp';</script>");
		}
	}

}
