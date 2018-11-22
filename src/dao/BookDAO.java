package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import entity.Book;
import entity.Publisher;
import util.DatabaseUtil;

/**
 * 
 * @author Hu Yuxi
 * @version 1.0
 * @date 2018-11-14 20:00:00
 * @see entity.Book
 *
 */
public class BookDAO {
	/**
	 * 根据ISBN返回每本书籍信息,带有ID，分页展示
	 * 
	 * @author zengyaoNPU
	 * @param isbn
	 * @param start
	 * @param count
	 * @return
	 */
	public Collection<Book> getBookListByIsbn(String isbn, int start, int count) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (isbn != "all" && isbn != "" && isbn != null) {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where book.isbn = " + "\'" + isbn
					+ "\' group by book_id LIMIT " + start + "," + count;
		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 更新书的信息
	 * 
	 * @author zengyaoNPU
	 * @param bookId
	 * @param location
	 * @param state
	 * @return
	 */
	public boolean updateBookInfoById(int bookId, String location, String state) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			String sql = "UPDATE book_in_library SET book_location=?,state=? WHERE book_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, location);
			pstmt.setString(2, state);
			pstmt.setInt(3, bookId);
			int row = pstmt.executeUpdate();
			if (row == 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			System.out.println("--BookDAO--,--updateBookInfoById--,suffers exception");
			return false;
		}

	}

