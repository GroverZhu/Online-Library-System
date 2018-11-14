package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entity.Reader;
import util.DatabaseUtil;

public class ReaderDAO {
	/**
	 * 根据Reader ID获取Reader实例
	 * @author zengyaoNPU
	 * @param id
	 * @return
	 */
	public Reader getReaderById(int id) {
		Reader reader=new Reader();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			String sql="select * from reader where reader_id="+id;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				//获取数据
				String name=rs.getString("reader_name");
				String password=rs.getString("reader_password");
				String email=rs.getString("reader_email");
				String state=rs.getString("state");
				//封装实体
				reader.setId(id);
				reader.setEmail(email);
				reader.setName(name);
				reader.setPassword(password);
				reader.setState(state);
				rs.close();
				st.close();
				conn.close();
				return reader;
			}else{
				System.out.println("--ReaderDAO--,--getReaderById()--,Cannot find the Reader by id="+id);
				rs.close();
				st.close();
				conn.close();
				return null;
			}
		}catch(Exception e) {
			System.out.println("--ReaderDAO--,--getReaderById()--,suffers exception");
			return null;
		}
	}
	/**
	 * 修改用户信息
	 * @author zengyaoNPU
	 * @param id readerID，该值不被改动
	 * @param name reader_name
	 * @param password reader_password
	 * @param email reader_email
	 * @return 
	 */
	public boolean updateData(int id,String name,String password,String email,String state) {
		Connection conn=null;
		Statement st=null;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			//更新Reader的信息
			String sql="UPDATE reader SET reader_name='"+name+"',reader_password='"+password+"',reader_email='"+email+"',state='"+state+"' WHERE reader_id="+id;
			st.executeUpdate(sql);
			int row=st.executeUpdate(sql);
			st.close();
			conn.close();
			return true;
		}catch(Exception e) {
			System.out.println("--ReaderDAO--,--updateData()--,suffers exception");
			return false;
		}
	}
	/**
	 * librarian添加reader的信息
	 * @author zengyaoNPU
	 * @param name 用户名
	 * @param password 初始密码
	 * @param state 初始状态
	 * @return 如果插入成功，则返回id；如果插入失败，则返回-1
	 */
	public int addReaderByName_Passowrd_State(String name,String password,String state) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int readerId=-1;//初始化为-1
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			//开启事务
			conn.setAutoCommit(false);
			st=conn.createStatement();
			//添加数据，email默认为"default"
			String sql="insert into reader(reader_name,reader_password,state,reader_email) values ('"+name+"','"+password+"','"+state+"','default')";
			st.executeUpdate(sql);
			System.out.println("OK");
			//获取数据，由于reader_id为自增，因此获取最大整数值，即为刚刚添加的数据
			sql="select reader_id from reader order by reader_id desc limit 1";
			rs=st.executeQuery(sql);
			if(rs.next()) {
				readerId=rs.getInt("reader_id");//设置返回值，此时不会为-1
			}
			//提交事务
			conn.commit();
			//关闭连接
			st.close();
			conn.close();
			return readerId;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("--ReaderDAO--,--addReaderByName_Passowrd_State()--,suffers exception");
			return readerId;
		}
		
	}
}
