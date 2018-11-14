package controller.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LibrarianDAO;
import entity.Librarian;

/**
 * 该Servlet用于接收Librarian修改密码的请求
 * @author zengyaoNPU
 *
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		Librarian librarian=(Librarian)session.getAttribute("librarianEntity");
		LibrarianDAO librarianDAO=new LibrarianDAO();
		//获取表单数据
		String oldPw=request.getParameter("old");
		String newPw=request.getParameter("new");
		String confirm=request.getParameter("confirm");
		//检查“新密码”和“确认密码”是否相同
		if(newPw.equals(confirm)) {
			if(librarianDAO.changePasswordByOldPassword_NewPassword(librarian.getName(), oldPw,newPw)) {
				System.out.println("--Librarian--, 修改密码成功");
				response.sendRedirect("SuccessChangePassword.jsp");
			}else {
				System.out.println("旧密码错误");
				response.sendRedirect("librarianModifyInfo.jsp");
				
			}
		}else {
			System.out.println("新密码和确认密码不相同");
			response.sendRedirect("librarianModifyInfo.jsp");
		}
		
	}

}
