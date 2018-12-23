package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AutoFilling
 */
public class AutoFilling extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AutoFilling() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = request.getParameter("param");
		System.out.println(userId);
		Cookie[] cookies = request.getCookies();
		Cookie accountCookie = null;
		boolean has = false;
		if (cookies != null) {// 含有cookie
			System.out.println("--AutoFilling--,--doGet()--,cookie存在");
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("account")) {
					System.out.println("--AutoFilling--,--doGet()--,含有保存账号的cookie");
					has = true;
					accountCookie = cookie;
					break;
				}
			}
			if (has) {// 含有所需cookie
				String cookieValue = accountCookie.getValue();
				if (cookieValue.contains("&")) {// 有多个account
					System.out.println("--AutoFilling--,--doGet()--,含有多个账号");
					String[] accounts = cookieValue.split("&");
					for (int i = 0; i < accounts.length; i++) {
						if (accounts[i].split("=")[0].equals(userId)) {// 在cookie中找到选中的userID
							String password = accounts[i].split("=")[1];
							String authority = accounts[i].split("=")[2];
							System.out.println("--AutoFilling--,--doGet()--,userId=" + userId);
							System.out.println("--AutoFilling--,--doGet()--,password=" + password);
							System.out.println("--AutoFilling--,--doGet()--,authority=" + authority);
							request.setAttribute("userId", userId);
							request.setAttribute("password", password);
							request.setAttribute("authority", authority);
							break;
						}
					}
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				} else {// 仅有一个account
					System.out.println("--AutoFilling--,--doGet()--,仅有一个账号");
					if (cookieValue.split("=")[0].equals(userId)) {// 在cookie中找到选中的userID
						String password = cookieValue.split("=")[1];
						String authority = cookieValue.split("=")[2];
						System.out.println("--AutoFilling--,--doGet()--,userId=" + userId);
						System.out.println("--AutoFilling--,--doGet()--,password=" + password);
						System.out.println("--AutoFilling--,--doGet()--,authority=" + authority);
						request.setAttribute("userId", userId);
						request.setAttribute("password", password);
						request.setAttribute("authority", authority);
					}
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				}
			} else {// 不含有所需cookie
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} else {// 无cookie
			System.out.println("--AutoFilling--,--doGet()--,无cookie");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
