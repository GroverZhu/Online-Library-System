package controller.reader;

/**
 * @author LiuZhuocheng
 * 用来展示readerborrowhistory
 */
import dao.BorrowItemDAO;
import entity.BorrowItem;
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

public class ReaderBorrowHistory extends HttpServlet {
	public ReaderBorrowHistory() {
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
		List<BorrowItem> borrowItems = new ArrayList<>();
		BorrowItemDAO borrowItemDAO = new BorrowItemDAO();
		borrowItems = borrowItemDAO.getBorrowItemInCurrent(userid);
		session.setAttribute("borrowHistory", borrowItems);// 设置session属性，以便后面使用
		response.sendRedirect("readerBorrowHistory.jsp");
	}

}
