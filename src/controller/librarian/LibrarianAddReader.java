package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReaderDAO;
import util.SecurityUtil;

/**
 * librarian添加reader
 */
public class LibrarianAddReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LibrarianAddReader() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取参数
		String name = request.getParameter("readerName");// 用户名
		String password = request.getParameter("password");// 密码
		String state = request.getParameter("state");// 状态
		password = SecurityUtil.md5(password);// 将reader的密码转换成密文
		// 数据库操作
		ReaderDAO readerDAO = new ReaderDAO();
		int readerId = readerDAO.addReaderByName_Passowrd_State(name, password, state);
		if (readerId == -1) {
			System.out.println("添加Reader失败");
			out.print("<script language='javascript'>" + "alert('Sorry! Fail to Add Reader!');"
					+ "window.location.href='librarianAddReader.jsp';" + "</script>");
		} else {
			System.out.println("添加Reader成功，新的reader id=" + readerId);
			out.print("<script language='javascript'>" + "alert('Succeed to Add Reader!');"
					+ "window.location.href='ShowNewReader?reader_id=" + readerId + "';" + "</script>");
		}
	}

}
