package controller.librarian;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LibrarianDAO;
import entity.Librarian;

/**
 * 该类用于处理Librarian登录的业务逻辑
 * 
 * @author zengyaoNPU
 *
 */
public class LibrarianLogin extends HttpServlet {
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * 处理Librarian登录的业务逻辑
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 测试用：NPU，710072
		request.setCharacterEncoding("utf-8");
		String userName = (String) request.getParameter("userName");//获取用户名
		String password = (String) request.getParameter("password");//获取密码
		 System.out.println(userName);
		 System.out.println(password);
		LibrarianDAO librarianDAO=new LibrarianDAO();
		Librarian librarian=librarianDAO.getLibrarianByName(userName);//根据用户名获取一个librarian实体
		if(librarian==null) {//无法获取librarian实体
			System.out.println("用户不存在");
		}else {
			if(librarian.getPassword().equals(password)) {//密码匹配
				if(librarian.getState().equals("unlock")) {//账号未锁定
					System.out.println("登录成功");
					HttpSession session =request.getSession();
					session.setAttribute("librarianEntity", librarian);//设置session属性，以便后面使用
					RequestDispatcher dispatcher=request.getRequestDispatcher("LibrarianInformation.jsp");
					dispatcher.forward(request, response);//跳转到信息页面
				}else {
					System.out.println("账号被锁定");
					response.sendRedirect("UserLogin.jsp");//跳转到登录界面
				}
			}else {
				System.out.println("密码不正确");
				response.sendRedirect("UserLogin.jsp");//跳转到登录界面
			}
		}
		
	}
}
