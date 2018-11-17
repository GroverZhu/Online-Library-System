package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.ReaderDAO;
import entity.Book;
import entity.Librarian;
import entity.Reader;

/**
 * 该serlvet获取librarianLendBook.jsp的请求，查询reader的状态，并将book借给reader。
 * @author zengyaoNPU
 * @date 2018-11-17 11:29
 */
public class LibrarianLendBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibrarianLendBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		if(request.getParameter("bookId")!=null) {
			//获取参数
			int bookId=Integer.parseInt(request.getParameter("bookId"));
			int readerId=Integer.parseInt(request.getParameter("readerId"));
			ReaderDAO readerDAO=new ReaderDAO();
			//获取reader实体
			Reader reader=readerDAO.getReaderById(readerId);
			//获取借阅总数
			int total=readerDAO.getBorrowTotal(readerId);
			request.setAttribute("readerEntity", reader);
			request.setAttribute("total",String.valueOf(total));
			BookDAO bookDAO=new BookDAO();
			//获取book实例
			Book book=bookDAO.searchByID(bookId);
			//设置request属性
			if(book.getState().equals("borrowed")) {//书已被借阅
				System.out.println("--LibrarianLendBook--,borrowed");
				out.print("<script language='javascript'>"
						+ "alert('Sorry! The book you selected has been borrowed!');"
						+ "window.location.href='librarianLendBook.jsp';"
						+ "</script>");
			}else if(book.getState().equals("reserve")) {//书已被预约
				System.out.println("--LibrarianLendBook--,reserve");
				out.print("<script language='javascript'>"
						+ "alert('Sorry! The book you selected has been reserved!');"
						+ "window.location.href='librarianLendBook.jsp';"
						+ "</script>");
			}else {//借书成功
				System.out.println("--LibrarianLendBook--,success branch");
				HttpSession session=request.getSession();
				Librarian librarian=(Librarian)session.getAttribute("librarianEntity");
				bookDAO.borrowBook(bookId, readerId, librarian.getId());
				out.print("<script language='javascript'>"
						+ "alert('Congratulation! Borrow Successfully!');"
						+ "window.location.href='librarianLendBook.jsp';"
						+ "</script>");
			}
		}else if(request.getParameter("readerId")!=null) {
			int readerId=Integer.parseInt(request.getParameter("readerId"));
			ReaderDAO readerDAO=new ReaderDAO();
			//获取reader实例和该reader借阅的数量
			Reader reader=readerDAO.getReaderById(readerId);
			int total=readerDAO.getBorrowTotal(readerId);
			//设置request属性
			request.setAttribute("readerEntity", reader);
			request.setAttribute("total",String.valueOf(total));
			//转发
			request.getRequestDispatcher("librarianLendBook.jsp").forward(request, response);
		}
		
	}

}
