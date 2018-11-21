package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReaderDAO;
import entity.Reader;

/**
 * Servlet implementation class SearchReaderBeforeEdit
 */
public class SearchReaderBeforeEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchReaderBeforeEdit() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		int readerId = Integer.parseInt(request.getParameter("reader_id"));
		ReaderDAO readerDAO = new ReaderDAO();
		Reader reader = readerDAO.getReaderById(readerId);// 实例化Reader
		// 设置属性
		request.setAttribute("readerEntity", reader);// 新的request，request属性与之前的request对象相同不会影响
		RequestDispatcher dispatcher = request.getRequestDispatcher("searchReaderBeforeEdit.jsp");
		dispatcher.forward(request, response);// 转发
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		try {
			int readerId = Integer.parseInt(request.getParameter("account"));// 获取参数
			ReaderDAO readerDAO = new ReaderDAO();
			Reader reader = readerDAO.getReaderById(readerId);// 实例化属性
			request.setAttribute("readerEntity", reader);// 设置request属性，仅在下一个被请求页面中使用该属性
			RequestDispatcher dispatcher = request.getRequestDispatcher("searchReaderBeforeEdit.jsp");
			dispatcher.forward(request, response);// 转发
		} catch (Exception e) {
			System.out.println("--SearchReaderBeforeEdit--doPost(),传参错误：很有可能是reader账号没有写对");
			out.print("<script language='javascript'>" + "alert('Reader ID may be invalid, please input again!');"
					+ "window.location.href='searchReaderBeforeEdit.jsp';" + "</script>");
		}
	}

}
