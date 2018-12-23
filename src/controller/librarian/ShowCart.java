package controller.librarian;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowCartDAO;
import entity.Cart;

/**
 * 该servlet用于接收从showCart.jsp传来的get请求，从数据库中获取borrow_cart，并分页展示给librarian
 * 
 * @author zengyaoNPU
 * @date 2018-11-17 21:43
 *
 */
public class ShowCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int start = 0;// 起始行
		int count = 5;// 每页显示行数
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (NumberFormatException e) {
			// 当浏览器没有传参数start时
		}
		int next = start + count;// 下一页
		int pre = start - count;// 前一页
		BorrowCartDAO bDAO = new BorrowCartDAO();
		int total = bDAO.getTotal();// 获取borrow_cart中的总数
		// 最后一页的起始行
		int last;
		if (0 == total % count) {// 每一页都能展示最大行数
			last = total - count;
		} else {
			last = total - total % count;
		}
		pre = pre < 0 ? 0 : pre;
		next = next > last ? last : next;
		// 设置request属性
		request.setAttribute("next", next);
		request.setAttribute("pre", pre);
		request.setAttribute("last", last);
		request.setAttribute("current", start);
		// 获取数据库中所有的borrow_cart，分页展示
		List<Cart> list = bDAO.getAllBorrowCartOrderByTime(start, count);
		// 设置request属性
		request.setAttribute("cartList", list);
		// 转发
		request.getRequestDispatcher("showCart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
