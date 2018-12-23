package controller.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BorrowItemDAO;
import dao.LibrarianDAO;
import entity.BorrowItem;
import entity.Librarian;
import entity.Reader;

/**
 * Servlet implementation class AdminViewLibrarian
 */
public class ReaderViewReturnRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReaderViewReturnRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	private BorrowItemDAO borrowItemDAO = new BorrowItemDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		int start = 0;
		int count = 5;
		int num = 0;
		HttpSession session = request.getSession();
		try {
			start = Integer.parseInt(request.getParameter("start"));
			num = num + start;
			session.setAttribute("nums", num);
		} catch (NumberFormatException e) {
			// 当浏览器没有传参数start时
		}

		int next = start + count;
		int pre = start - count;
		if (session != null) {
			Reader reader = (Reader) session.getAttribute("ReaderEntity");
			if (reader != null) {
				int readerid = reader.getId();

				List<BorrowItem> borrowitems = borrowItemDAO.getBorrowItemInHistory(readerid);
				int total = 0;
				for (BorrowItem borrowItem : borrowitems) {
					total++;
				}

				int last;
				if (0 == total % count)
					last = total - count;
				else
					last = total - total % count;

				pre = pre < 0 ? 0 : pre;
				next = next > last ? last : next;
				request.setAttribute("next", next);
				request.setAttribute("pre", pre);
				request.setAttribute("last", last);

				List<BorrowItem> borrowItems = borrowItemDAO.getBorrowItemInHistory(start, count, readerid);
				request.setAttribute("borrowitems", borrowItems);
				request.getRequestDispatcher("readerReturnHistory.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("homepage.jsp").forward(request, response);
		}
	}
}
