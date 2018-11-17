package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

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
	 * Search book by title
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
			sql = "select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book left join book_in_library on book.isbn=book_in_library.isbn) "+
					" left join publisher on publisher.publisher_id=book.publisher_id) "+
					" left join writes on writes.isbn=book.isbn) "+
					" left join author on author.author_id=writes.author_id "+
					" where book_name like " + "\'%" + title + "%\' group by book_id ";
		} else {
			return bookColle;
		}
//		select * from (((book left join book_in_library on book.isbn=book_in_library.isbn) 
//				left join publisher on publisher.publisher_id=book.publisher_id)
//				left join writes on writes.isbn=book.isbn)
//				left join author on author.author_id=writes.author_id
//				where book_name='白鹿原'
		
//		select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book left join book_in_library on book.isbn=book_in_library.isbn) 
//				left join publisher on publisher.publisher_id=book.publisher_id)
//				left join writes on writes.isbn=book.isbn
//			    left join author on author.author_id=writes.author_id)
//				where book_name='白鹿原'
//                group by book_id
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
				//set publisher
				Publisher publisher=new Publisher();
				publisher.setId(rs.getInt("publisher_id"));
				publisher.setName(rs.getString("publisher_name"));
				publisher.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher);
				//set author
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
	 * Search book by author
	 * 有个小问题，搜索的书籍有多个作者时，搜索出来的书籍作者只会返回输入进去的这个作者
	 * @param Hu Yuxi
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
			sql="select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book left join book_in_library on book.isbn=book_in_library.isbn) " 
					+"left join publisher on publisher.publisher_id=book.publisher_id) "+
					" left join writes on writes.isbn=book.isbn "+
			        " left join author on author.author_id=writes.author_id) "+	             
					" where author.author_name like " + "\'%" + author + "%\'  group by book_id ";
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
				//set publisher
				Publisher publisher=new Publisher();
				publisher.setId(rs.getInt("publisher_id"));
				publisher.setName(rs.getString("publisher_name"));
				publisher.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher);
				//set author
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
	 * @param Hu Yuxi
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
			sql="select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) " 
					+" left join publisher on publisher.publisher_id=book.publisher_id) "+
					" left join writes on writes.isbn=book.isbn "+
			        " left join author on author.author_id=writes.author_id) "+	             
					" where publisher.publisher_name like " + "\'%" + publisher + "%\'  group by book_id";
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
				//set publisher
				Publisher publisher1=new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				//set author
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
	 * @author Hu Yuxi
	 * @param id
	 * @return book
	 */
	public Book searchByID(int id) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		String sql = null;
		sql="select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) " 
					+" left join publisher on publisher.publisher_id=book.publisher_id) "+
					" left join writes on writes.isbn=book.isbn "+
			        " left join author on author.author_id=writes.author_id) "+	             
					" where book_in_library.book_id=" + id + " group by book_id ";
		
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
				//set publisher
				Publisher publisher1=new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				//set author
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
		if(isbn!="all"||isbn!=null||isbn!="") {
		sql="select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book join book_in_library on book.isbn=book_in_library.isbn) " 
					+" left join publisher on publisher.publisher_id=book.publisher_id) "+
					" left join writes on writes.isbn=book.isbn "+
			        " left join author on author.author_id=writes.author_id) "+	             
					" where book_in_library.isbn=" + isbn + "group by book_id ";
		}else {
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
				//set publisher
				Publisher publisher1=new Publisher();
				publisher1.setId(rs.getInt("publisher_id"));
				publisher1.setName(rs.getString("publisher_name"));
				publisher1.setDescription(rs.getString("publisher_description"));
				book.setPublisher(publisher1);
				//set author
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
	 * @author zengyaoNPU
	 * @param bookId 
	 * @param readerId 
	 * @param librarianId 在数据库borrow_item表中对应为borrow_librarian_id
	 * @return
	 */
	public boolean lendBook(int bookId,int readerId,int librarianId) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement pstmt=null;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			//改变book_in_library中 state
			String sql="update book_in_library set state='borrowed' where book_id="+bookId;
			st=conn.createStatement();
			st.executeUpdate(sql);
			//在borrow_item中添加一条数据
			Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
			sql="insert into borrow_item(reader_id,book_id,borrow_librarian_id,borrow_time) values (?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,readerId);
			pstmt.setInt(2,bookId);
			pstmt.setInt(3, librarianId);
			pstmt.setTimestamp(4, time);
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
			st.close();
			conn.close();
			return true;
		}catch(Exception e) {
			System.out.println("--BookDAO--,--borrowBook()--, suffers exception");
			return false;
		}

	}
 
	/**
	 * 该方法不返回作者信息
	 * @author Hu Yuxi
	 * @date 2018-11-16
	 * @param start
	 * @param count
	 * @return All book list
	 */
	public Collection<Book> getAllBook(int start,int count){
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		Collection bookColle = new ArrayList();
		String sql = null;

		sql="select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state from (((book_in_library join book on book.isbn=book_in_library.isbn) "
				+" left join publisher on publisher.publisher_id=book.publisher_id) "
				+" left join writes on writes.isbn=book.isbn "
				+" left join author on author.author_id=writes.author_id) " 
                +" order by book_id asc limit ?,? ";
		
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
				//set publisher
				Publisher publisher1=new Publisher();
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
	 * @author Huyuxi
	 * @date 2018-11-17
	 * @param isbn
	 * @param location
	 * @param copy
	 * @return
	 */
	public int addBookInLib(String isbn,String location,int copy) {
		int flag=0;
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		Book book = null;
		String sql = null;
		if(isbn!="all"||isbn!=null||isbn!="") {
			
		sql = "insert into bookinlib (isbn,book_location,state) values (" + "\'"
					+ isbn + "\'" + " , " + "\'" + location + "\'" + " , " + "\'"
					+ "inlib\'" + ") returning book_id";
		}else {	
			return flag;		
		}
		
		System.out.println("add book_in_library sql:" + sql);
		
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();		
			ArrayList<Integer> idList=new ArrayList<Integer>();		
			
			for (int i = 0; i < copy; i++) {
				rs=st.executeQuery(sql);
				if(rs.next()) {
					int id=rs.getInt("book_id");
					idList.add(id);
				}			
			conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
}
