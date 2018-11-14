package controller.librarian;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReaderDAO;
import entity.Reader;

/**
 * Servlet implementation class OperateReader
 */
public class OperateReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OperateReader() {
        super();
    }
    /**
     * 接收从ChangeReaderInformation.java通过URL转发传来的参数，展示用户的信息
     * @author zengyaoNPU
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int readerId=Integer.parseInt(request.getParameter("reader_id"));
			ReaderDAO readerDAO=new ReaderDAO();
			Reader reader=readerDAO.getReaderById(readerId);//实例化属性
			request.setAttribute("readerEntity", reader);//设置request属性，仅在下一个被请求页面中使用该属性
			RequestDispatcher dispatcher=request.getRequestDispatcher("../../ShowReaderInformation.jsp");
			dispatcher.forward(request, response);//转发
		}catch(Exception e ) {
			System.out.println("--OperateReader--doGet(),传参错误：很有可能是reader账号没有写对");
		}
	}
	/**
	 * 接收从LibrarianInformation.jsp传来的readerID（属性名为account），实例化一个Reader后，展示用户的信息
	 * @author zengyaoNPU
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int readerId=Integer.parseInt(request.getParameter("account"));//获取参数
			ReaderDAO readerDAO=new ReaderDAO();
			Reader reader=readerDAO.getReaderById(readerId);//实例化属性
			request.setAttribute("readerEntity", reader);//设置request属性，仅在下一个被请求页面中使用该属性
			RequestDispatcher dispatcher=request.getRequestDispatcher("../../ShowReaderInformation.jsp");
			dispatcher.forward(request, response);//转发
		}catch(Exception e) {
			System.out.println("--OperateReader--doPost(),传参错误：很有可能是reader账号没有写对");
			
		}
	}

}
