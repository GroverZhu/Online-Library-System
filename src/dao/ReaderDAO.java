package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import entity.Reader;
import util.DatabaseUtil;

public class ReaderDAO {
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
				System.out.println("--Reader--,--getReaderById()--,Cannot find the Reader by id="+id);
				rs.close();
				st.close();
				conn.close();
				return null;
			}
		}catch(Exception e) {
			System.out.println("--Reader--,--getReaderById()--,suffers exception");
			return null;
		}
		
	}

}
