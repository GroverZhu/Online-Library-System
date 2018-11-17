package controller.administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdministratorDAO;
import entity.Administrator;

/**
 * 更新administrator的名字/密码
 * 
 * @author GroverZhu
 *
 */
public class AdminUpdateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUpdateInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		AdministratorDAO adminDao = new AdministratorDAO();
		int adminId = Integer.valueOf(request.getParameter("AdminID"));
		String name = request.getParameter("AdminName");
		String password = request.getParameter("password");
		if (password == null || password.equals("")) {
			HttpSession session = request.getSession();
			Administrator ad = (Administrator) session.getAttribute("AdministratorEntity");
			password = ad.getPassword();

		} else {
			password = util.SecurityUtil.md5(password);
		}

		int flag = adminDao.updateAdmin(adminId, name, password);

		if (flag == 1) {
			// 更新成功后，更新session中的数据
			Administrator admin = new Administrator();
			admin.setId(adminId);
			admin.setName(name);
			admin.setPassword(password);
			HttpSession session = request.getSession();
			session.setAttribute("AdministratorEntity", admin);

			String msg = "Update the Information Successfully!";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);
		} else {

			String msg = "Update the Information Failed, Please Try Again!";
			request.setAttribute("message", msg);
			request.getRequestDispatcher("adminOperateResult.jsp").forward(request, response);
		}
	}

}
