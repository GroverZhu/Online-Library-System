package controller.reader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReaderDAO;
import entity.Reader;
import util.DatabaseUtil;

/**
 * 该类用于处理Reader登录的业务逻辑
 * 
 * @author zengyaoNPU
 *
 */
public class ReaderLogin extends HttpServlet {
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * 处理Reader登录的业务逻辑
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--ReaderLogin--");
		String readerName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		// System.out.println(readerName);
		// System.out.println(password);
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from reader where reader_name='" + readerName + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				if (readerName.equals(rs.getString("reader_name"))) {
					if (password.equals(rs.getString("reader_password"))) {
						int readerId = rs.getInt("reader_id");
						Reader reader = ReaderDAO.getReaderById(readerId);
						System.out.println("--ReaderLogin--,登录成功");
						HttpSession session = request.getSession();
						session.setAttribute("reader", reader);// 设置属性，Reader
																// Name
						System.out.println("--ReaderLogin--," + reader.toString());
						System.out.println("--ReaderLogin--,set attribute name = reader value = reader entity");
						response.sendRedirect("../controller/BookList");
					} else {
						System.out.println("--ReaderLogin--,密码错误");
					}
				}
			} else {
				System.out.println("--ReaderLogin--,该用户不存在");
			}

		} catch (Exception e) {

		} finally {

		}
	}

}
