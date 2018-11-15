package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.BorrowItem;
import util.DatabaseUtil;

public class BorrowItemDAO {

	/**
	 * 获取当前借阅，根据读者ID
	 * @author zengyaoNPU
	 * @param readerId 
	 * @return 列表
	 */
	public List<BorrowItem> getBorrowItemInCurrent(int readerId){
		Connection conn=null;
		Statement st=null;
		ResultSet rs;
		List<BorrowItem> list=new ArrayList<BorrowItem>();
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			String sql="SELECT reader.reader_id,reader.`reader_name`,book.isbn,book.book_name,book_in_library.book_id,borrow_item.`borrow_time`,borrow_item.`return_time`,borrow_item.`borrow_id`,borrow_item.`borrow_librarian_id`,book_in_library.`state`\r\n" + 
					"FROM book,reader,book_in_library,borrow_item\r\n" + 
					"WHERE borrow_item.`reader_id`=reader.`reader_id` \r\n" + 
					"	AND book_in_library.`book_id`=borrow_item.`book_id`\r\n" + 
					"	AND book.isbn=book_in_library.`isbn`\r\n" + 
					"	AND return_time IS NULL\r\n" + 
					"	AND reader.reader_id="+readerId;
			rs=st.executeQuery(sql);
			while(rs.next()) {
				//获取参数
				int borrowId=rs.getInt("borrow_id");
				int bookId=rs.getInt("book_id");
				String bookName=rs.getString("book_name");
				int borrowLibrarianId=rs.getInt("borrow_librarian_id");
				String state=rs.getString("state");
				Timestamp borrowTime=rs.getTimestamp("borrow_time");
				Timestamp returnTime=rs.getTimestamp("borrow_time");
				returnTime.setTime(returnTime.getTime() + 1000*60*60*24*15);
				returnTime.setTime(returnTime.getTime() + 1000*60*60*24*15);
				//实例化
				BorrowItem borrowItem=new BorrowItem();
				borrowItem.setBookId(bookId);
				borrowItem.setBookName(bookName);
				borrowItem.setBorrowId(borrowId);
				borrowItem.setBorrowLibrarianId(borrowLibrarianId);
				borrowItem.setReaderId(readerId);
				borrowItem.setBorrowTime(borrowTime);
				borrowItem.setReturnTime(returnTime);
				borrowItem.setState(state);
				//加入列表
				list.add(borrowItem);				
			}
			return list;
		}catch(Exception e) {
			System.out.println("--BorrowItemDAO--,--getBorrowItemInCurrent()--,suffers exception");
			return null;
		}
		
	}
	/**
	 * 获取借阅历史，根据读者ID
	 * @author zengyaoNPU
	 * @param readerId 
	 * @return 列表
	 */
	public List<BorrowItem> getBorrowItemInHistory(int readerId){
		Connection conn=null;
		Statement st=null;
		ResultSet rs;
		List<BorrowItem> list=new ArrayList<BorrowItem>();
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			String sql="SELECT reader.reader_id,reader.`reader_name`,book.isbn,book.book_name,book_in_library.book_id,borrow_item.`borrow_time`,borrow_item.`return_time`,borrow_item.`borrow_id`,borrow_item.`borrow_librarian_id`,borrow_item.`return_librarian_id`,book_in_library.`state`\r\n" + 
					"FROM book,reader,book_in_library,borrow_item\r\n" + 
					"WHERE borrow_item.`reader_id`=reader.`reader_id` \r\n" + 
					"	AND book_in_library.`book_id`=borrow_item.`book_id`\r\n" + 
					"	AND book.isbn=book_in_library.`isbn`\r\n" + 
					"	AND return_time IS NOT NULL\r\n" + 
					"	AND reader.reader_id="+readerId;
			rs=st.executeQuery(sql);
			while(rs.next()) {
				//获取参数
				int borrowId=rs.getInt("borrow_id");
				int bookId=rs.getInt("book_id");
				String bookName=rs.getString("book_name");
				int borrowLibrarianId=rs.getInt("borrow_librarian_id");
				int returnLibrarianId=rs.getInt("return_librarian_id");
				String state=rs.getString("state");
				Timestamp borrowTime=rs.getTimestamp("borrow_time");
				Timestamp returnTime=rs.getTimestamp("return_time");
				//实例化
				BorrowItem borrowItem=new BorrowItem();
				borrowItem.setBookId(bookId);
				borrowItem.setBookName(bookName);
				borrowItem.setBorrowId(borrowId);
				borrowItem.setBorrowLibrarianId(borrowLibrarianId);
				borrowItem.setReaderId(readerId);
				borrowItem.setBorrowTime(borrowTime);
				borrowItem.setReturnTime(returnTime);
				borrowItem.setReturnLibrarianId(returnLibrarianId);
				borrowItem.setState(state);
				//加入列表
				list.add(borrowItem);				
			}
			return list;
		}catch(Exception e) {
			System.out.println("--BorrowItemDAO--,--getBorrowItemInHistory()--,suffers exception");
			return null;
		}
		
	}
}
