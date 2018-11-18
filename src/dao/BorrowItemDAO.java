package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.BorrowItem;
import entity.Reader;
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
			String sql="SELECT reader.reader_id,reader.`reader_name`," + 
					"book.isbn,book.book_name," + 
					"book_in_library.book_id,book_in_library.`state`," + 
					"borrow_item.`borrow_time`,borrow_item.`return_time`,borrow_item.`borrow_id`,borrow_item.`borrow_librarian_id`," + 
					"librarian.`librarian_name` " + 
					"FROM book,reader,book_in_library,borrow_item,librarian " + 
					"WHERE borrow_item.`reader_id`=reader.`reader_id`" + 
					"AND book_in_library.`book_id`=borrow_item.`book_id`" + 
					"AND book.isbn=book_in_library.`isbn`" + 
					"AND librarian.`librarian_id`=borrow_item.`borrow_librarian_id`" + 
					"AND return_time IS NULL " + 
					"AND reader.reader_id="+readerId;
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
				String readerName=rs.getString("reader_name");
				String borrowLibrarianName=rs.getString("librarian_name");
				
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
				borrowItem.setReaderName(readerName);
				borrowItem.setBorrowLibrarianName(borrowLibrarianName);
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
			String sql="SELECT reader.reader_id,reader.`reader_name`," + 
					"book.isbn,book.book_name," + 
					"book_in_library.book_id," + 
					"book_in_library.`state`," + 
					"borrow_item.`borrow_time`,borrow_item.`return_time`,borrow_item.`borrow_id`,borrow_item.`borrow_librarian_id`,borrow_item.`return_librarian_id` ," + 
					"lib1.`librarian_name` AS borrow_name,lib2.librarian_name AS return_name " + 
					"FROM book,reader,book_in_library,borrow_item,librarian lib1,librarian lib2 " + 
					"WHERE borrow_item.`reader_id`=reader.`reader_id` " + 
					"AND book_in_library.`book_id`=borrow_item.`book_id`" + 
					"AND book.isbn=book_in_library.`isbn`" + 
					"AND lib1.librarian_id=borrow_item.`borrow_librarian_id`" + 
					"AND lib2.librarian_id=borrow_item.`return_librarian_id`" + 
					"AND return_time IS NOT NULL " + 
					"AND reader.reader_id="+readerId;
			rs=st.executeQuery(sql);
			while(rs.next()) {
				//获取参数
				int borrowId=rs.getInt("borrow_id");
				int bookId=rs.getInt("book_id");
				String bookName=rs.getString("book_name");
				int borrowLibrarianId=rs.getInt("borrow_librarian_id");
				int returnLibrarianId=rs.getInt("return_librarian_id");
				String state=rs.getString("state");
				String readerName=rs.getString("reader_name");
				Timestamp borrowTime=rs.getTimestamp("borrow_time");
				Timestamp returnTime=rs.getTimestamp("return_time");
				String borrowLibrarianName=rs.getString("borrow_name");
				String returnLibrarianName=rs.getString("return_name");
				//实例化
				BorrowItem borrowItem=new BorrowItem();
				borrowItem.setBookId(bookId);
				borrowItem.setBookName(bookName);
				borrowItem.setBorrowId(borrowId);
				borrowItem.setBorrowLibrarianId(borrowLibrarianId);
				borrowItem.setReaderId(readerId);
				borrowItem.setReaderName(readerName);
				borrowItem.setBorrowTime(borrowTime);
				borrowItem.setReturnTime(returnTime);
				borrowItem.setReturnLibrarianId(returnLibrarianId);
				borrowItem.setState(state);
				borrowItem.setBorrowLibrarianName(borrowLibrarianName);
				borrowItem.setReturnLibrarianName(returnLibrarianName);
				//加入列表
				list.add(borrowItem);				
			}
			return list;
		}catch(Exception e) {
			System.out.println("--BorrowItemDAO--,--getBorrowItemInHistory()--,suffers exception");
			return null;
		}
		
	}
	/**
	 * 根据bookID，找到相应的readerID，并获取reader信息，展示给librarian看
	 * @param bookID
	 * @return
	 */
	public Reader getReaderInBorrowItemByBookID(int bookID) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			String sql="SELECT reader.reader_id,reader.`reader_name`,reader.`reader_email`,reader.`state`\r\n" + 
					"FROM borrow_item,reader \r\n" + 
					"WHERE borrow_item.`reader_id`=reader.`reader_id`\r\n" + 
					"AND return_time IS NULL \r\n" + 
					"AND book_id="+bookID;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				int Id=rs.getInt("reader_id");
				String name=rs.getString("reader_name");
				String email=rs.getString("reader_email");
				String state=rs.getString("state");
				Reader reader=new Reader();
				reader.setEmail(email);
				reader.setId(Id);;
				reader.setName(name);
				reader.setState(state);
				return reader;
			}else {
				return null;
			}
		}catch(Exception e) {
			System.out.println("--BorrowItemDAO--,--getReaderInBorrowItemByBookID()--,suffers exception");
			return null;
		}
		
	}
	
	
	public static void main(String[] args) {
		BorrowItemDAO b=new BorrowItemDAO();
		Reader reader=b.getReaderInBorrowItemByBookID(2);
		System.out.println(reader.toString());
	}
}
