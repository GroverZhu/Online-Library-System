package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

	// 加上?useUnicode=true&characterEncoding=UTF-8是解决后面存取数据库时中文乱码问题,
	// &serverTimezone=GMT解决数据库连接出现时区错误问题
	private static String url = "jdbc:mysql://localhost:3306/librarysystem?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT";
	private static String userName = "root";
	private static String password = "123456";
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
	 * 获得类实例
	 * 
	 * @return
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
	 * 获得连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, userName, password);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("--DatabaseUtil--,Failed to getConnection()");
		} finally {
			return connection;
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void closeConnection(Connection conn, Statement st, ResultSet rs) {
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
