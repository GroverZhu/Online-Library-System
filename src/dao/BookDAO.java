package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Author;
import entity.Book;
import entity.Publisher;
import util.DatabaseUtil;

public class BookDAO {
	/**
	 * 根据book_id从book_in_library获取isbn，再从book中获取Book实例（完整信息）
	 * 
	 * @param bookId
	 * @return
	 */
	public static Book getBookById(int bookId) {
		Book book = new Book();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM book_in_library where book_id = " + bookId;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String isbn = rs.getString("isbn");
				String location = rs.getString("book_location");
				String status = rs.getString("status_");
				sql = "SELECT * from book where isbn='" + isbn + "'";
				rs = st.executeQuery(sql);
				if (rs.next()) {
					double price = rs.getDouble("book_price");
					String name = rs.getString("book_name");
					String description = rs.getString("book_description");
					int publisherId = rs.getInt("publisher_id");
					Publisher publisher = PublisherDAO.getPublisherById(publisherId);
					List<Author> authors = AuthorDAO.getAuthorByISBN(isbn);
					book.setBookId(bookId);
					book.setDescription(description);
					book.setISBN(isbn);
					book.setLocation(location);
					book.setName(name);
					book.setPublisher(publisher);
					book.setAuthors(authors);
					book.setStatus(status);
					book.setPrice(price);
					rs.close();
					st.close();
					conn.close();
					return book;
				} else {
					rs.close();
					st.close();
					conn.close();
					System.out.println("--BookDAO--,getBookById()--,Cannot find book by isbn = " + isbn);
					return null;
				}
			} else {
				System.out.println(
						"--BookDAO--,getBookById()--,Cannot find Book by book_id = " + bookId + " int book_in_library");
				return null;
			}
		} catch (Exception e) {
			System.out.println("--BookDAO--,getBookById() suffers Exception");
			return null;
		}
	}

	/**
	 * 根据isbn从book_in_library获取List，元素为Book,区分book_id
	 * 
	 * @param isbn
	 * @return
	 */
	public static List<Book> getBookListByIsbn(String isbn) {
		List<Book> list = new ArrayList<Book>();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select book_id from book_in_library where isbn = '" + isbn + "'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("book_id");
				Book book = getBookById(id);
				list.add(book);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--BookDAO--,getBookIdByName() suffers exception");
			return list;
		}
	}

	/**
	 * 根据isbn获取Book，不区分book_id，List是用于放入Map中
	 * 
	 * @param isbn
	 * @return
	 */
	public static List<Book> getBookByIsbn(String isbn) {
		List<Book> list = new ArrayList<Book>();
		try {
			Book book = new Book();
			book = getBookByIsbn(isbn, 1);
			list.add(book);
			return list;
		} catch (Exception e) {
			System.out.println("--BookDAO--,getBookIdByName() suffers exception");
			return null;
		}
	}

	/**
	 * 根据isbn获取Book，不区分book_id，int i是用来标记，重载方法
	 * 
	 * @param isbn
	 * @param i
	 * @return
	 */
	public static Book getBookByIsbn(String isbn, int i) {
		Book book = new Book();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from book where isbn = '" + isbn + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("book_name");
				String description = rs.getString("book_description");
				Publisher publisher = PublisherDAO.getPublisherById(rs.getInt("publisher_id"));
				List<Author> authors = AuthorDAO.getAuthorByISBN(isbn);
				double price = rs.getDouble("book_price");
				book.setName(name);
				book.setAuthors(authors);
				book.setDescription(description);
				book.setISBN(isbn);
				book.setPrice(price);
				book.setPublisher(publisher);
			}
			rs.close();
			st.close();
			conn.close();
			return book;
		} catch (Exception e) {
			System.out.println("--BookDAO--,getBookIdByName() suffers exception");
			return null;
		}
	}

	/**
	 * 根据book_name获取List，元素为Book
	 * 
	 * @param name
	 * @return
	 */
	public static List<Book> getBookListByName(String name) {
		List<Book> list = new ArrayList<Book>();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String sql = "select isbn from book where book_name like '%" + name + "%'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String isbn = rs.getString("isbn");
				Book book = getBookByIsbn(isbn, 1);
				if (book == null) {
					continue;
				} else {
					list.add(book);
				}
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--BookDAO--,getBookListByName() suffers exception");
			return null;
		}
	}

	/**
	 * 获取所有的书名
	 * 
	 * @return
	 */
	public static List<Book> getBookList() {
		List<Book> list = new ArrayList<Book>();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from book";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				String isbn = rs.getString("isbn");
				String name = rs.getString("book_name");
				String description = rs.getString("book_description");
				Publisher publisher = PublisherDAO.getPublisherById(rs.getInt("publisher_id"));
				List<Author> authors = AuthorDAO.getAuthorByISBN(isbn);
				book.setISBN(isbn);
				book.setName(name);
				book.setDescription(description);
				book.setPrice(rs.getDouble("book_price"));
				book.setPublisher(publisher);
				book.setAuthors(authors);
				list.add(book);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--BookDAO--,getBookList() suffers exception");
			return null;
		}
	}

	/**
	 * 根据作者名字获取书本列表
	 * 
	 * @param authorName
	 * @return
	 */
	public static List<Book> getBookListByAuthorName(String authorName) {
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			List<Author> authors = AuthorDAO.getAuthorsByAuthorName(authorName);// 根据作者名字获取相似的作者
			List<String> isbns = new ArrayList<String>();
			for (Author author : authors) {
				String sql = "select isbn from writes where  author_id=" + author.getId();// 获取作者的写的书的isbn
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					isbns.add(rs.getString("isbn"));
				}
			}
			List<Book> bookList = new ArrayList<Book>();
			for (String isbn : isbns) {
				Book book = BookDAO.getBookByIsbn(isbn, 1);// 获取该isbn的书
				bookList.add(book);
			}
			st.close();
			conn.close();
			return bookList;
		} catch (Exception e) {
			System.out.println("--BookDAO--,getBookListByAuthorName() suffers exception");
			return null;
		}
	}

	/**
	 * 根据相似PublisherName获取Book的实体列表
	 * 
	 * @param publisherName
	 * @return
	 */
	public static List<Book> getBookListByPublisherName(String publisherName) {
		List<Book> list = new ArrayList<Book>();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			List<Publisher> publishers = PublisherDAO.getPublisherByPublisherName(publisherName);// 根据publisherName获取Publisher列表
			for (Publisher publisher : publishers) {
				String sql = "select isbn from book where publisher_id =" + publisher.getId();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					String isbn = rs.getString("isbn");
					Book book = BookDAO.getBookByIsbn(isbn, 1);// 获取一个Book实体，不区分book_id
					list.add(book);
				}
			}
			if (list.isEmpty()) {
				System.out.println("No such book that Publisher Name contains " + publisherName);
			}
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--BookDAO--,getBookListByPublisherName() suffers exception");
			return null;
		}
	}

	public static void main(String[] args) {
		List<Book> books = getBookListByName("云技术");

		for (Book i : books) {
			System.out.println(i.toString());
		}
	}

}
