package controller.reader;
/**
 * @author LiuZhuocheng
 * 用来展示readerborrowcart
 */

import dao.BorrowCartDAO;
import dao.BorrowItemDAO;
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

public class ReaderBorrowCart extends HttpServlet {
	public ReaderBorrowCart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Reader reader = (Reader) session.getAttribute("ReaderEntity");
		int userid = Integer.MAX_VALUE;
		if (reader != null) {
			userid = reader.getId();
		}
		List<Cart> carts = new ArrayList<>();
		BorrowCartDAO borrowCartDAO = new BorrowCartDAO();
		carts = borrowCartDAO.getNullBorrowCartByReaderId(userid);
		session.setAttribute("borrowCart", carts);// 设置session属性，以便后面使用
		response.sendRedirect("readerBorrowCart.jsp");
	}

}
