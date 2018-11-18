package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import entity.Book;
/**
 * 该servlet用于获取librarianSearchBook.jsp的请求，返回图书的信息
 * @author zengyaoNPU
 *
 */
public class LibrarianSearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibrarianSearchBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String searchBy=request.getParameter("searchBy");
		String keyword=request.getParameter("keyword");
		BookDAO bookDAO=new BookDAO();
		if(searchBy.equals("Book Name")) {
			System.out.println("By Book Name");
			List<Book> list=(List<Book>) bookDAO.searchByTitle(keyword);
			request.setAttribute("bookList", list);
			if(list==null||list.isEmpty()) {
				out.print("<script language='javascript'>"
						+ "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';"
						+ "</script>");
			}else {
				request.getRequestDispatcher("librarianSearchBook.jsp").forward(request, response);
			}
		}else if(searchBy.equals("ISBN")) {
			System.out.println("By ISBN");
			Book book=bookDAO.searchByIsbn(keyword);
			request.setAttribute("bookEntity",book);
			System.out.println(book.toString());
			if(book==null) {
				out.print("<script language='javascript'>"
						+ "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';"
						+ "</script>");
			}else {
				request.getRequestDispatcher("librarianSearchBook.jsp").forward(request, response);
			}
		}else if(searchBy.equals("Author")) {
			System.out.println("By Author");
			List<Book> list=(List<Book>) bookDAO.searchByAuthor(keyword);
			request.setAttribute("bookList", list);
			if(list==null||list.isEmpty()) {
				out.print("<script language='javascript'>"
						+ "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';"
						+ "</script>");
			}else {
				request.getRequestDispatcher("librarianSearchBook.jsp").forward(request, response);
			}
		}
	}

}
