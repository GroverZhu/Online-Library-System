package controller.administrator;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.AdministratorDAO;
import entity.Administrator;
import util.CharacterFilterUtil;
import util.SecurityUtil;

/**
 * 该类用于处理Administrator登录的业务逻辑
 *
 * @author zengyaoNPU 11.14基本全改了。Liu Zhuocheng
 */
public class AdministratorLogin extends HttpServlet {
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * 处理Administrator登录的业务逻辑
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String usersid = (String) request.getParameter("userID");
		int userid = Integer.MAX_VALUE;
		if (usersid != null && !usersid.isEmpty())
			userid = Integer.valueOf(usersid);
		String password = (String) request.getParameter("password");
		if (request.getParameter("userID") == null || request.getParameter("password") == null
				|| request.getParameter("userID").equals("") || request.getParameter("password").equals("")) {
			out.print(
					"<script language='javascript'>alert('Admin ID or Password Can Not Be Empty!');window.location.href='Login.jsp';</script>");
		} else {
			AdministratorDAO administratorDAO = new AdministratorDAO();
			Administrator administrator = null;
			password = SecurityUtil.md5(password);
			if (CharacterFilterUtil.isNumeric(usersid)) {
				administrator = administratorDAO.getAdministratorById(userid);
			}
			if (administrator == null) {// 无法获取Administrator实体
				System.out.println("the administrator not exsit");
				out.print(
						"<script language='javascript'>alert('AdminID Not Exist!');window.location.href='Login.jsp';</script>");
			} else if (administrator.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("AdministratorEntity", administrator);// 设置session属性，以便后面使用
				response.sendRedirect("adminHomepage.jsp");
			} else {
				out.print(
						"<script language='javascript'>alert('Your AdminID or Password is Wrong!');window.location.href='Login.jsp';</script>");
			}
		}
	}

}
