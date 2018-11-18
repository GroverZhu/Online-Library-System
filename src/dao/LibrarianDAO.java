package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Librarian;
import util.DatabaseUtil;

public class LibrarianDAO {
	/**
	 * 根据ID获取Librarian实体
	 *
	 * @param id
	 * @return
	 * @author Liu Zhuocheng
	 */

	public Librarian getLibrarianById(int id) {
		Librarian librarian = new Librarian();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from librarian where librarian_id=" + id;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				// 获取数据
				String name = rs.getString("librarian_name");
				String password = rs.getString("librarian_password");
				String state = rs.getString("state");
				// 封装实体
				librarian.setId(id);
				librarian.setName(name);
				librarian.setPassword(password);
				librarian.setState(state);
				rs.close();
				st.close();
				conn.close();
				return librarian;
			} else {
				System.out.println("--Librarian--,--getLibrarianById()--,Cannot find the librarian by id=" + id);
				rs.close();
				st.close();
				conn.close();
				return null;
			}
		} catch (Exception e) {
			System.out.println("--Librarian--,--getLibrarianById()--,suffers exception");
			return null;
		}
	}

	/**
	 * 根据用户名获取Librarian实体
	 *
	 * @param name
	 * @return
	 * @author zengyaoNPU
	 */
	public Librarian getLibrarianByName(String name) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from librarian where librarian_name='" + name + "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				Librarian librarian = new Librarian();
				// 设置librarian实体的属性
				librarian.setId(rs.getInt("librarian_id"));
				librarian.setName(rs.getString("librarian_name"));
				librarian.setPassword(rs.getString("librarian_password"));
				librarian.setState(rs.getString("state"));
				// 返回实体
				return librarian;
			} else {
				return null;// 如果无返回对象，则返回null
			}

		} catch (Exception e) {
			System.out.println("--LibrarianDAO--,--getLibrarianByName()--suffers exception");
			return null;
		}

	}

	/**
	 * 通过Librarian的名字来修改Librarian的密码
	 *
	 * @param name
	 *            Librarian的用户名
	 * @param oldPassword
	 *            旧密码
	 * @param newPassword
	 *            新密码
	 * @return 旧密码错误，返回false；修改成功返回true
	 * @author zengyaoNPU
	 */
	public boolean changePasswordByOldPassword_NewPassword(String name, String oldPassword, String newPassword) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from librarian where librarian_name='" + name + "'";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				String oldPw = rs.getString("librarian_password");
				if (oldPassword.equals(oldPw)) {// 检查旧密码是否匹配
					sql = "update librarian set librarian_password='" + newPassword + "' where librarian_name='" + name
							+ "'";
					int row = st.executeUpdate(sql);// 更新数据库，将新密码填入
					if (row == 0) {// 改变0行，说明没有改变，返回false
						System.out.println("--changePasswordByOldPassword_NewPassword--,row==0");
						return false;
					} else {
						return true;// 改变1行，更改密码成功
					}
				} else {
					System.out.println("密码不正确");
					return false;
				}
			} else {// 未找到数据
				return false;// 没有该用户，一般不会出现这种情况
			}

		} catch (Exception e) {
			System.out.println("--LibrarianDAO--,--changePasswordByOldPassword_NewPassword()--suffers exception");
			return false;
		}

	}

	/**
	 * 根据Librarian的ID来更新Librarian的所有信息
	 * 
	 * @author GroverZhu
	 * @param id
	 *            已存在的Librarian的id
	 * @param name
	 *            新的name
	 * @param password
	 *            新的password
	 * @param state
	 *            新的state,只有unlock 与 blockade两种状态
	 * @return 若更新成功返回1， 否则返回0
	 */
	public int updateLibrarian(int id, String name, String password, String state) {
		int flag = 0;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String update = "update librarian set librarian_name=?, librarian_password = ?, state = ? where librarian_id = ?";
			PreparedStatement sql = conn.prepareStatement(update);
			sql.setString(1, name);
			sql.setString(2, password);
			sql.setString(3, state);
			sql.setInt(4, id);
			flag = sql.executeUpdate();
			conn.close();
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;

	}

	/**
	 * 输入姓名，密码,用户状态来增加一个librarian
	 * 
	 * @author GroverZhu
	 * @param name
	 * @param password
	 *            经过MD5加密
	 * @param state
	 *            仅有“unlock” 与“blockade”
	 * @return 添加成功，返回新增librarian的ID，否则返回-1
	 */
	public int addLibrarian(String name, String password, String state) {
		int librarianId = -1;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String insert = "insert into librarian(librarian_name, librarian_password, state) values(?, ?, ?)";
			PreparedStatement sql = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			sql.setString(1, name);
			sql.setString(2, password);
			sql.setString(3, state);
			sql.executeUpdate();
			ResultSet rs = sql.getGeneratedKeys();
			if (rs.next()) {
				librarianId = rs.getInt(1);
			}
			DatabaseUtil.getInstance().closeConnection(conn, sql, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return librarianId;
	}

	/**
	 * 根据librarian的ID与name去删除一个librarian
	 * 
	 * @author GroverZhu
	 * @param id
	 * @param name
	 * @return 若删除成功，则返回1， 否则返回0
	 */
	public int deleteLibrarianByIdName(int id, String name) {
		int flag = -1;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String delete = "delete from librarian where librarian_id = ? and librarian_name = ?";
			PreparedStatement sql = conn.prepareStatement(delete);
			sql.setInt(1, id);
			sql.setString(2, name);
			flag = sql.executeUpdate();
			conn.close();
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 从数据库中获取一定数量count的librarian来实现分页展示librarian信息
	 * 
	 * @author GroverZhu
	 * @param start
	 *            开始的位数(数据库中第一个元素从0开始计算)
	 * @param count
	 *            要取得个数
	 * @return librarian的一个ArrayList
	 */
	public ArrayList<Librarian> getLibrarianList(int start, int count) {
		ArrayList<Librarian> librarians = new ArrayList<Librarian>();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String query = "select * from librarian order by librarian_id limit ?,?";
			PreparedStatement sql = conn.prepareStatement(query);
			sql.setInt(1, start);
			sql.setInt(2, count);
			ResultSet rs = sql.executeQuery();
			while (rs.next()) {
				Librarian lib = new Librarian();
				int id = rs.getInt("librarian_id");
				String name = rs.getString("librarian_name");
				String state = rs.getString("state");
				lib.setId(id);
				lib.setName(name);
				lib.setState(state);
				librarians.add(lib);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return librarians;
	}

	/**
	 * 获取所有的librarian的数量
	 * 
	 * @author GroverZhu
	 * @return librarian中存储的所有的数量
	 */
	public int getTotal() {
		int total = 0;
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String count = "select count(*) from librarian";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(count);
			if (rs.next()) {
				total = rs.getInt(1);
			}
			conn.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 为方便使用administrator查询librarian，考虑到librarian的名字会有重复
	 * 为了统一在界面上显示，查询的返回值均为ArrayList<Librarian>，便于在界面上统一显示
	 * 将getLibrarianByName()与getLibrarianById()的方法重写
	 * 
	 * @author GroverZhu
	 */

	public ArrayList<Librarian> getLibrarianListByName(String name) {
		ArrayList<Librarian> libs = new ArrayList<Librarian>();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String query = "select * from librarian where librarian_name like ?";
			PreparedStatement sql = conn.prepareStatement(query);
			sql.setString(1, "%" + name + "%");
			ResultSet rs = sql.executeQuery();
			while (rs.next()) {
				Librarian lib = new Librarian();
				int id = rs.getInt("librarian_id");
				String nameDiff = rs.getString("librarian_name");
				String state = rs.getString("state");
				lib.setId(id);
				lib.setName(nameDiff);
				lib.setState(state);
				libs.add(lib);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libs;
	}

	public ArrayList<Librarian> getLibrarianListById(int id) {
		ArrayList<Librarian> lib = new ArrayList<Librarian>();
		Librarian librarian = new Librarian();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from librarian where librarian_id=" + id;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				// 获取数据
				String name = rs.getString("librarian_name");
				String password = rs.getString("librarian_password");
				String state = rs.getString("state");
				// 封装实体
				librarian.setId(id);
				librarian.setName(name);
				librarian.setPassword(password);
				librarian.setState(state);
				rs.close();
				st.close();
				conn.close();
				lib.add(librarian);
				return lib;
			} else {
				System.out.println("--Librarian--,--getLibrarianById()--,Cannot find the librarian by id=" + id);
				rs.close();
				st.close();
				conn.close();
				return null;
			}
		} catch (Exception e) {
			System.out.println("--Librarian--,--getLibrarianById()--,suffers exception");
			return null;
		}
	}
}
