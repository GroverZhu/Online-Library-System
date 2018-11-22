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
	 * 根据readerID获取当前借阅书籍，分页显示
	 * 
	 * @author zengyaoNPU
	 * @param start
	 *            起始行
	 * @param count
	 *            每页行数
	 * @param readerId
	 * @return BorrowItem列表
	 */
	public List<BorrowItem> getBorrowItemInCurrent(int start, int count, int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		List<BorrowItem> list = new ArrayList<BorrowItem>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT reader.reader_id,reader.`reader_name`," + "book.isbn,book.book_name,"
					+ "book_in_library.book_id,book_in_library.`state`,"
					+ "borrow_item.`borrow_time`,borrow_item.`return_time`,borrow_item.`borrow_id`,borrow_item.`borrow_librarian_id`,"
					+ "librarian.`librarian_name` " + "FROM book,reader,book_in_library,borrow_item,librarian "
					+ "WHERE borrow_item.`reader_id`=reader.`reader_id`"
					+ "AND book_in_library.`book_id`=borrow_item.`book_id`" + "AND book.isbn=book_in_library.`isbn`"
					+ "AND librarian.`librarian_id`=borrow_item.`borrow_librarian_id`" + "AND return_time IS NULL "
					+ "AND reader.reader_id=" + readerId + " " + "LIMIT " + start + "," + count;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// 获取参数
				int borrowId = rs.getInt("borrow_id");
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				int borrowLibrarianId = rs.getInt("borrow_librarian_id");
				String state = rs.getString("state");
				Timestamp borrowTime = rs.getTimestamp("borrow_time");
				Timestamp returnTime = rs.getTimestamp("borrow_time");
				returnTime.setTime(returnTime.getTime() + 1000 * 60 * 60 * 24 * 15);
				returnTime.setTime(returnTime.getTime() + 1000 * 60 * 60 * 24 * 15);
				String readerName = rs.getString("reader_name");
				String borrowLibrarianName = rs.getString("librarian_name");

				// 实例化
				BorrowItem borrowItem = new BorrowItem();
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
				// 加入列表
				list.add(borrowItem);
			}
			return list;
		} catch (Exception e) {
			System.out.println(
					"--BorrowItemDAO--,--getBorrowItemInCurrent(int start,int count,int readerId)--,suffers exception");
			return null;
		}

	}

	/**
	 * 获取当前借阅，根据读者ID,不分页显示
	 * 
	 * @author zengyaoNPU
	 * @param readerId
	 * @return 列表
	 */
	public List<BorrowItem> getBorrowItemInCurrent(int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		List<BorrowItem> list = new ArrayList<BorrowItem>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT reader.reader_id,reader.`reader_name`," + "book.isbn,book.book_name,"
					+ "book_in_library.book_id,book_in_library.`state`,"
					+ "borrow_item.`borrow_time`,borrow_item.`return_time`,borrow_item.`borrow_id`,borrow_item.`borrow_librarian_id`,"
					+ "librarian.`librarian_name` " + "FROM book,reader,book_in_library,borrow_item,librarian "
					+ "WHERE borrow_item.`reader_id`=reader.`reader_id`"
					+ "AND book_in_library.`book_id`=borrow_item.`book_id`" + "AND book.isbn=book_in_library.`isbn`"
					+ "AND librarian.`librarian_id`=borrow_item.`borrow_librarian_id`" + "AND return_time IS NULL "
					+ "AND reader.reader_id=" + readerId;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// 获取参数
				int borrowId = rs.getInt("borrow_id");
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				int borrowLibrarianId = rs.getInt("borrow_librarian_id");
				String state = rs.getString("state");
				Timestamp borrowTime = rs.getTimestamp("borrow_time");
				Timestamp returnTime = rs.getTimestamp("borrow_time");
				returnTime.setTime(returnTime.getTime() + 1000 * 60 * 60 * 24 * 15);
				returnTime.setTime(returnTime.getTime() + 1000 * 60 * 60 * 24 * 15);
				String readerName = rs.getString("reader_name");
				String borrowLibrarianName = rs.getString("librarian_name");

				// 实例化
				BorrowItem borrowItem = new BorrowItem();
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
				// 加入列表
				list.add(borrowItem);
			}
			return list;
		} catch (Exception e) {
			System.out.println("--BorrowItemDAO--,--getBorrowItemInCurrent(int readerId)--,suffers exception");
			return null;
		}

	}

	/**
	 * 根据readerID获取BorrowItem列表，分页显示
	 * 
	 * @author zengyaoNPU
	 * @param start
	 *            起始行
	 * @param count
	 *            每页行数
	 * @param readerId
	 * @return BorrowItem列表
	 */
	public List<BorrowItem> getBorrowItemInHistory(int start, int count, int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		List<BorrowItem> list = new ArrayList<BorrowItem>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT reader.reader_id,reader.`reader_name`," + "book.isbn,book.book_name,"
					+ "book_in_library.book_id," + "book_in_library.`state`,"
					+ "borrow_item.`borrow_time`,borrow_item.`return_time`,borrow_item.`borrow_id`,borrow_item.`borrow_librarian_id`,borrow_item.`return_librarian_id` ,"
					+ "lib1.`librarian_name` AS borrow_name,lib2.librarian_name AS return_name "
					+ "FROM book,reader,book_in_library,borrow_item,librarian lib1,librarian lib2 "
					+ "WHERE borrow_item.`reader_id`=reader.`reader_id` "
					+ "AND book_in_library.`book_id`=borrow_item.`book_id`" + "AND book.isbn=book_in_library.`isbn`"
					+ "AND lib1.librarian_id=borrow_item.`borrow_librarian_id`"
					+ "AND lib2.librarian_id=borrow_item.`return_librarian_id`" + "AND return_time IS NOT NULL "
					+ "AND reader.reader_id=" + readerId + " " + "LIMIT " + start + "," + count;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// 获取参数
				int borrowId = rs.getInt("borrow_id");
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				int borrowLibrarianId = rs.getInt("borrow_librarian_id");
				int returnLibrarianId = rs.getInt("return_librarian_id");
				String state = rs.getString("state");
				String readerName = rs.getString("reader_name");
				Timestamp borrowTime = rs.getTimestamp("borrow_time");
				Timestamp returnTime = rs.getTimestamp("return_time");
				String borrowLibrarianName = rs.getString("borrow_name");
				String returnLibrarianName = rs.getString("return_name");
				// 实例化
				BorrowItem borrowItem = new BorrowItem();
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
				// 加入列表
				list.add(borrowItem);
			}
			return list;
		} catch (Exception e) {
			System.out.println(
					"--BorrowItemDAO--,--getBorrowItemInHistory(int start,int count,int readerId)--,suffers exception");
			return null;
		}

	}

	/**
	 * 获取借阅历史，根据读者ID
	 * 
	 * @author zengyaoNPU
	 * @param readerId
	 * @return 列表
	 */
	public List<BorrowItem> getBorrowItemInHistory(int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		List<BorrowItem> list = new ArrayList<BorrowItem>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT reader.reader_id,reader.`reader_name`," + "book.isbn,book.book_name,"
					+ "book_in_library.book_id," + "book_in_library.`state`,"
					+ "borrow_item.`borrow_time`,borrow_item.`return_time`,borrow_item.`borrow_id`,borrow_item.`borrow_librarian_id`,borrow_item.`return_librarian_id` ,"
					+ "lib1.`librarian_name` AS borrow_name,lib2.librarian_name AS return_name "
					+ "FROM book,reader,book_in_library,borrow_item,librarian lib1,librarian lib2 "
					+ "WHERE borrow_item.`reader_id`=reader.`reader_id` "
					+ "AND book_in_library.`book_id`=borrow_item.`book_id`" + "AND book.isbn=book_in_library.`isbn`"
					+ "AND lib1.librarian_id=borrow_item.`borrow_librarian_id`"
					+ "AND lib2.librarian_id=borrow_item.`return_librarian_id`" + "AND return_time IS NOT NULL "
					+ "AND reader.reader_id=" + readerId;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// 获取参数
				int borrowId = rs.getInt("borrow_id");
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				int borrowLibrarianId = rs.getInt("borrow_librarian_id");
				int returnLibrarianId = rs.getInt("return_librarian_id");
				String state = rs.getString("state");
				String readerName = rs.getString("reader_name");
				Timestamp borrowTime = rs.getTimestamp("borrow_time");
				Timestamp returnTime = rs.getTimestamp("return_time");
				String borrowLibrarianName = rs.getString("borrow_name");
				String returnLibrarianName = rs.getString("return_name");
				// 实例化
				BorrowItem borrowItem = new BorrowItem();
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
				// 加入列表
				list.add(borrowItem);
			}
			return list;
		} catch (Exception e) {
			System.out.println("--BorrowItemDAO--,--getBorrowItemInHistory(int readerId)--,suffers exception");
			return null;
		}
	}

	/**
	 * 根据bookID，找到相应的readerID，并获取reader信息，展示给librarian看
	 * 
	 * @author zengyaoNPU
	 * @param bookID
	 * @return
	 */
	public Reader getReaderInBorrowItemByBookID(int bookID) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT reader.reader_id,reader.`reader_name`,reader.`reader_email`,reader.`state`\r\n"
					+ "FROM borrow_item,reader \r\n" + "WHERE borrow_item.`reader_id`=reader.`reader_id`\r\n"
					+ "AND return_time IS NULL \r\n" + "AND book_id=" + bookID;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				int Id = rs.getInt("reader_id");
				String name = rs.getString("reader_name");
				String email = rs.getString("reader_email");
				String state = rs.getString("state");
				Reader reader = new Reader();
				reader.setEmail(email);
				reader.setId(Id);
				;
				reader.setName(name);
				reader.setState(state);
				return reader;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("--BorrowItemDAO--,--getReaderInBorrowItemByBookID()--,suffers exception");
			return null;
		}

	}

	/**
	 * 获取已还的图书列表，分页显示
	 * 
	 * @author zengyaoNPU
	 * @param start
	 *            起始页
	 * @param count
	 *            每页行数
	 * @param readerId
	 * @return BorrowItem的列表
	 */
	public List<BorrowItem> getBorrowItemInReturn(int start, int count, int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<BorrowItem> list = new ArrayList<BorrowItem>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT borrow_item.*,reader.`reader_name`,lib1.`librarian_id` AS blib_id,lib1.`librarian_name` AS blib_name,\r\n"
					+ "lib2.`librarian_id` AS rlib_id,lib2.`librarian_name` AS rlib_name,book.`isbn`,book.`book_name`\r\n"
					+ "FROM borrow_item,book,reader,librarian lib1,librarian lib2,book_in_library\r\n"
					+ "WHERE borrow_item.`book_id`=book_in_library.`book_id`\r\n"
					+ "AND borrow_item.`reader_id`=reader.`reader_id`\r\n"
					+ "AND borrow_item.`borrow_librarian_id`=lib1.`librarian_id`\r\n"
					+ "AND borrow_item.`return_librarian_id`=lib2.`librarian_id`\r\n"
					+ "AND book.`isbn`=book_in_library.`isbn`\r\n" + "AND borrow_item.`return_time` IS NOT NULL\r\n"
					+ "AND borrow_item.`reader_id`=" + readerId + " \r\n" + "LIMIT " + start + "," + count + "";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				BorrowItem item = new BorrowItem();
				item.setBorrowId(rs.getInt("borrow_id"));
				item.setBookId(rs.getInt("book_id"));
				item.setBookName(rs.getString("book_name"));
				item.setReaderId(rs.getInt("reader_id"));
				item.setReaderName(rs.getString("reader_name"));
				item.setBorrowLibrarianId(rs.getInt("borrow_librarian_id"));
				item.setBorrowLibrarianName(rs.getString("blib_name"));
				item.setReturnLibrarianId(rs.getInt("return_librarian_id"));
				item.setReturnLibrarianName(rs.getString("rlib_name"));
				item.setBorrowTime(rs.getTimestamp("borrow_item"));
				item.setReturnTime(rs.getTimestamp("return_time"));
				item.setState("unreturned");
				list.add(item);
			}
			return list;
		} catch (Exception e) {
			System.out.println(
					"--BorrowItemDAO--,--getBorrowItemInReturn(int start,int count,int readerId)--,suffers exception");
			return null;
		}
	}

	/**
	 * 获取所有已还图书，不分页显示
	 * 
	 * @author zengyaoNPU
	 * @param readerId
	 * @return BorrowItem的列表
	 */
	public List<BorrowItem> getBorrowItemInReturn(int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<BorrowItem> list = new ArrayList<BorrowItem>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT borrow_item.*,reader.`reader_name`,lib1.`librarian_id` AS blib_id,lib1.`librarian_name` AS blib_name,\r\n"
					+ "lib2.`librarian_id` AS rlib_id,lib2.`librarian_name` AS rlib_name,book.`isbn`,book.`book_name`\r\n"
					+ "FROM borrow_item,book,reader,librarian lib1,librarian lib2,book_in_library\r\n"
					+ "WHERE borrow_item.`book_id`=book_in_library.`book_id`\r\n"
					+ "AND borrow_item.`reader_id`=reader.`reader_id`\r\n"
					+ "AND borrow_item.`borrow_librarian_id`=lib1.`librarian_id`\r\n"
					+ "AND borrow_item.`return_librarian_id`=lib2.`librarian_id`\r\n"
					+ "AND book.`isbn`=book_in_library.`isbn`\r\n" + "AND borrow_item.`return_time` IS NOT NULL\r\n"
					+ "AND borrow_item.`reader_id`=" + readerId;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				BorrowItem item = new BorrowItem();
				item.setBorrowId(rs.getInt("borrow_id"));
				item.setBookId(rs.getInt("book_id"));
				item.setBookName(rs.getString("book_name"));
				item.setReaderId(rs.getInt("reader_id"));
				item.setReaderName(rs.getString("reader_name"));
				item.setBorrowLibrarianId(rs.getInt("borrow_librarian_id"));
				item.setBorrowLibrarianName(rs.getString("blib_name"));
				item.setReturnLibrarianId(rs.getInt("return_librarian_id"));
				item.setReturnLibrarianName(rs.getString("rlib_name"));
				item.setBorrowTime(rs.getTimestamp("borrow_time"));
				item.setReturnTime(rs.getTimestamp("return_time"));
				item.setState("unreturned");
				list.add(item);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--BorrowItemDAO--,--getBorrowItemInReturn(int readerId)--,suffers exception");
			return null;
		}

	}

}
