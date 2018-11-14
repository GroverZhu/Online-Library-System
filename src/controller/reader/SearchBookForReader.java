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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		BookDAO  bookDAO =new BookDAO();
		HttpSession session = request.getSession();  
		Collection<Book> books =new  ArrayList<Book>();
		int i = 0;

		String style = request.getParameter("style");
		String name = request.getParameter("name");
		name = name.trim();
		name=name.toLowerCase();
		if(name == ""||name.isEmpty()) {
			out.print("<script>alert('Please enter the full keyword, the keyword cannot null!');window.location='readerSearchBook.jsp';</script>");
		}
		if(style.equals("bookName")) {
			books = bookDAO.searchByTitle(name);
		}else if(style.equals("author")) {
			books = bookDAO.searchByAuthor(name);
		}else if(style.equals("publisher")) {
			books = bookDAO.searchByPublisher(name);
		}else {
			out.print("<script>alert('Please select a search type!');window.location='readerSearchBook.jsp';</script>");
		}
		System.out.println(books.size());

		if(books.isEmpty()) {
			out.print("<script>alert('No related books!Please try a new one!');window.location='readerSearchBook.jsp';</script>");
		}else {
			session.setAttribute("bookList", books);
//			session.setAttribute("bookcount", books.size());
//			for(Book book : books) {
//				session.setAttribute("bookname"+i, book.getName());
//				System.out.println(session.getAttribute("bookname"+i));
//				session.setAttribute("bookisbn"+i, book.getISBN());
//				session.setAttribute("bookauthor"+i, book.getAuthors());
//				session.setAttribute("bookpublisher"+i, book.getPublisher());
//				session.setAttribute("bookedition"+i, book.getLocation());
//				session.setAttribute("bookstatue"+i, book.getState());
//				i++;
//			}
			request.getRequestDispatcher("readerSearchBook.jsp").forward(request,response);
		}
	}

}
