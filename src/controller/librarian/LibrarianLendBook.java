package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.BorrowCartDAO;
import dao.ReaderDAO;
import entity.Book;
import entity.Librarian;
import entity.Reader;

/**
 * 该serlvet获取librarianLendBook.jsp的请求，查询reader的状态，并将book借给reader。
 * 
 * @author zengyaoNPU
 * @date 2018-11-17 17:38
 */
public class LibrarianLendBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LibrarianLendBook() {
		super();
	}

	/**
	 * 处理showCart.jsp发来的请求，同意将borrow_cart中的书借给读者
	 * 
	 * @author zengyaoNPU
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String operate = request.getParameter("operate");
		int readerId = Integer.parseInt(request.getParameter("readerId"));
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		HttpSession session = request.getSession();
		Librarian librarian = (Librarian) session.getAttribute("librarianEntity");
		BorrowCartDAO bDAO = new BorrowCartDAO();
		if (operate.equals("agree")) {// 同意将书借出
			System.out.println(
					"readerId=" + readerId + "," + "bookId=" + bookId + "," + "librarianId" + librarian.getId());
			bDAO.agreeBorrowBook(readerId, bookId, librarian.getId());// 同意借书
		} else if (operate.equals("disagree")) {// 不同意将书借出
			bDAO.disagreeBorrowBook(readerId, bookId, librarian.getId());// 拒绝借书
		}
		// 重定向到ShowCart页面
		response.sendRedirect("ShowCart");
	}

	/**
	 * 接收从librarianLendBook.jsp发来的请求，将书借给读者
	 * 
	 * @author zengyaoNPU
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("bookId") != null) {
			// 获取参数
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int readerId = Integer.parseInt(request.getParameter("readerId"));
			ReaderDAO readerDAO = new ReaderDAO();
			// 获取reader实体
			Reader reader = readerDAO.getReaderById(readerId);
			// 获取借阅总数
			int total = readerDAO.getBorrowTotal(readerId);
			request.setAttribute("readerEntity", reader);
			request.setAttribute("total", String.valueOf(total));
			BookDAO bookDAO = new BookDAO();
			// 获取book实例
			Book book = bookDAO.searchByID(bookId);
			if (book == null) {
				System.out.println("--LibrarianLendBook--,book is null for id=" + bookId);
				out.print("<script language='javascript'>" + "alert('Sorry! The book not exsits!');"
						+ "window.location.href='librarianLendBook.jsp';" + "</script>");
			} else if (book.getState().equals("borrowed")) {// 书已被借阅
				System.out.println("--LibrarianLendBook--,borrowed");
				out.print("<script language='javascript'>" + "alert('Sorry! The book you selected has been borrowed!');"
						+ "window.location.href='librarianLendBook.jsp';" + "</script>");
			} else if (book.getState().equals("reserve")) {// 书已被预约
				System.out.println("--LibrarianLendBook--,reserve");
				out.print("<script language='javascript'>" + "alert('Sorry! The book you selected has been reserved!');"
						+ "window.location.href='librarianLendBook.jsp';" + "</script>");
			} else {// 借书成功
				System.out.println("--LibrarianLendBook--,success branch");
				HttpSession session = request.getSession();
				Librarian librarian = (Librarian) session.getAttribute("librarianEntity");
				bookDAO.lendBook(bookId, readerId, librarian.getId());
				out.print("<script language='javascript'>" + "alert('Congratulation! Borrow Successfully!');"
						+ "window.location.href='librarianLendBook.jsp';" + "</script>");
			}
		} else if (request.getParameter("readerId") != null) {
			int readerId = Integer.parseInt(request.getParameter("readerId"));
			ReaderDAO readerDAO = new ReaderDAO();
			// 获取reader实例和该reader借阅的数量
			Reader reader = readerDAO.getReaderById(readerId);
			int total = readerDAO.getBorrowTotal(readerId);
			// 设置request属性
			request.setAttribute("readerEntity", reader);
			request.setAttribute("total", String.valueOf(total));
			// 转发
			request.getRequestDispatcher("librarianLendBook.jsp").forward(request, response);
		}

	}

}
