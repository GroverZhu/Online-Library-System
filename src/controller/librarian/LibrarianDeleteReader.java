package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReaderDAO;

/**
 * 该类用于librarian删除reader 接收从librarianDeleteReader.jsp传来的参数
 * 
 * @author zengyaoNPU
 */
public class LibrarianDeleteReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LibrarianDeleteReader() {
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
		try {
			int readerId = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			if (name == null) {
				System.out.println("--LibrarianDeleteReader--doPost(),name为空");
				out.print("<script language='javascript'>" + "alert('Failed! Reason: Reader Name is NULL!');"
						+ "window.location.href='librarianDeleteReader.jsp';" + "</script>");
			}
			ReaderDAO readerDAO = new ReaderDAO();
			if (readerDAO.deleteReaderById(readerId, name)) {
				System.out.println("--LibrarianDeleteReader--doPost(),删除成功");
				out.print("<script language='javascript'>" + "alert('Succeed to delete Reader!');"
						+ "window.location.href='librarianDeleteReader.jsp';" + "</script>");
			} else {
				System.out.println("--LibrarianDeleteReader--doPost(),删除失败");
				out.print("<script language='javascript'>" + "alert('Fail to Delete Reader!');"
						+ "window.location.href='librarianDeleteReader.jsp';" + "</script>");
			}
		} catch (Exception e) {
			System.out.println("--LibrarianDeleteReader--doPost(),输入不合法");
			out.print("<script language='javascript'>" + "alert('Invalid input!');"
					+ "window.location.href='librarianDeleteReader.jsp';" + "</script>");
		}

	}

}
