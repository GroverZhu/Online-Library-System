package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import model.Book;

public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 将form传来的参数编码成UTF-8，重要！否则无法读出中文
		String searchBy = (String) request.getParameter("searchBy");
		String keyword = (String) request.getParameter("keyword");
		System.out.println("searchBy = " + searchBy + " , keyword = " + keyword + " , --SearchBook--,--doPost()--");// 中文乱码
		if (searchBy.equals("Book Name")) {
			List<Book> books = BookDAO.getBookListByName(keyword);
			HttpSession session = request.getSession();
			session.setAttribute("searchResult", books);
			session.setMaxInactiveInterval(3);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../SearchResult.jsp");
			dispatcher.forward(request, response);
		} else if (searchBy.equals("Author")) {
			List<Book> books = BookDAO.getBookListByAuthorName(keyword);
			HttpSession session = request.getSession();
			session.setAttribute("searchResult", books);
			session.setMaxInactiveInterval(3);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../SearchResult.jsp");
			dispatcher.forward(request, response);
		} else if (searchBy.equals("Publisher")) {
			List<Book> books = BookDAO.getBookListByPublisherName(keyword);
			HttpSession session = request.getSession();
			session.setAttribute("searchResult", books);
			session.setMaxInactiveInterval(3);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../SearchResult.jsp");
			dispatcher.forward(request, response);
		} else if (searchBy.equals("ISBN")) {
			List<Book> books = BookDAO.getBookByIsbn(keyword);
			HttpSession session = request.getSession();
			session.setAttribute("searchResult", books);
			session.setMaxInactiveInterval(3);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../SearchResult.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("../Error.jsp");// 暂未定义
			dispatcher.forward(request, response);
		}

	}

}
