package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdministratorDAO;
import dao.LibrarianDAO;
import dao.ReaderDAO;

/**
 * 该类用于处理登录请求，根据权限（Reader/Librarian/Administrator）分发到不同的servlet
 * 
 * @author zengyaoNPU
 *
 */
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginHandler() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取参数
		String authority = (String) request.getParameter("authority");
		String userId=request.getParameter("userID");
		int id=Integer.parseInt(userId);
		String password=request.getParameter("password");
		String isRemember=request.getParameter("isRemember");
		System.out.println(isRemember);
		boolean isCorrect=true;
		if(isRemember!=null) {
			//判断用户ID、密码、权限是否正确
//			if (authority.equals("reader")) {
//				ReaderDAO readerDAO=new ReaderDAO();
//				if(readerDAO.getReaderById(id).getPassword().equals(password)) {//密码匹配
//					System.out.println("reader匹配");
//					isCorrect=true;
//				}
//			} else if (authority.equals("librarian")) {
//				LibrarianDAO librarianDAO=new LibrarianDAO();
//				if(librarianDAO.getLibrarianById(id).getPassword().equals(password)) {//密码匹配
//					System.out.println("librarian匹配");
//					isCorrect=true;
//				}
//				
//			} else if (authority.equals("administrator")) {
//				AdministratorDAO administratorDAO=new AdministratorDAO();
//				if(administratorDAO.getAdministratorById(id).getPassword().equals(password)) {//密码匹配
//					System.out.println("administrator匹配");
//					isCorrect=true;
//				}
//				
//			}
			if(isCorrect) {//必须要密码正确才能保存密码
				rememberPassword(userId,password,authority,request,response);//保存密码
			}
		}
		if (authority.equals("reader")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ReaderLogin");
			dispatcher.forward(request, response);
		} else if (authority.equals("librarian")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("LibrarianLogin");
			dispatcher.forward(request, response);
		} else if (authority.equals("administrator")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdministratorLogin");
			dispatcher.forward(request, response);
		}
	}
	private void rememberPassword(String userId,String password,String authority,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
		boolean hasAccountCookie=false;
		Cookie accountCookie=null;
		if(cookies!=null) {//如果cookies不为空（一定不为空，有系统自带的cookie）
			for(Cookie cookie:cookies) {
				//遍历cookies，找到名称为account的cookie
				if(cookie.getName().equals("account")) {
					accountCookie=cookie;//初始化
					hasAccountCookie=true;
					break;
				}
			}
			if(hasAccountCookie==true) {//非首次保存密码
				String cookieValue=accountCookie.getValue();
				cookieValue+="&"+userId+"="+password+"="+authority;
				Cookie cookie=new Cookie("account",cookieValue);//更新cookie的值
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
				System.out.println();
			}else {//首次保存密码，直接初始化一个cookie
				Cookie cookie=new Cookie("account",userId+"="+password+"="+authority);
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
			}
		}else {
			return ;
		}
		
		
	}

}
