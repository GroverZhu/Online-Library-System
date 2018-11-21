package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Author;
import util.DatabaseUtil;

/**
 * 对author进行增删改查
 * 
 * @author GroverZhu
 *
 */
public class AuthorDAO {
	/**
	 * 增加一个author
	 * 
	 * @author GroverZhu
	 * @author zengyaoNPU 修改
	 * @param name
	 * @param decsription
	 * @return 若增加成功，则返回author的ID，否则返回-1
	 */
	public int addAuthor(String name, String decsription) {
		int authorId = -1;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			// 修改 @zengyaoNPU
			String query = "select * from author where author_name='" + name + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				int id = rs.getInt("author_id");
				DatabaseUtil.getInstance().closeConnection(conn, st, rs);
				return id;
			} // 修改 @zengyaoNPU
			String add = "insert into author(author_name, author_description) values(?, ?)";
			PreparedStatement sql = conn.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
			sql.setString(1, name);
			sql.setString(2, decsription);
			sql.executeUpdate();
			rs = sql.getGeneratedKeys();
			if (rs.next()) {
				authorId = rs.getInt(1);
			}

			DatabaseUtil.getInstance().closeConnection(conn, sql, rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authorId;
	}

	/**
	 * 通过 author的名字删除一个author
	 * 
	 * @author GroverZhu
	 * @param name
	 * @return 若删除成功，返回为1，否则返回-1
	 */
	public int deleteAuthorByName(String name) {
		int flag = -1;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String delete = "delete from author where author_name = ?";
			PreparedStatement sql = conn.prepareStatement(delete);
			sql.setString(1, name);
			flag = sql.executeUpdate();
			conn.close();
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 通过author的namec从数据库中取出一个数据并实例化该author
	 * 
	 * @author GroverZhu
	 * @param name
	 * @return 查询成功返回一个author，该author的ID不为0
	 */
	public Author searchAuthorByName(String name) {
		Author author = new Author();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String search = "select * from  author where author_name = ?";
			PreparedStatement sql = conn.prepareStatement(search);
			sql.setString(1, name);
			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("author_id");
				String description = rs.getString("author_description");
				author.setDescription(description);
				author.setName(name);
				author.setId(id);
			}

			DatabaseUtil.getInstance().closeConnection(conn, sql, rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return author;
	}

	/**
	 * 通过author的name来更新author的信息(name, description)
	 * 
	 * @author GroverZhu
	 * @param name
	 * @param newName
	 * @param newDescription
	 * @return 更新成功返回1，否则返回0
	 */
	public int updateAuthor(String name, String newName, String newDescription) {
		int flag = -1;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String update = "update author set author_name = ?, author_description = ? where author_name = ?";
			PreparedStatement sql = conn.prepareStatement(update);
			sql.setString(1, newName);
			sql.setString(2, newDescription);
			sql.setString(3, name);
			flag = sql.executeUpdate();

			conn.close();
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
