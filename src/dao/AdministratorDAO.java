package dao;

import entity.Administrator;
import entity.Librarian;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdministratorDAO {
	/**
	 * 根据ID获取administrator实体
	 *
	 * @param id
	 * @return
	 * @author Liu Zhuocheng
	 */

	public Administrator getAdministratorById(int id) {
		Administrator administrator = new Administrator();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from administrator where administrator_id=" + id;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				// 获取数据
				String name = rs.getString("administrator_name");
				String password = rs.getString("administrator_password");
				// 封装实体
				administrator.setId(id);
				administrator.setName(name);
				administrator.setPassword(password);
				rs.close();
				st.close();
				conn.close();
				return administrator;
			} else {
				System.out.println(
						"--Administrator--,--getAdministratorById()--,Cannot find the Administrator by id=" + id);
				rs.close();
				st.close();
				conn.close();
				return null;
			}
		} catch (Exception e) {
			System.out.println("--Administrator--,--getAdministratorById()--,suffers exception");
			return null;
		}
	}

	/**
	 * 通过Administrator的ID来更新name与password
	 * 
	 * @author GroverZhu
	 * @param id
	 * @param name
	 * @param password
	 * @return 若更新成功的话就返回1，否则更新失败
	 */
	public int updateAdmin(int id, String name, String password) {
		int flag = 0;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String update = "update administrator set administrator_name = ?, administrator_password = ? where administrator_id = ?";
			PreparedStatement sql = conn.prepareStatement(update);
			sql.setString(1, name);
			sql.setString(2, password);
			sql.setInt(3, id);
			flag = sql.executeUpdate();
			conn.close();
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return flag;

	}
}
