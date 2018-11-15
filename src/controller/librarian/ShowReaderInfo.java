package controller.librarian;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 该类用于展示reader信息，供librarian查看
 * 接收从librarianSearchReader.jsp传来的参数，通过doGet方法
 */
public class ShowReaderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowReaderInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int readerId=Integer.parseInt(request.getParameter("reader_id"));
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
