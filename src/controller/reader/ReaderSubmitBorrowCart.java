package controller.reader;
/**
 * @author LiuZhuocheng
 * 用来展示readerborrowcart
 */

import dao.BookDAO;
import dao.BorrowCartDAO;
import dao.BorrowItemDAO;
import entity.Book;
import entity.BorrowItem;
import entity.Cart;
import entity.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReaderSubmitBorrowCart extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String bookids = request.getParameter("bookid");
		int bookid = Integer.MAX_VALUE;
		if (bookids != null)
			bookid = Integer.valueOf(bookids);
		Reader reader = (Reader) session.getAttribute("ReaderEntity");
		int userid = Integer.MAX_VALUE;
		if (reader != null) {
			userid = reader.getId();
		}
		BorrowCartDAO borrowCartDAO = new BorrowCartDAO();
		borrowCartDAO.updateBorrowCart(bookid, userid);
		BookDAO bookDAO = new BookDAO();
		Book book = new Book();
		book = bookDAO.searchByID(bookid);
		String state = book.getState();
		if (book != null && !state.equals("inlib")) {
			borrowCartDAO.deleteBorrowCart(bookid, reader.getId());
			response.sendRedirect("readerFailedSubmitBorrowCart.jsp");
		} else {
			BorrowItemDAO borrowItemDAO = new BorrowItemDAO();
			int num = 0;
			List<Cart> carts = borrowCartDAO.getBorrowCartByReaderId(reader.getId());
			for (Cart cart : carts) {
				num++;
			}
			if (num >= 10) {
				PrintWriter out = response.getWriter();
				out.print(
						"<script language='javascript'>alert('You have reserved too many books!');window.location.href='readerFailedSubmitBorrowCart.jsp';</script>");
			} else {
				bookDAO.updateBookStateToReserve(bookid);
				// List<Cart>carts =
				// borrowCartDAO.getBorrowCartByReaderId(userid);
				// for(Cart cart : carts){
				// if(cart.getBookId()==bookid && cart.getReaderId()==userid &&
				// )
				// }
				// 写时还没有更改状态的方法。无法进行判断
				// 已有更改状态的方法，进行更改
				response.sendRedirect("readerSuccessSubmitBorrowCart.jsp");
			}
		}
	}

}
