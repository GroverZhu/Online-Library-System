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
 * 查看当前借阅，通过reader_id获取其当前借阅列表。
 * @author zengyaoNPU
 *
 */
public class BorrowCurrent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BorrowCurrent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int readerId;
		try {
			readerId=Integer.parseInt(request.getParameter("reader_id"));
		}catch(Exception e) {
			System.out.println("--BorrowCurrent--,--doGet()--,Integer.parseInt suffers exception.");
			return ;
		}
		try {
			Connection conn=DatabaseUtil.getInstance().getConnection();
			Statement st=conn.createStatement();
			String sql="select * from borrow_record where status_='未还' and reader_id="+readerId;
			ResultSet rs=st.executeQuery(sql);
			List<BookItemWithTime> list=new ArrayList<BookItemWithTime>();
			while(rs.next()) {
				int bookId=rs.getInt("book_id");
				Timestamp borrowTime=rs.getTimestamp("borrow_time");
				Book book=BookDAO.getBookById(bookId);
				BookItemWithTime item=new BookItemWithTime();
				item.setBook(book);
				item.setBorrowTime(borrowTime);//设置借阅时间
				borrowTime.setTime(borrowTime.getTime() + 1000*60*60*24*15);
				borrowTime.setTime(borrowTime.getTime() + 1000*60*60*24*15);
				item.setReturnTime(borrowTime);//设置应还时间
				item.setStatus("未还");
				list.add(item);
			}
			
			rs.close();
			st.close();
			conn.close();
			request.setAttribute("borrowCurrent", list);
			request.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher=request.getRequestDispatcher("../BorrowCurrent.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println("--BorrowCurrent--,suffers Exception");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
