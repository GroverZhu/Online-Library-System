package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LibrarianDAO;
import entity.Librarian;
import util.CharacterFilterUtil;
import util.SecurityUtil;

/**
 * 该类用于处理Librarian登录的业务逻辑
 *
 * @author zengyaoNPU
 */
public class LibrarianLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void destroy() {
		super.destroy();
	}

	/**
	 * 处理Librarian登录的业务逻辑
	 */

	/**
	 * 更改了判断后的提示信息LiuZhuocheng
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 测试用：NPU，710072
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userName = (String) request.getParameter("userID");// 获取用户名
		int userid = Integer.MAX_VALUE;
		if (userName != null && !userName.isEmpty())
			userid = Integer.valueOf(userName);
		String password = (String) request.getParameter("password");// 获取密码
		System.out.println(userName);
		System.out.println(password);
		if (request.getParameter("userID") == null || request.getParameter("password") == null
				|| request.getParameter("userID").equals("") || request.getParameter("password").equals("")) {
			out.print(
					"<script language='javascript'>alert('Librarian ID or Password Can Not Be Empty!');window.location.href='Login.jsp';</script>");
		} else {
			LibrarianDAO librarianDAO = new LibrarianDAO();
			Librarian librarian = null;
			if (CharacterFilterUtil.isNumeric(userName)) {
				librarian = librarianDAO.getLibrarianById(userid);// 根据用户名获取一个librarian实体
			}
			if (librarian == null) {// 无法获取librarian实体
				System.out.println("用户不存在");
				out.print(
						"<script language='javascript'>alert('LibrarianID Not Exist!');window.location.href='Login.jsp';</script>");
			} else {
				password = SecurityUtil.md5(password);
				if (librarian.getPassword().equals(password)) {// 密码匹配
					if (librarian.getState().equals("unlock")) {// 账号未锁定
						System.out.println("登录成功");
						HttpSession session = request.getSession();
						session.setAttribute("librarianEntity", librarian);// 设置session属性，以便后面使用
						response.sendRedirect("librarian/librarianHomepage.jsp");
					} else {
						System.out.println("账号被锁定");
						out.print(
								"<script language='javascript'>alert('Your LibrarianID has been locked!');window.location.href='Login.jsp';</script>");
					}
				} else {
					System.out.println("密码不正确");
					out.print(
							"<script language='javascript'>alert('Your LibrarianID or Password is Wrong!');window.location.href='Login.jsp';</script>");

				}
			}

		}
	}
}
