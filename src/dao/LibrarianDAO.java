package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import entity.Librarian;
import util.DatabaseUtil;

public class LibrarianDAO {
	/**
	 * 根据用户名获取Librarian实体
	 * @param name
	 * @return
	 */
	public Librarian getLibrarianByName(String name) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			String sql="select * from librarian where librarian_name='"+name+"'";
			rs=st.executeQuery(sql);
			if(rs.next()) {
				Librarian librarian=new Librarian();
				//设置librarian实体的属性
				librarian.setId(rs.getInt("librarian_id"));
				librarian.setName(rs.getString("librarian_name"));
				librarian.setPassword(rs.getString("librarian_password"));
				librarian.setState(rs.getString("state"));
				//返回实体
				return librarian;
			}else {
				return null;//如果无返回对象，则返回null
			}
			
		}catch(Exception e){
			System.out.println("--LibrarianDAO--,--getLibrarianByName()--suffers exception");
			return null;
		}
		
	}

}
