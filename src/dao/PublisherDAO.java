package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Publisher;
import util.DatabaseUtil;

public class PublisherDAO {
	/**
	 * 增加一个publisher
	 * 
	 * @author GroverZhu
	 * @author zengyaoNPU 修改
	 * @param name
	 * @param decsription
	 * @return 若增加成功，则返回publisher的ID，否则返回-1
	 */
	public int addPublisher(String name, String decsription) {
		int publisherId = -1;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			// 修改 @ZengyaoNPU
			String query = "select * from publisher where publisher_name='" + name + "'";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("publisher_id");
				rs.close();
				pstmt.close();
				conn.close();
				return id;
			} // 修改 @ZengyaoNPU
			String add = "insert into publisher(publisher_name, publisher_description) values(?, ?)";
			PreparedStatement sql = conn.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
			sql.setString(1, name);
			sql.setString(2, decsription);
			sql.executeUpdate();
			rs = sql.getGeneratedKeys();
			if (rs.next()) {
				publisherId = rs.getInt(1);
			}
			DatabaseUtil.getInstance().closeConnection(conn, sql, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return publisherId;
	}

	/**
	 * 通过 publisher的名字删除一个publisher
	 * 
	 * @author GroverZhu
	 * @param name
	 * @return 若删除成功，返回为1，否则返回-1
	 */
	public int deletePublisherByName(String name) {
		int flag = -1;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String delete = "delete from publisher where publisher_name = ?";
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
	 * 通过publisher的namec从数据库中取出一个数据并实例化该publisher
	 * 
	 * @author GroverZhu
	 * @param name
	 * @return 查询成功返回一个publisher，该publisher的ID不为0
	 */
	public Publisher searchPublisherByName(String name) {
		Publisher publisher = new Publisher();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String search = "select * from  publisher where publisher_name = ?";
			PreparedStatement sql = conn.prepareStatement(search);
			sql.setString(1, name);
			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("publisher_id");
				String description = rs.getString("publisher_description");
				publisher.setDescription(description);
				publisher.setName(name);
				publisher.setId(id);
			}

			DatabaseUtil.getInstance().closeConnection(conn, sql, rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return publisher;
	}

	/**
	 * 通过publisher的name来更新publisher的信息(name, description)
	 * 
	 * @author GroverZhu
	 * @param name
	 * @param newName
	 * @param newDescription
	 * @return 更新成功返回1，否则返回0
	 */
	public int updatePublisher(String name, String newName, String newDescription) {
		int flag = -1;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String update = "update publisher set publisher_name = ?, publisher_description = ? where publisher_name = ?";
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