	/**
	 * Search book by title
	 * 
	 * @author Hu Yuxi
	 * @param title
	 * @return book list
	 */
	public Collection searchByTitle(String title) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (title != "all" && title != "" && title != null) {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book left join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn) "
					+ " left join author on author.author_id=writes.author_id " + " where book_name like " + "\'%"
					+ title + "%\' group by book_id ";
		} else {
			return bookColle;
		}
		// select * from (((book left join book_in_library on
		// book.isbn=book_in_library.isbn)
		// left join publisher on publisher.publisher_id=book.publisher_id)
		// left join writes on writes.isbn=book.isbn)
		// left join author on author.author_id=writes.author_id
		// where book_name='白鹿原'

		// select
		// book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name
		// SEPARATOR ',') from (((book left join book_in_library on
		// book.isbn=book_in_library.isbn)
		// left join publisher on publisher.publisher_id=book.publisher_id)
		// left join writes on writes.isbn=book.isbn
		// left join author on author.author_id=writes.author_id)
		// where book_name='白鹿原'
		// group by book_id
		System.out.println("searchByTitle sql:" + sql);
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher = new Publisher();
				publisher.setId(rs.getInt("publisher_id"));
				publisher.setName(rs.getString("publisher_name"));
				publisher.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * Search book by author 有个小问题，搜索的书籍有多个作者时，搜索出来的书籍作者只会返回输入进去的这个作者
	 * 
	 * @param Hu
	 *            Yuxi
	 * @param author
	 * @return book list
	 */
	public Collection searchByAuthor(String author) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (author != "all" && author != "" && author != null) {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book left join book_in_library on book.isbn=book_in_library.isbn) "
					+ "left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where author.author_name like "
					+ "\'%" + author + "%\'  group by book_id ";
		} else {
			return bookColle;
		}
		System.out.println("SearchByAuthor sql:" + sql);
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher = new Publisher();
				publisher.setId(rs.getInt("publisher_id"));
				publisher.setName(rs.getString("publisher_name"));
				publisher.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * Search by publisher
	 * 
	 * @param Hu
	 *            Yuxi
	 * @param author
	 * @return book list
	 */
	public Collection searchByPublisher(String publisher) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (publisher != "all" && publisher != "" && publisher != null) {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) "
					+ " where publisher.publisher_name like " + "\'%" + publisher + "%\'  group by book_id";
		} else {
			return bookColle;
		}
		System.out.println("SearchByPublisher sql:" + sql);
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * Search book by id
	 * 
	 * @author Hu Yuxi
	 * @author zengyaoNPU 修改
	 * @param id
	 * @return book
	 */
	public Book searchByID(int id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		String sql = null;
		sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
				+ " left join publisher on publisher.publisher_id=book.publisher_id) "
				+ " left join writes on writes.isbn=book.isbn "
				+ " left join author on author.author_id=writes.author_id) " + " where book_in_library.book_id=" + id
				+ " group by book_id ";

		System.out.println("SearchByID sql:" + sql);
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("book_id"));// zengyaoNPU添加
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	/**
	 * Search by ISBN
	 * 
	 * @author Hu Yuxi
	 * @param isbn
	 * @return search book information by isbn
	 */
	public Book searchByIsbn(String isbn) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		String sql = null;
		if (isbn != "all" || isbn != null || isbn != "") {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where book_in_library.isbn=" + isbn
					+ "group by book_id ";
		} else {
			return book;
		}
		System.out.println("SearchByID sql:" + sql);
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	/**
	 * 将bookID的书借给读者
	 * 
	 * @author zengyaoNPU
	 * @param bookId
	 * @param readerId
	 * @param librarianId
	 *            在数据库borrow_item表中对应为borrow_librarian_id
	 * @return
	 */
	public boolean lendBook(int bookId, int readerId, int librarianId) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			// 改变book_in_library中 state
			String sql = "update book_in_library set state='borrowed' where book_id=" + bookId;
			st = conn.createStatement();
			st.executeUpdate(sql);
			// 在borrow_item中添加一条数据
			Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
			sql = "insert into borrow_item(reader_id,book_id,borrow_librarian_id,borrow_time) values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, readerId);
			pstmt.setInt(2, bookId);
			pstmt.setInt(3, librarianId);
			pstmt.setTimestamp(4, time);
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
			System.out.println("--BookDAO--,--borrowBook()--, suffers exception");
			return false;
		}

	}

	/**
	 * 该方法不返回作者信息
	 * 
	 * @author Hu Yuxi
	 * @date 2018-11-16
	 * @param start
	 * @param count
	 * @return All book list
	 */
	public Collection<Book> getAllBook(int start, int count) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;

		sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state from (((book_in_library join book on book.isbn=book_in_library.isbn) "
				+ " left join publisher on publisher.publisher_id=book.publisher_id) "
				+ " left join writes on writes.isbn=book.isbn "
				+ " left join author on author.author_id=writes.author_id) " + " order by book_id asc limit ?,? ";

		System.out.println("getAllBook sql:" + sql);
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, count);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 添加书籍
	 * 
	 * @author zengyaoNPU
	 * @date 2018-11-17
	 * @param isbn
	 * @param location
	 * @param number
	 *            书本的数量
	 * @return bookID的一个列表
	 */
	public List<Integer> addBookInLib(String isbn, String location, int number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> bookIdList = new ArrayList<Integer>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			// 往book_in_library添加书本
			String sql = "INSERT INTO book_in_library (isbn,book_location,state) \r\n" + "VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, location);
			pstmt.setString(3, "inlib");
			for (int i = 0; i < number; i++) {
				pstmt.executeUpdate();
				System.out.println("in");
			}
			// 返回新添加的书本的book_Id
			sql = "SELECT * FROM book_in_library ORDER BY book_id DESC LIMIT " + number;
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				bookIdList.add(rs.getInt("book_id"));
			}
			conn.commit();
			return bookIdList;

		} catch (SQLException e) {
			System.out.println("--BookDAO--,--addBookInLib()--,suffers exception");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @author zengyao
	 * @param ISBN
	 * @param price
	 * @param name
	 * @param description
	 * @param publisher
	 * @param author
	 * @return
	 */
	public boolean addNewBook(String ISBN, BigDecimal price, String name, String description, String publisher,
			String author) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			// 查询book表是否存在同一个isbn的书籍
			String sql = "select * from book where isbn='" + ISBN + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				// 不存在同isbn的书籍，则添加
				sql = "insert into book(isbn,book_price,book_name,book_description,publisher_id) "
						+ "values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ISBN);
				System.out.println("OK");
				pstmt.setBigDecimal(2, price);
				pstmt.setString(3, name);
				pstmt.setString(4, description);
				PublisherDAO publisherDAO = new PublisherDAO();
				int publisherID = publisherDAO.searchPublisherByName(publisher).getId();
				System.out.println("publisherID=" + publisherID);
				pstmt.setInt(5, publisherID);
				pstmt.executeUpdate();
				// 在writes表中添加新的关系（先判断是否存在该关系）
				AuthorDAO authorDAO = new AuthorDAO();
				int authorId = authorDAO.searchAuthorByName(author).getId();
				sql = "SELECT * FROM writes WHERE author_id=" + authorId + " AND is|bn='" + ISBN + "'";
				pstmt = conn.prepareStatement(sql);
				if (rs.next()) {
					return true;
				} else {
					sql = "insert into writes(isbn,author_id) values(?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, ISBN);
					pstmt.setInt(2, authorId);
					pstmt.executeUpdate();
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("--BookDAO--,--addNewBook()--,suffers exception");
			return false;
		}
	}

	/**
	 * @author zengyao
	 * @param bookId
	 * @return
	 */
	public int deleteBookById(int bookId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			// 在book_in_library中搜索该书（判断有无），获取状态
			String sql = "SELECT * FROM book_in_library where book_id=" + bookId;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 该书存在
				String state = rs.getString("state");
				if (state.equals("inlib")) {// 状态为inlib
					sql = "DELETE FROM book_in_library\r\n" + "WHERE book_id=" + bookId;
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
					conn.commit();
					rs.close();
					pstmt.close();
					conn.close();
					return 1;
				} else {// 状态不是inlib
					conn.commit();
					rs.close();
					pstmt.close();
					conn.close();
					return 2;
				}
			} else {// 该书不存在（不会进入该分支）
				conn.commit();
				rs.close();
				pstmt.close();
				conn.close();
				return 3;
			}

		} catch (Exception e) {
			System.out.println("--BookDAO--,--deleteBookById()--,suffers exception");
			return 4;
		}

	}

	/**
	 * 根据ISBN返回每本书籍信息,带有ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param isbn
	 * @return book list with ID
	 */
	public Collection<Book> getBookListByIsbn(String isbn) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (isbn != "all" && isbn != "" && isbn != null) {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where book.isbn = " + "\'" + isbn
					+ "\' group by book_id";
		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 根据ISBN返回书籍信息,不带ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param isbn
	 * @return book
	 */
	public Book getBookByIsbn(String isbn) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		String sql = null;
		if (isbn != "all" && isbn != "" && isbn != null) {
			sql = "select book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where book.isbn = " + "\'" + isbn
					+ "\'";
		} else {
			return book;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	/**
	 * 根据书名返回具有相似名称的每本书籍信息，带有ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param title
	 * @return
	 */
	public Collection<Book> getBookListByAlikeTitle(String title) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (title != "all" && title != "" && title != null) {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where book.book_name like " + "\'%"
					+ title + "%\' group by book_id";
		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 根据书名返回具有相似名称的书籍信息列表，不带ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param title
	 * @return
	 */
	public Collection<Book> getBookByAlikeTitle(String title) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (title != "all" && title != "" && title != null) {
			sql = "select book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id)  where book.book_name like " + "\'%"
					+ title + "%\'" + "group by book.isbn ";
		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 根据书名返回名称一致的每本书籍信息，带有ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param title
	 * @return
	 */
	public Collection<Book> getBookListByTitle(String title) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (title != "all" && title != "" && title != null) {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where book.book_name =" + "\'"
					+ title + "\' group by book_id";
		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 根据书名返回名称一致的书籍信息，不带有ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param title
	 * @return
	 */
	public Book getBookByTitle(String title) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;

		String sql = null;
		if (title != "all" && title != "" && title != null) {
			sql = "select book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where book.book_name =" + "\'"
					+ title + "\' group by book.isbn";
		} else {
			return book;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	/**
	 * 根据作者名返回名称相似的每本书籍信息，带有ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param title
	 * @return
	 */
	public Collection<Book> getBookListByAuthor(String author) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (author != "all" && author != "" && author != null) {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ "left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where author.author_name like "
					+ "\'%" + author + "%\'  group by book_id ";
		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 根据作者名返回名称一致的每本书籍信息，不带ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param title
	 * @return
	 */
	public Collection<Book> getBookByAuthor(String author) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (author != "all" && author != "" && author != null) {
			sql = "select book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ "left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where author.author_name like "
					+ "\'%" + author + "%\'  group by book.isbn ";
		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 根据出版社返回名称相似的每本书籍信息，带有ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param publisher
	 * @return
	 */
	public Collection<Book> getBookListByPublisher(String publisher) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (publisher != "all" && publisher != "" && publisher != null) {
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book  join book_in_library on book.isbn=book_in_library.isbn) "
					+ "left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) "
					+ " where publisher.publisher_name like " + "\'%" + publisher + "%\'  group by book_id ";
		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 根据出版社返回名称一致的每本书籍信息，不带ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param publisher
	 * @return
	 */
	public Collection<Book> getBookByPublisher(String publisher) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (publisher != "all" && publisher != "" && publisher != null) {
			sql = "select book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ "left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) "
					+ " where publisher.publisher_name like " + "\'%" + publisher + "%\'  group by book.isbn ";
		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				book = new Book();
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				bookColle.add(book);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 更新书籍状态为reserve
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param id
	 * @return
	 */
	public int updateBookStateToReserve(int id) {
		int flag = 0;
		Connection conn = null;
		Statement st = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			// 改变book_in_library中 state
			String sql = "update book_in_library set state='reserve' where book_id=" + id;
			st.executeUpdate(sql);
			st.close();
			conn.close();
			flag = 1;
			return flag;
		} catch (Exception e) {
			flag = 2;
			e.printStackTrace();
			return flag;
		}
	}

	/**
	 * 根据ISBN返回可以加入购物车的书籍信息,带有ID
	 * 
	 * @author Huyuxi
	 * @date 2018-11-18
	 * @param isbn
	 * @return book list with ID
	 */
	public Collection<Book> getBookListByIsbnForCart(String isbn, int start, int count) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		if (isbn != "all" && isbn != "" && isbn != null) {
			sql = " select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where book.isbn=\'" + isbn
					+ "\' group by book_id desc limit ?,?";

		} else {
			return bookColle;
		}

		try {
			conn = DatabaseUtil.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, count);
			rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setISBN(rs.getString("isbn"));
				book.setName(rs.getString("book_name"));
				book.setPrice(rs.getBigDecimal("book_price"));
				book.setDescription(rs.getString("book_description"));
				// set publisher
				Publisher publisher1 = new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				// set author
				book.setAuthors(rs.getString("GROUP_CONCAT(distinct author.author_name SEPARATOR ',')"));

				book.setLocation(rs.getString("book_location"));
				book.setState(rs.getString("state"));
				bookColle.add(book);
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookColle;
	}

	/**
	 * 馆藏书籍分页功能
	 * 
	 * @author Huyuxi
	 * @return total
	 */
	public int getTotal(String isbn) {
		int total = 0;
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;
		try {

			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			sql = "select count(*),GROUP_CONCAT(distinct author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) "
					+ " left join publisher on publisher.publisher_id=book.publisher_id) "
					+ " left join writes on writes.isbn=book.isbn "
					+ " left join author on author.author_id=writes.author_id) " + " where book.isbn = " + "\'" + isbn
					+ "\'  group by book_id";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				total = total + rs.getInt(1);
			}

			System.out.println("total:" + total);
			st.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

}
