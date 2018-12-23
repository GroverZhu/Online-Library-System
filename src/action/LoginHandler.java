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

		if (request.getParameter("authority") == null || request.getParameter("userID") == null
				|| request.getParameter("password") == null || request.getParameter("authority").equals("")
				|| request.getParameter("userID").equals("") || request.getParameter("password").equals("")) {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else {
			// 获取参数
			String authority = (String) request.getParameter("authority");
			String userId = request.getParameter("userID");
			int id = Integer.parseInt(userId);
			String password = request.getParameter("password");
			String isRemember = request.getParameter("isRemember");
			System.out.println("isRemember=" + isRemember);
			boolean isCorrect = true;
			if (isRemember != null) {
				// 判断用户ID、密码、权限是否正确
				// if (authority.equals("reader")) {
				// ReaderDAO readerDAO=new ReaderDAO();
				// if(readerDAO.getReaderById(id).getPassword().equals(password))
				// {//密码匹配
				// System.out.println("reader匹配");
				// isCorrect=true;
				// }
				// } else if (authority.equals("librarian")) {
				// LibrarianDAO librarianDAO=new LibrarianDAO();
				// if(librarianDAO.getLibrarianById(id).getPassword().equals(password))
				// {//密码匹配
				// System.out.println("librarian匹配");
				// isCorrect=true;
				// }
				//
				// } else if (authority.equals("administrator")) {
				// AdministratorDAO administratorDAO=new AdministratorDAO();
				// if(administratorDAO.getAdministratorById(id).getPassword().equals(password))
				// {//密码匹配
				// System.out.println("administrator匹配");
				// isCorrect=true;
				// }
				//
				// }
				if (isCorrect) {// 必须要密码正确才能保存密码
					rememberPassword(userId, password, authority, request, response);// 保存密码
				}
			} else {
				forgetPassword(userId, request, response);
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
	}

	private void rememberPassword(String userId, String password, String authority, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		boolean hasAccountCookie = false;
		Cookie accountCookie = null;
		if (cookies != null) {// 如果cookies不为空（一定不为空，有系统自带的cookie）
			for (Cookie cookie : cookies) {
				// 遍历cookies，找到名称为account的cookie
				if (cookie.getName().equals("account")) {
					accountCookie = cookie;// 初始化
					hasAccountCookie = true;
					break;
				}
			}
			if (hasAccountCookie == true) {// 非首次保存密码
				String cookieValue = accountCookie.getValue();
				System.out.println("--LoginHandler--,cookieValue=" + cookieValue);
				boolean hasStore = false;
				// 查找是否保存过该账号
				if (cookieValue.contains("&")) {
					String[] accounts = cookieValue.split("&");

					for (int i = 0; i < accounts.length; i++) {
						System.out.println("accounts[" + i + "]=" + accounts[i]);
						if (accounts[i].split("=")[0].equals(userId)) {
							hasStore = true;
							break;
						}
					}
				} else {
					if (cookieValue.split("=")[0].equals(userId)) {
						hasStore = true;
					}
				}
				if (hasStore == false) {
					System.out.println("userId=" + userId);
					System.out.println("password=" + password);
					System.out.println("authority=" + authority);
					cookieValue += "&" + userId + "=" + password + "=" + authority;
					Cookie cookie = new Cookie("account", cookieValue);// 更新cookie的值
					cookie.setMaxAge(60 * 60 * 24);
					response.addCookie(cookie);
					System.out.println("--LoginHandler--,添加新的账户");
				} else {
					System.out.println("--LoginHandler--,该账户已被保存");
				}
			} else {// 首次保存密码，直接初始化一个cookie
				Cookie cookie = new Cookie("account", userId + "=" + password + "=" + authority);
				cookie.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie);
			}
		} else {
			return;
		}
	}

	private void forgetPassword(String userId, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		boolean hasAccountCookie = false;
		Cookie accountCookie = null;
		if (cookies != null) {// 如果cookies不为空（一定不为空，有系统自带的cookie）
			for (Cookie cookie : cookies) {
				// 遍历cookies，找到名称为account的cookie
				if (cookie.getName().equals("account")) {
					accountCookie = cookie;// 初始化
					hasAccountCookie = true;
					break;
				}
			}
			if (hasAccountCookie == true) {// 非首次保存密码
				String cookieValue = accountCookie.getValue();
				if (cookieValue.contains("&")) {// 有多个account
					System.out.println("--LoginHandler--,--doGet()--,含有多个账号");
					String[] accounts = cookieValue.split("&");
					int k = -1;
					for (int i = 0; i < accounts.length; i++) {
						if (accounts[i].split("=")[0].equals(userId)) {// 在cookie中找到选中的userID
							k = i;
							break;
						}
					}
					String newCookieValue = "";
					if (k != 0) {
						newCookieValue = accounts[0];
						for (int i = 1; i < accounts.length; i++) {
							if (k == i) {
								continue;
							} else {
								newCookieValue += "&" + accounts[i];
							}
						}
					} else {
						newCookieValue = accounts[1];
						for (int i = 2; i < accounts.length; i++) {
							if (k == i) {
								continue;
							} else {
								newCookieValue += "&" + accounts[i];
							}
						}
					}
					System.out.println("newCookieValue=" + newCookieValue);
					Cookie cookie = new Cookie("account", newCookieValue);
					response.addCookie(cookie);
				} else {// 仅有一个account
					System.out.println("--LoginHandler--,--forgetPassword()--,仅有一个账号");
					if (cookieValue.split("=")[0].equals(userId)) {// 在cookie中找到选中的userID
						Cookie cookie = new Cookie("account", "");
						cookie.setMaxAge(1);// 失效
						response.addCookie(cookie);
					}
				}
			} else {// 不用删除密码
				System.out.println("--LoginHandler--,--forgetPassword()--,没有记录");
				return;
			}
		} else {
			return;
		}

	}

}
