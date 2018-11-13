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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int readerId=Integer.parseInt(request.getParameter("account"));
		ReaderDAO readerDAO=new ReaderDAO();
		Reader reader=readerDAO.getReaderById(readerId);
		request.setAttribute("readerEntity", reader);
		RequestDispatcher dispatcher=request.getRequestDispatcher("../../ShowReaderInformation.jsp");
		dispatcher.forward(request, response);
	}

}
