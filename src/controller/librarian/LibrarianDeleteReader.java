package controller.librarian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReaderDAO;
import entity.Reader;

/**
 * 该类用于librarian删除reader
 * 接收从librarianDeleteReader.jsp传来的参数
 * @author zengyaoNPU
 */
public class LibrarianDeleteReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LibrarianDeleteReader() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			int readerId=Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("name");
			if(name==null) {
				System.out.println("--LibrarianDeleteReader--doPost(),name为空");
				response.sendRedirect("errorDeleteReader.jsp");
			}
			ReaderDAO readerDAO=new ReaderDAO();
			if(readerDAO.deleteReaderById(readerId, name)) {
				System.out.println("--LibrarianDeleteReader--doPost(),删除成功");
				response.sendRedirect("successDeleteReader.jsp");
			}else {
				System.out.println("--LibrarianDeleteReader--doPost(),删除失败");
				response.sendRedirect("errorDeleteReader.jsp");
			}
		}catch(Exception e) {
			System.out.println("--LibrarianDeleteReader--doPost(),输入不合法");
			response.sendRedirect("errorDeleteReader.jsp");
		}
		
	}

}
