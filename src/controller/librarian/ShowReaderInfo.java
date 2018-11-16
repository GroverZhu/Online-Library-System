package controller.librarian;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BorrowCartDAO;
import dao.BorrowItemDAO;
import entity.BorrowItem;
import entity.Cart;

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
		String param=request.getParameter("param");
		if(param.equals("cart")) {
			BorrowCartDAO borrowCartDAO=new BorrowCartDAO();
			List<Cart> list=borrowCartDAO.getBorrowCartByReaderId(readerId);
			request.setAttribute("borrowCart", list);
			RequestDispatcher dispatcher=request.getRequestDispatcher("showReaderInfo.jsp");
			dispatcher.forward(request, response);
		}else if(param.equals("history")) {
			BorrowItemDAO borrowItemDAO=new BorrowItemDAO();
			List<BorrowItem> list=borrowItemDAO.getBorrowItemInHistory(readerId);
			request.setAttribute("historyList", list);
			RequestDispatcher dispatcher=request.getRequestDispatcher("showReaderInfo.jsp");
			dispatcher.forward(request, response);
		}else if(param.equals("current")) {
			BorrowItemDAO borrowItemDAO=new BorrowItemDAO();
			List<BorrowItem> list=borrowItemDAO.getBorrowItemInCurrent(readerId);
			request.setAttribute("currentList", list);
			RequestDispatcher dispatcher=request.getRequestDispatcher("showReaderInfo.jsp");
			dispatcher.forward(request, response);
			
		}else {
			response.sendRedirect("librarianSearchReader.jsp");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
