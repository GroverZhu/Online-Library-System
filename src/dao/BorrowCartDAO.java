package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import entity.Book;
import entity.Cart;
import entity.Reader;
import util.DatabaseUtil;

public class BorrowCartDAO {
	/**
	 * 根据readerID获取该reader的borrowCart
	 *
	 * @param readerId
	 * @return
	 * @author zengyaoNPU
	 */
	public List<Cart> getBorrowCartByReaderId(int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Cart> list = new ArrayList<Cart>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT reader.`reader_id`,reader.`reader_name`,\r\n"
					+ "borrow_cart.`book_id`,borrow_cart.`submit_time`,\r\n"
					+ "book.`isbn`,book.`book_name`,book_in_library.`book_location` \r\n"
					+ "FROM borrow_cart,reader,book_in_library,book\r\n"
					+ "WHERE borrow_cart.`book_id`=book_in_library.`book_id`\r\n"
					+ "AND reader.`reader_id`=borrow_cart.`reader_id`\r\n"
					+ "AND book.`isbn`=book_in_library.`isbn`\r\n" + "AND borrow_cart.`submit_time` IS NOT NULL\r\n"
					+ "AND reader.`reader_id`=" + readerId + "\r\n" + "ORDER BY book_id ASC";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Cart cart = new Cart();
				cart.setBookId(rs.getInt("book_id"));
				cart.setBookName(rs.getString("book_name"));
				cart.setReaderId(readerId);
				cart.setReaderName(rs.getString("reader_name"));
				cart.setSubmitTime(rs.getTimestamp("submit_time"));
				list.add(cart);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,getBorrowCartByReaderId(),suffers exception");
			return null;
		}
	}

	/**
	 * 返回borrow_cart中的所有submit_time非空的條目 ，分页展示
	 *
	 * @param start
	 *            起始行数
	 * @param count
	 *            每页行数
	 * @return 符合条件的列表
	 * @author zengyaoNPU
	 */
	public List<Cart> getAllBorrowCartOrderByTime(int start, int count) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Cart> list = new ArrayList<Cart>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT reader.`reader_id`,reader.`reader_name`,\r\n"
					+ "book.`isbn`,book.`book_name`,book_in_library.`book_location`,\r\n"
					+ "borrow_cart.`book_id`,borrow_cart.`submit_time` \r\n"
					+ "FROM borrow_cart,reader,book_in_library,book\r\n"
					+ "WHERE borrow_cart.`book_id`=book_in_library.`book_id`\r\n"
					+ "AND reader.`reader_id`=borrow_cart.`reader_id`\r\n"
					+ "AND book.`isbn`=book_in_library.`isbn`\r\n" + "AND submit_time IS NOT NULL\r\n"
					+ "ORDER BY submit_time ASC\r\n" + "LIMIT " + start + "," + count;
			rs = st.executeQuery(sql);
			Cart cart;
			while (rs.next()) {
				cart = new Cart();
				cart.setBookId(rs.getInt("book_id"));
				cart.setBookName(rs.getString("book_name"));
				cart.setReaderId(rs.getInt("reader_id"));
				cart.setReaderName(rs.getString("reader_name"));
				cart.setSubmitTime(rs.getTimestamp("submit_time"));
				list.add(cart);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,getAllBorrowCartOrderByTime(),suffers exception");
			return null;
		}

	}

	/**
	 * 返回borrow_cart中submit_time非空的数量
	 *
	 * @return
	 * @author zengyaoNPU
	 */
	public int getTotal() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int total = -1;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			System.out.println(conn.toString());
			st = conn.createStatement();
			String sql = "SELECT COUNT(*) AS total FROM borrow_cart WHERE submit_time IS NOT NULL";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt("total");
			}
			return total;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,getTotal(),suffers exception");
			return total;
		}
	}

	/**
	 * librarian同意将书借阅给该读者
	 * 
	 * @author zengyaoNPU
	 * @param readerId
	 * @param bookId
	 * @param librarianId
	 * @return
	 */
	public boolean agreeBorrowBook(int readerId, int bookId, int librarianId) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			System.out.println(conn.toString());
			conn.setAutoCommit(false);// 开启事务
			st = conn.createStatement();
			// 从borrow_cart删除该条目
			String sql = "DELETE FROM borrow_cart " + "WHERE book_id=" + bookId + " AND reader_id=" + readerId
					+ " AND submit_time IS NOT NULL";
			st.executeUpdate(sql);
			System.out.println(getTotal());
			// 将book_in_library中的相应book改变状态
			sql = "UPDATE book_in_library " + "SET state='borrowed' " + "WHERE book_id=" + bookId;
			st.executeUpdate(sql);
			// 往borrow_item中加入一条数据
			sql = "INSERT INTO borrow_item(reader_id,book_id,borrow_librarian_id,borrow_time)\r\n" + "VALUE (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readerId);
			pstmt.setInt(2, bookId);
			pstmt.setInt(3, librarianId);
			Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
			pstmt.setTimestamp(4, time);
			pstmt.executeUpdate();
			System.out.println(getTotal());
			conn.commit();// 提交事务
			pstmt.close();
			System.out.println(getTotal());
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,agreeBorrowBook(),suffers exception");
			return false;

		}
	}

	/**
	 * librarian不同意将该书借给该读者
	 * 
	 * @author zengyaoNPU
	 * @param readerId
	 * @param bookId
	 * @param librarianId
	 * @return
	 */
	public boolean disagreeBorrowBook(int readerId, int bookId, int librarianId) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			conn.setAutoCommit(false);// 开启事务
			st = conn.createStatement();
			// 从borrow_cart删除该条目
			String sql = "DELETE FROM borrow_cart " + "WHERE book_id=" + bookId + " AND reader_id=" + readerId
					+ " AND submit_time IS NOT NULL";
			st.executeUpdate(sql);
			// 将book_in_library中的相应book改变状态
			sql = "UPDATE book_in_library " + "SET state='inlib' " + "WHERE book_id=" + bookId;
			st.executeUpdate(sql);
			conn.commit();// 提交事务
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,disagreeBorrowBook(),suffers exception");
			return false;
		}

	}

	public boolean returnBook(int bookId, int librarianId) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			// 将book_in_library的state改为inlib
			String sql = "UPDATE book_in_library\r\n" + "SET state='inlib'\r\n" + "WHERE book_id=" + bookId;
			st.executeUpdate(sql);
			// 将borrow_item中的return_librarian_id填上，并加上return_time
			sql = "UPDATE borrow_item SET return_librarian_id=?, return_time=? WHERE book_id=? AND return_time IS NULL";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, librarianId);
			pstmt.setInt(3, bookId);
			Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
			pstmt.setTimestamp(2, time);
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,--returnBook()--,suffers exception");
			return false;
		}

	}

	/**
	 * 根据readerID获取该reader的还未提交的borrowCart
	 *
	 * @param readerId
	 * @return
	 * @author Liu Zhuocheng
	 */

	public List<Cart> getNullBorrowCartByReaderId(int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Cart> list = new ArrayList<Cart>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT reader.`reader_id`,reader.`reader_name`,\r\n"
					+ "borrow_cart.`book_id`,borrow_cart.`submit_time`,\r\n"
					+ "book.`isbn`,book.`book_name`,book_in_library.`book_location` \r\n"
					+ "FROM borrow_cart,reader,book_in_library,book\r\n"
					+ "WHERE borrow_cart.`book_id`=book_in_library.`book_id`\r\n"
					+ "AND reader.`reader_id`=borrow_cart.`reader_id`\r\n"
					+ "AND book.`isbn`=book_in_library.`isbn`\r\n" + "AND borrow_cart.`submit_time` IS NULL\r\n"
					+ "AND reader.`reader_id`=" + readerId + "\r\n" + "ORDER BY book_id ASC";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Cart cart = new Cart();
				cart.setBookId(rs.getInt("book_id"));
				cart.setBookName(rs.getString("book_name"));
				cart.setReaderId(readerId);
				cart.setReaderName(rs.getString("reader_name"));
				cart.setSubmitTime(rs.getTimestamp("submit_time"));
				list.add(cart);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,getBorrowCartByReaderId(),suffers exception");
			return null;
		}
	}

	/**
	 * 实现将书添加进购预约车的功能，此时还未提交，因此submit time为null 返回值1表示添加成功
	 *
	 * @author Liu Zhuocheng
	 */

	public int addBorrowCart(int bookID, int readerID) {
		Connection conn = null;
		Statement st = null;
		BookDAO bookDAO = new BookDAO();
		ReaderDAO readerDAO = new ReaderDAO();
		Book book = bookDAO.searchByID(bookID);
		Reader reader = readerDAO.getReaderById(readerID);
		if (book == null) {
			System.out.println("The book is not exist");
			return 0;
		} else if (book.getState() != null && !book.getState().equals("inlib")) {
			System.out.println("This book can't be put into cart,isn't inlib");
			return 0;
		} else if (reader == null) {
			System.out.println("The reader is not exist");
			return 0;
		} else if (reader.getState() != null && !reader.getState().equals("unlock")) {
			System.out.println("This reader has been locked");
			return 0;
		} else {
			List<Cart> carts = getAllBorrowCartByReaderID(readerID);
			for (Cart cart : carts) {
				if (cart.getBookId() == bookID && cart.getReaderId() == readerID) {
					System.out.println("This book has been put into cart");
					return 0;
				}
			}
			try {
				conn = DatabaseUtil.getInstance().getConnection();
				st = conn.createStatement();
				String sql = "insert into borrow_cart(book_id,reader_id) values(" + bookID + "," + readerID + ")";
				st.executeUpdate(sql);
				st.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
			carts = getAllBorrowCartByReaderID(readerID);
			for (Cart cart : carts) {
				if (cart.getBookId() == bookID && cart.getReaderId() == readerID) {
					System.out.println("This book has been put into cart");
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * 通过ReaderID返回所有购物车
	 */
	public List<Cart> getAllBorrowCartByReaderID(int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Cart> list = new ArrayList<Cart>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT reader.`reader_id`,reader.`reader_name`,\r\n"
					+ "borrow_cart.`book_id`,borrow_cart.`submit_time`,\r\n"
					+ "book.`isbn`,book.`book_name`,book_in_library.`book_location` \r\n"
					+ "FROM borrow_cart,reader,book_in_library,book\r\n"
					+ "WHERE borrow_cart.`book_id`=book_in_library.`book_id`\r\n"
					+ "AND reader.`reader_id`=borrow_cart.`reader_id`\r\n"
					+ "AND book.`isbn`=book_in_library.`isbn`\r\n" + "AND reader.`reader_id`=" + readerId + "\r\n"
					+ "ORDER BY book_id ASC";
			rs = st.executeQuery(sql);
			Cart cart;
			while (rs.next()) {
				cart = new Cart();
				cart.setBookId(rs.getInt("book_id"));
				cart.setBookName(rs.getString("book_name"));
				cart.setReaderId(rs.getInt("reader_id"));
				cart.setReaderName(rs.getString("reader_name"));
				cart.setSubmitTime(rs.getTimestamp("submit_time"));
				list.add(cart);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,getBorrowCartByReaderId(),suffers exception");
			return null;
		}
	}

	/**
	 * 此方法用来提交申请，将submit time更改为此刻,因此不用输入时间
	 *
	 * @author Liu Zhuocheng
	 */
	public int updateBorrowCart(int bookID, int readerID) {
		Connection conn = null;
		Statement st = null;
		List<Cart> carts = getAllBorrowCartByReaderID(readerID);
		boolean isexist = false;
		for (Cart cart : carts) {
			if (cart.getReaderId() == readerID && cart.getBookId() == bookID) {
				isexist = true;
			}
		}
		if (!isexist) {// 若不存在已有记录的话
			System.out.println("This book" + bookID + "has not been put into cart by you" + readerID);
			return 0;
		} else {
			BookDAO bookDAO = new BookDAO();
			Book book = bookDAO.searchByID(bookID);
			if (book != null) {
				if (book.getState().equals("reserve")) {
					System.out.println("You have reserved this book");
					return 0;
				} else {
					System.out.println(book.getState());
					// book.updateStateByBookID("reserve",bookID);
					// 此方法未写，应该之后会有
					System.out.println(book.getState());
					Date dates = new Date();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = df.format(dates);
					String sql = "update borrow_cart set submit_time = \"" + date + "\"" + "where book_id =" + bookID
							+ " and reader_id = " + readerID;
					try {
						conn = DatabaseUtil.getInstance().getConnection();
						st = conn.createStatement();
						st.executeUpdate(sql);
						st.close();
						conn.close();
						System.out.println("You can borrow This book now");
						return 1;
					} catch (Exception e) {
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 删除某人某书的记录
	 *
	 * @param bookID
	 * @param readerID
	 * @return 1表示成功，0表示失败
	 * @author LiuZhuocheng
	 */
	public int deleteBorrowCart(int bookID, int readerID) {
		Connection conn = null;
		Statement st = null;
		List<Cart> carts = getAllBorrowCartByReaderID(readerID);
		boolean isexist = false;
		for (Cart cart : carts) {
			if (cart.getReaderId() == readerID && cart.getBookId() == bookID) {
				isexist = true;
			}
		}
		if (!isexist) {// 若不存在已有记录的话
			System.out.println("This book" + bookID + "has not been put into cart by you" + readerID);
			return 0;
		} else {
			String sql = "delete from borrow_cart where book_id = " + bookID + " and reader_id = " + readerID;
			try {
				conn = DatabaseUtil.getInstance().getConnection();
				st = conn.createStatement();
				st.executeUpdate(sql);
				System.out.println("This borrow record has been deleted");
				st.close();
				conn.close();
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		BorrowCartDAO dao = new BorrowCartDAO();
		// dao.updateBorrowCart(2, 1);
		dao.agreeBorrowBook(1, 2, 1);
	}
}
