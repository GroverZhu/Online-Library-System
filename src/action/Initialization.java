package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Initialization
 */
public class Initialization extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Initialization() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		boolean hasAccountCookie = false;
		Cookie accountCookie = null;
		if (cookies != null) {
			System.out.println("--Initialization--,cookie not null");
			for (Cookie cookie : cookies) {// 遍历cookies，查询名为account的cookie
				if ("account".equals(cookie.getName())) {
					hasAccountCookie = true;
					accountCookie = cookie;
					break;
				}
			}
			if (hasAccountCookie) {
				List<String> idList = new ArrayList<>();// userID的列表
				String cookieValue = accountCookie.getValue();
				System.out.println("--Initialization--,cookieValue=" + cookieValue);
				// 字符串处理
				if (cookieValue.contains("&")) {
					String[] accounts = cookieValue.split("&");
					for (int i = 0; i < accounts.length; i++) {
						String id = accounts[i].split("=")[0];
						String pw = accounts[i].split("=")[1];
						String authority = accounts[i].split("=")[2];
						idList.add(id);
					}
				} else {
					String id = cookieValue.split("=")[0];
					String pw = cookieValue.split("=")[1];
					String authority = cookieValue.split("=")[2];
					idList.add(id);
				}
				// 设置request参数
				session.setAttribute("idList", idList);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
