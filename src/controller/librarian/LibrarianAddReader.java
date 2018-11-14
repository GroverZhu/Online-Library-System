package controller.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReaderDAO;

/**
 * Servlet implementation class LibrarianAddReader
 */
public class LibrarianAddReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibrarianAddReader() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取参数
		String name=request.getParameter("readerName");//用户名
		String password=request.getParameter("password");//密码
		String state=request.getParameter("state");//状态
		//数据库操作
		ReaderDAO readerDAO=new ReaderDAO();
		int readerId=readerDAO.addReaderByName_Passowrd_State(name, password, state);
		if(readerId==-1) {
			System.out.println("添加Reader失败");
			response.sendRedirect("errorAddReader.jsp");//添加失败，跳转到错误页面
		}else {
			System.out.println("添加Reader成功，新的reader id="+readerId);
			response.sendRedirect("successAddReader.jsp");//添加成功，跳转到成功页面
		}
	}
	

}
