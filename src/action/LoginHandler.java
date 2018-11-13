package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String authority = (String) request.getParameter("authority");
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

