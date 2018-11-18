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
import entity.Book;

/**
 * Servlet implementation class ShowBookInLib
 * @author Huyuxi
 */
public class ShowBookInLib extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBookInLib() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		BookDAO  bookDAO =new BookDAO();
		HttpSession session = request.getSession();  
		Collection<Book> books =new  ArrayList<Book>();
		
		String isbn = request.getParameter("isbn");
		System.out.println(isbn);
		session.removeAttribute("bookList");
		session.removeAttribute("isbn");
		session.removeAttribute("bookInfoList");
		isbn = isbn.trim();
		if(isbn == ""||isbn.isEmpty()) {
			out.print("<script>alert('Please enter the full keyword, the keyword cannot null!');window.location='readerSearchBook.jsp';</script>");
		}
		books=bookDAO.getBookListByIsbnForCart(isbn);
		if(books.isEmpty()) {
			out.print("<script>alert('Sorry, there is no book in library right now!Please try a new one!');window.location='readerSearchBook.jsp';</script>");
		}else {
			session.setAttribute("bookInfoList", books);		
		}
		request.getRequestDispatcher("readerShowBookInLib.jsp").forward(request,response);
	}

}
