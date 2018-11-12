package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DatabaseUtil;

public class BookInLibraryDAO {
	public static boolean changeStatus(int bookId) {
		Connection conn=null;
		Statement st=null;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			String sql="update book_in_library set status_='待审批' where id="+bookId;
			int row=st.executeUpdate(sql);
			if(row==0) {
				System.out.println("--BookInLibraryDAO--,--changeStatus()--,插入数据失败");
				return false;
			}else {
				System.out.println("--BookInLibraryDAO--,--changeStatus()--,插入数据成功");
				return true;
			}
			
		}catch(Exception e) {
			System.out.println("--BookInLibraryDAO--,--changeStatus()--, suffers exception");
			return false;
		}
	}
}
