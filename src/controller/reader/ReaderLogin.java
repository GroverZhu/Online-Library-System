package controller.reader;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.*;
import dao.ReaderDAO;

/**
 * Servlet implementation class Readerlogin
 */
public class ReaderLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReaderLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) 实现Reader用户登陆，判断密码和ID是否相符 Liu Zhuocheng
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String usersid = (String) request.getParameter("userID");
		int userid = Integer.MAX_VALUE;
		if (usersid != null && !usersid.isEmpty())
			userid = Integer.valueOf(usersid);
		String password = (String) request.getParameter("password");
		System.out.println(usersid);
		System.out.println(password);
		if (request.getParameter("userID") == null || request.getParameter("password") == null
				|| request.getParameter("userID").equals("") || request.getParameter("password").equals("")) {
			out.print(
					"<script language='javascript'>alert('Reader ID or Password Can Not Be Empty!');window.location.href='Login.jsp';</script>");
		} else {
			ReaderDAO readerDAO = new ReaderDAO();
			entity.Reader reader = null;
			if (CharacterFilterUtil.isNumeric(usersid)) {
				reader = readerDAO.getReaderById(userid);
			}
			if (reader == null) {// 无法获取Reader实体
				out.print(
						"<script language='javascript'>alert('ReaderID Not Exist!');window.location.href='Login.jsp';</script>");
			} else {
				password = SecurityUtil.md5(password);
				if (reader.getPassword().equals(password)) {
					if (reader.getState().equals("unlock")) {
						System.out.println("unlock");
						HttpSession session = request.getSession();
						session.setAttribute("ReaderEntity", reader);// 设置session属性，以便后面使用
						response.sendRedirect("readerIndex.jsp");
					} else
						out.print(
								"<script language='javascript'>alert('Your ReaderID has been locked!');window.location.href='Login.jsp';</script>");
				} else {
					out.print(
							"<script language='javascript'>alert('Your ReaderID or Password is Wrong!');window.location.href='Login.jsp';</script>");
				}
			}
		}
	}
}
