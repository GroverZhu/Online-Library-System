package controller.librarian;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReaderDAO;
import entity.Reader;

/**
 * 该类用于librarian查询reader的信息 接收从librarianSearchReader.jsp传来的参数，通过doPost方法
 * 
 * @author zengyaoNPU
 */
public class LibrarianSearchReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReaderDAO readerDAO;
	private int start = 0;// 起始行
	private int count = 5;// 每页显示行数
	private int condition = 0;

	public LibrarianSearchReader() {
		super();
		readerDAO = new ReaderDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (NumberFormatException e) {
			// 当浏览器没有传参数start时
		}
		int next = start + count;// 下一页
		int pre = start - count;// 前一页
		int total = readerDAO.getTotal();
		// 最后一页的起始行
		int last;
		if (0 == total % count) {// 每一页都能展示最大行数
			last = total - count;
		} else {
			last = total - total % count;
		}
		pre = pre < 0 ? 0 : pre;
		next = next > last ? last : next;
		// 设置request属性
		request.setAttribute("next", next);
		request.setAttribute("pre", pre);
		request.setAttribute("last", last);
		request.setAttribute("current", start);
		System.out.println("--LibrarianSearchReader--，分页展示所有读者--,Condition" + 6);
		int condition = Integer.parseInt(request.getParameter("condition"));
		if (condition == 3) {
			// 获取数据库中所有的reader，分页展示
			List<Reader> list = readerDAO.getAllReaders(start, count);
			request.setAttribute("readerList", list);
			request.setAttribute("condition", 3);
			RequestDispatcher dispatcher = request.getRequestDispatcher("librarianSearchReader.jsp");
			dispatcher.forward(request, response);
		} else if (condition == 1) {
		} else if (condition == 3) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 获取参数
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		System.out.println("id=" + id + ",name=" + name + ",state=" + state);
		if (state.equals("unknown")) {
			state = null;
		}
		if (name.equals("")) {
			name = null;
		}
		if (id.equals("")) {
			id = null;
		}
		// 3个值，每个值都有可能为空，因此共有8种条件组合
		if (id != null) { // id 不为空的情况下
			Reader reader = readerDAO.getReaderById(Integer.parseInt(id));
			if (reader != null) {
				// 下面4种组合为正确查询方式
				if (name != null && reader.getName().equals(name) && state == null
						|| state != null && reader.getState().equals(state) && name == null
						|| name == null && state == null || name != null && reader.getName().equals(name)
								&& state != null && reader.getState().equals(state)) {
					request.setAttribute("readerEntity", reader);
					System.out.println("--LibrarianSearchReader--,Condition" + 1);
					RequestDispatcher dispatcher = request.getRequestDispatcher("librarianSearchReader.jsp");
					dispatcher.forward(request, response);
				} else {
					System.out.println("--LibrarianSearchReader--,Condition" + 2);
					PrintWriter out = response.getWriter();
					out.print("<script language='javascript'>" + "alert('There is no such reader!');"
							+ "window.location.href='librarianSearchReader.jsp';" + "</script>");
				}
			} else {
				PrintWriter out = response.getWriter();
				out.print("<script language='javascript'>" + "alert('There is no such reader!');"
						+ "window.location.href='librarianSearchReader.jsp';" + "</script>");
			}
		} else {// id为空
			if (name == null && state != null) {
				System.out.println("--LibrarianSearchReader--,Condition" + 3);
				List<Reader> list = readerDAO.getReaderByState(state);
				request.setAttribute("readerList", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("librarianSearchReader.jsp");
				dispatcher.forward(request, response);
			} else if (name != null && state == null) {
				System.out.println("--LibrarianSearchReader--,Condition" + 4);
				// 获取数据库中所有name相似的reader，分页展示
				List<Reader> list = readerDAO.getReaderByName(name, 0, count);
				request.setAttribute("readerList", list);
				request.setAttribute("condition", 1);
				RequestDispatcher dispatcher = request.getRequestDispatcher("librarianSearchReader.jsp");
				dispatcher.forward(request, response);
			} else if (name != null && state != null) {
				System.out.println("--LibrarianSearchReader--,Condition" + 5);
				List<Reader> list = readerDAO.getReaderByName_State(name, state);
				request.setAttribute("readerList", list);
				request.setAttribute("condition", 2);
				RequestDispatcher dispatcher = request.getRequestDispatcher("librarianSearchReader.jsp");
				dispatcher.forward(request, response);
			} else if (name == null && state == null) {
				System.out.println("--LibrarianSearchReader--,Condition" + 6);
				// 获取数据库中所有的reader，分页展示
				List<Reader> list = readerDAO.getAllReaders();
				request.setAttribute("condition", 3);
				request.setAttribute("readerList", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("librarianSearchReader.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

}
