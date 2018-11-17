package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 采用单一实例化完成对数据库的连接，同时提供关闭数据库方法
 * 
 * @author GroverZhu
 * @date 2018-11-12 22:36:00
 */
public class DatabaseUtil {

	// ?useUnicode=true&characterEncoding=UTF-8是解决后面存取数据库时中文乱码问题,
	// &serverTimezone=GMT解决数据库连接出现时区错误问题
	private String url = "jdbc:mysql://localhost:3306/librarysystem?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
	private String userName = "root";
	private String password = "123456";
	private static DatabaseUtil instance = null;// 单一实例模式

	static {
		// 静态模块注册，保证只注册一次
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("--DatabaseUtil--,Cannot find Class : MySQL Jdbc Driver");
		}

	}

	private DatabaseUtil() {
	}

	/**
	 * 通过该方法获取对象，保证单一实例
	 * 
	 * @return DatabaseUtil
	 */
	public static DatabaseUtil getInstance() {
		// 加锁，防止并发
		synchronized (DatabaseUtil.class) {
			if (instance == null) {
				instance = new DatabaseUtil();
			}
		}
		return instance;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return Connection
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, userName, password);
	}

	/**
	 * 关闭当前数据库的连接
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public void closeConnection(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
