package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author zengyaoNPU
 *
 */
public class LibrarianSearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDAO bookDAO=new BookDAO();
    public LibrarianSearchBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn=request.getParameter("isbn");
		//转发到页面，展示图书详细信息
		List<Book> list=(List<Book>) bookDAO.getBookListByIsbn(isbn);
		Book book=bookDAO.getBookByIsbn(isbn);
		request.setAttribute("information", book);
		request.setAttribute("library", list);
		request.getRequestDispatcher("details.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String searchBy=request.getParameter("searchBy");
		String keyword=request.getParameter("keyword");
		System.out.println("关键词keyword="+keyword);
		System.out.println(request.getCharacterEncoding());
		if(searchBy.equals("Book Name")) {
			System.out.println("By Book Name");
			List<Book> list=(List<Book>) bookDAO.getBookByAlikeTitle(keyword);
			if(list==null||list.isEmpty()) {
				out.print("<script language='javascript'>"
						+ "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';"
						+ "</script>");
			}else {
				//不知道为什么，这里用requestDispatcher的时候，第二次进入jsp页面会中文乱码
				HttpSession session=request.getSession();
				session.setAttribute("bookList", list);
				session.setMaxInactiveInterval(1);
				response.sendRedirect("librarianSearchBook.jsp");
			}
		}else if(searchBy.equals("ISBN")) {
			System.out.println("By ISBN");
			Book book=bookDAO.getBookByIsbn(keyword);
			if(book==null) {
				out.print("<script language='javascript'>"
						+ "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';"
						+ "</script>");
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("bookEntity", book);
				session.setMaxInactiveInterval(1);
				response.sendRedirect("librarianSearchBook.jsp");
//				request.getRequestDispatcher("librarianSearchBook.jsp").forward(request, response);
			}
		}else if(searchBy.equals("Author")) {
			System.out.println("By Author");
			List<Book> list=(List<Book>) bookDAO.getBookByAuthor(keyword);
			if(list==null||list.isEmpty()) {
				out.print("<script language='javascript'>"
						+ "alert('There is no such book in the library!');"
						+ "window.location.href='librarianSearchBook.jsp';"
						+ "</script>");
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("bookList", list);
				session.setMaxInactiveInterval(1);
				response.sendRedirect("librarianSearchBook.jsp");
//				request.getRequestDispatcher("librarianSearchBook.jsp").forward(request, response);
			}
		}
	}

}
