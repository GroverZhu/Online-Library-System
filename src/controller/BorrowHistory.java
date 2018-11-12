package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import model.Book;
import model.BookItemWithTime;
import util.DatabaseUtil;

/**
 * Servlet implementation class BorrowHistory
 */
public class BorrowHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BorrowHistory() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int readerId;
		try {
			readerId=Integer.parseInt(request.getParameter("reader_id"));
		}catch(Exception e) {
			System.out.println("--BorrowHistory--,--doGet()--,Integer.parseInt suffers exception.");
			return ;
		}
		try {
			Connection conn=DatabaseUtil.getInstance().getConnection();
			Statement st=conn.createStatement();
			String sql="select * from borrow_record where status_='已还' and reader_id="+readerId;
			ResultSet rs=st.executeQuery(sql);
			List<BookItemWithTime> list=new ArrayList<BookItemWithTime>();
			while(rs.next()) {
				int bookId=rs.getInt("book_id");
				Timestamp borrowTime=rs.getTimestamp("borrow_time");
				Timestamp returnTime=rs.getTimestamp("return_time");
				Book book=BookDAO.getBookById(bookId);
				BookItemWithTime item=new BookItemWithTime();
				item.setBook(book);
				item.setBorrowTime(borrowTime);
				item.setReturnTime(returnTime);
				item.setStatus("已还");
				list.add(item);
			}
			
			rs.close();
			st.close();
			conn.close();
			request.setAttribute("borrowHistory", list);
			request.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher=request.getRequestDispatcher("../BorrowHistory.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println("--BorrowHistory--,suffers Exception");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
