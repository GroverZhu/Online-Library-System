package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import entity.Librarian;
import util.DatabaseUtil;

public class LibrarianDAO {
	/**
	 * 根据用户名获取Librarian实体
	 * @author zengyaoNPU
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
	/**
	 * 修改Librarian的密码
	 * @author zengyaoNPU
	 * @param name Librarian的用户名
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 旧密码错误，返回false；修改成功返回true
	 */
	public boolean changePasswordByOldPassword_NewPassword(String name,String oldPassword,String newPassword) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			String sql="select * from librarian where librarian_name='"+name+"'";
			rs=st.executeQuery(sql);
			if(rs.next()) {
				String oldPw=rs.getString("librarian_password");
				if(oldPassword.equals(oldPw)) {//检查旧密码是否匹配
					sql="update librarian set librarian_password='"+newPassword+"' where librarian_name='"+name+"'";
					int row=st.executeUpdate(sql);//更新数据库，将新密码填入
					if(row==0) {//改变0行，说明没有改变，返回false
						System.out.println("--changePasswordByOldPassword_NewPassword--,row==0");
						return false;
					}else {
						return true;//改变1行，更改密码成功
					}
				}else {
					System.out.println("密码不正确");
					return false;
				}
			}else {//未找到数据
				return false;//没有该用户，一般不会出现这种情况
			}
			
		}catch(Exception e){
			System.out.println("--LibrarianDAO--,--changePasswordByOldPassword_NewPassword()--suffers exception");
			return false;
		}
		
	}

}
