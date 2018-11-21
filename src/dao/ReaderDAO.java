package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Reader;
import util.DatabaseUtil;

/**
 * @author zengyaoNPU
 *
 */
public class ReaderDAO {
	/**
	 * 获取reader总数
	 * 
	 * @author zengyaoNPU
	 * @return
	 */
	public int getTotal() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT COUNT(*) AS total FROM reader";
			System.out.println(sql);
			rs = st.executeQuery(sql);
			int total = 0;
			while (rs.next()) {
				total = rs.getInt("total");
			}
			rs.close();
			st.close();
			conn.close();
			return total;
		} catch (Exception e) {
			System.out.println("--ReaderDAO--,--getTotal--,suffers exception");
			return 0;
		}
	}

	/**
	 * 获取所有的reader，分页显示
	 * 
	 * @author zengyaoNPU
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Reader> getAllReaders(int start, int count) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Reader> list = new ArrayList<>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT * FROM reader LIMIT " + start + "," + count;
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Reader reader = getReaderById(rs.getInt("reader_id"));
				list.add(reader);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--ReaderDAO--,--getAllReaders--,suffers exception");
			return null;
		}

	}

	/**
	 * 获取所有的reader，分页显示
	 * 
	 * @author zengyaoNPU
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Reader> getAllReaders() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Reader> list = new ArrayList<>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT * FROM reader";
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Reader reader = getReaderById(rs.getInt("reader_id"));
				list.add(reader);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--ReaderDAO--,--getAllReaders--,suffers exception");
			return null;
		}

	}

	/**
	 * librarian添加reader的信息
	 * 
	 * @author zengyaoNPU
	 * @param name
	 *            用户名
	 * @param password
	 *            初始密码
	 * @param state
	 *            初始状态
	 * @return 如果插入成功，则返回id；如果插入失败，则返回-1
	 */
	public int addReaderByName_Passowrd_State(String name, String password, String state) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int readerId = -1;// 初始化为-1
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			// 开启事务
			conn.setAutoCommit(false);
			st = conn.createStatement();
			// 添加数据，email默认为"default"
			String sql = "insert into reader(reader_name,reader_password,state,reader_email) values ('" + name + "','"
					+ password + "','" + state + "','default')";
			st.executeUpdate(sql);
			System.out.println("OK");
			// 获取数据，由于reader_id为自增，因此获取最大整数值，即为刚刚添加的数据
			sql = "select reader_id from reader order by reader_id desc limit 1";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				readerId = rs.getInt("reader_id");// 设置返回值，此时不会为-1
			}
			// 提交事务
			conn.commit();
			// 关闭连接
			st.close();
			conn.close();
			return readerId;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--ReaderDAO--,--addReaderByName_Passowrd_State()--,suffers exception");
			return readerId;
		}

	}

	/**
	 * @author Hu Yuxi
	 * @date 2018-11-15
	 * @param id
	 * @param name
	 * @param password
	 * @param email
	 * @return
	 */
	public int updateReaderInformation(int id, String name, String password, String email) {
		int flag = 0;
		Connection conn = null;
		Statement st = null;
		boolean rs;
		String sql = null;
		sql = "update reader set reader_name= \'" + name + "\' ,reader_password=\'" + password + "\', reader_email=\'"
				+ email + "\' where reader_id=" + String.valueOf(id);
		System.out.println("update reader info sql:" + sql);
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.execute(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return flag;
	}

	/**
	 * 根据id和name删除Reader，其中开启了事务
	 * 
	 * @author zengyaoNPU
	 * @param id
	 *            Reader的ID
	 * @param name
	 *            Reader的用户名
	 * @return id与name不匹配或者id不存在返回false；删除成功返回true
	 */
	public boolean deleteReaderById(int id, String name) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		boolean tag = false;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			String query = "select * from reader where reader_id=" + id + " and reader_name='" + name + "'";
			rs = st.executeQuery(query);
			if (rs.next()) {
				String sql = "delete from reader where reader_id=" + id;
				int row = st.executeUpdate(sql);
				if (row == 1) {
					tag = true;
				}
			} else {
				System.out.println("--ReaderDAO--,--deleteReaderById()--,id和nam不匹配");
				tag = false;
			}
			conn.commit();
			return tag;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--ReaderDAO--,--deleteReaderById()--,suffers exception");
			return false;
		}
	}

	/**
	 * 根据Reader ID获取Reader实例
	 *
	 * @param id
	 * @return
	 * @author zengyaoNPU
	 */
	public Reader getReaderById(int id) {
		Reader reader = new Reader();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from reader where reader_id=" + id;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				// 获取数据
				String name = rs.getString("reader_name");
				String password = rs.getString("reader_password");
				String email = rs.getString("reader_email");
				String state = rs.getString("state");
				// 封装实体
				reader.setId(id);
				reader.setEmail(email);
				reader.setName(name);
				reader.setPassword(password);
				reader.setState(state);
				rs.close();
				st.close();
				conn.close();
				return reader;
			} else {
				System.out.println("--Reader--,--getReaderById()--,Cannot find the Reader by id=" + id);
				rs.close();
				st.close();
				conn.close();
				return null;
			}
		} catch (Exception e) {
			System.out.println("--Reader--,--getReaderById()--,suffers exception");
			return null;
		}
	}

	/**
	 * 修改用户信息
	 *
	 * @param id
	 *            readerID，该值不被改动
	 * @param name
	 *            reader_name
	 * @param password
	 *            reader_password
	 * @param email
	 *            reader_email
	 * @return
	 * @author zengyaoNPU
	 */
	public boolean updateData(int id, String name, String password, String email, String state) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			// 更新Reader的信息
			String sql = "UPDATE reader SET reader_name='" + name + "',reader_password='" + password
					+ "',reader_email='" + email + "',state='" + state + "' WHERE reader_id=" + id;
			st.executeUpdate(sql);
			int row = st.executeUpdate(sql);
			st.close();
			conn.close();
			return true;
		} catch (Exception e) {
			System.out.println("--Reader--,--updateData()--,suffers exception");
			return false;
		}
	}

	/**
	 * 根据reader_name获取读者列表（考虑到多个reader同一个name的情况）
	 * 
	 * @author zengyaoNPU
	 * @param name
	 * @return
	 */
	public List<Reader> getReaderByName(String name) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Reader> list = new ArrayList<Reader>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from reader where reader_name like '%" + name + "%'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Reader reader = getReaderById(rs.getInt("reader_id"));// 根据reader_id获取一个reader实体
				list.add(reader);// 添加到列表中
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据reader_name获取读者列表（考虑到多个reader同一个name的情况）,分页展示
	 * 
	 * @author zengyaoNPU
	 * @param name
	 * @return
	 */
	public List<Reader> getReaderByName(String name, int start, int count) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Reader> list = new ArrayList<Reader>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from reader where reader_name like '%" + name + "%' LIMIT " + start + "," + count;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Reader reader = getReaderById(rs.getInt("reader_id"));// 根据reader_id获取一个reader实体
				list.add(reader);// 添加到列表中
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据state查询用户，返回用户列表
	 * 
	 * @author zengyaoNPU
	 * @param state
	 * @return
	 */
	public List<Reader> getReaderByState(String state) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Reader> list = new ArrayList<Reader>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from reader where state ='" + state + "'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Reader reader = getReaderById(rs.getInt("reader_id"));
				list.add(reader);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据用户名和state获取读者列表
	 * 
	 * @author zengyaoNPU
	 * @param name
	 * @param state
	 * @return
	 */
	public List<Reader> getReaderByName_State(String name, String state) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Reader> list = new ArrayList<Reader>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from reader where state ='" + state + "' and name like '" + name + "'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Reader reader = getReaderById(rs.getInt("reader_id"));
				list.add(reader);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 根据用户名和state获取读者列表，分页展示
	 * 
	 * @author zengyaoNPU
	 * @param name
	 * @param state
	 * @return
	 */
	public List<Reader> getReaderByName_State(String name, String state, int start, int count) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Reader> list = new ArrayList<Reader>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "select * from reader where state ='" + state + "' and name like '" + name + "'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Reader reader = getReaderById(rs.getInt("reader_id"));
				list.add(reader);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 根据readerID获取该reader借阅图书的总数
	 * 
	 * @author zengyaoNPU
	 * @param readerId
	 * @return
	 */
	public int getBorrowTotal(int readerId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int total = 0;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
			st = conn.createStatement();
			String sql = "SELECT COUNT(*) AS borrowTotal  " + "FROM borrow_item " + "WHERE return_time IS NULL "
					+ "AND reader_id=" + readerId;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt("borrowTotal");
			}
			rs.close();
			st.close();
			conn.close();
			return total;
		} catch (Exception e) {
			System.out.println("--ReaderDAO--,getBorrowTotal(),suffers exception");
			return -1;
		}

	}
}
