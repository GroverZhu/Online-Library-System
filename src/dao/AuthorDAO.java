package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Author;
import util.DatabaseUtil;

public class AuthorDAO {
	/**
	 * 根据Author_id获取一个Author实例
	 * 
	 * @param id
	 * @return
	 */
	public static Author getAuthorById(int id) {
		Author author = new Author();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from author where author_id = " + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("author_name");
				String description = rs.getString("author_description");
				author.setDescription(description);
				author.setId(id);
				author.setName(name);
				rs.close();
				st.close();
				conn.close();
				return author;
			} else {
				rs.close();
				st.close();
				conn.close();
				return null;
			}
		} catch (Exception e) {
			System.out.println("--AuthorDAO--,getAuthorById() suffers exception");
			return null;
		}
	}

	/**
	 * 根据isbn获取作者，参考writes表
	 * 
	 * @param isbn
	 * @return
	 */
	public static List<Author> getAuthorByISBN(String isbn) {
		List<Author> list = new ArrayList<>();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from writes where isbn = '" + isbn + "'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Author author = new Author();
				int author_id = rs.getInt("author_id");
				author = getAuthorById(author_id);
				list.add(author);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--AuthorDAO--,getAuthorByISBN() suffers exception");
			return null;
		}
	}

	/**
	 * 从author_name获取作者列表（可能同名,可能名字相似），参考author表
	 * 
	 * @param name
	 * @return
	 */
	public static List<Author> getAuthorsByAuthorName(String name) {
		List<Author> list = new ArrayList<>();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select author_id from author where author_name like '%" + name + "%'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				Author author = new Author();
				int author_id = rs.getInt("author_id");
				System.out.println(author_id);
				author = getAuthorById(author_id);
				list.add(author);
			} else {
				System.out.println("empty");
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--AuthorDAO--,getAuthorByAuthorName() suffers exception");
			return null;
		}
	}

	public static List<String> getIsbnByAuthorName(String name) {
		List<String> list = new ArrayList<>();
		List<Author> authors = getAuthorsByAuthorName(name);
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();

			for (Author author : authors) {
				String sql = "select * from writes where author_id = " + author.getId();
				ResultSet rs = st.executeQuery(sql);
				if (rs.next()) {
					String isbn = rs.getString("isbn");
					list.add(isbn);
				}
			}
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--AuthorDAO--,getIsbnByAuthorName() suffers exception");
			return null;
		}
	}

	public static void main(String[] args) {
		List<Author> authors = getAuthorsByAuthorName("shengangxiang");
		System.out.println("OK");
		for (Author i : authors) {
			System.out.println(i.toString());
		}
		System.out.println("OK");
		List<String> isbns = getIsbnByAuthorName("shengangxiang");
		for (String i : isbns) {
			System.out.println(i.toString());
		}
		// Author author=getAuthorById(1);
		// System.out.println(author.toString());

	}
}
