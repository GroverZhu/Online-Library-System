package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BorrowCartDAO;
import entity.Librarian;

/**
 * Servlet implementation class LibrarainReturnBook
 */
public class LibrarianReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianReturnBook() {
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
		PrintWriter out=response.getWriter();
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		BorrowCartDAO dao=new BorrowCartDAO();
		HttpSession session=request.getSession();
		Librarian librarian=(Librarian)session.getAttribute("librarianEntity");
		if(dao.returnBook(bookId, librarian.getId())) {
			out.print("<script language='javascript'>"
					+ "alert('Succeed to Return Book!');"
					+ "window.location.href='librarianReturnBook.jsp';"
					+ "</script>");
		}else {
			out.print("<script language='javascript'>"
					+ "alert('Fail to Return Book!');"
					+ "window.location.href='librarianReturnBook.jsp';"
					+ "</script>");
		}
	}

}
