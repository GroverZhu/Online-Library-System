package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import util.DatabaseUtil;
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
			//TODO

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
			sql="select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book left join book_in_library on book.isbn=book_in_library.isbn)" 
					+"left join publisher on publisher.publisher_id=book.publisher_id) "+
					" left join writes on writes.isbn=book.isbn "+
			        " left join author on author.author_id=writes.author_id) "+	             
					" where author.author_name like " + "\'%" + author + "%\'  group by book_id ";
		} else {
			//TODO
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
			sql="select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book left join book_in_library on book.isbn=book_in_library.isbn)" 
					+" left join publisher on publisher.publisher_id=book.publisher_id) "+
					" left join writes on writes.isbn=book.isbn "+
			        " left join author on author.author_id=writes.author_id) "+	             
					" where publisher.publisher_name like " + "\'%" + publisher + "%\'  group by book_id";
		} else {

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
		sql="select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book left join book_in_library on book.isbn=book_in_library.isbn)" 
					+" left join publisher on publisher.publisher_id=book.publisher_id) "+
					" left join writes on writes.isbn=book.isbn "+
			        " left join author on author.author_id=writes.author_id) "+	             
					" where book_in_library.book_id=" + id + "group by book_id ";
		
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
			sql="select book_id,book.isbn,book_name,book_price,book_description,publisher.publisher_id,publisher_name,publisher_description,book_location,state,GROUP_CONCAT(author.author_name SEPARATOR ',') from (((book left join book_in_library on book.isbn=book_in_library.isbn)" 
					+" left join publisher on publisher.publisher_id=book.publisher_id) "+
					" left join writes on writes.isbn=book.isbn "+
			        " left join author on author.author_id=writes.author_id) "+	             
					" where book_in_library.book_id=" + isbn + "group by book_id ";
		
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

}