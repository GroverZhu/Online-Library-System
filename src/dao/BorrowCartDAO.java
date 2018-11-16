package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Cart;
import util.DatabaseUtil;

public class BorrowCartDAO {
	public List<Cart> getBorrowCartByReaderId(int readerId) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		List<Cart> list=new ArrayList<Cart>();
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			String sql="SELECT reader.`reader_id`,reader.`reader_name`,\r\n" + 
					"borrow_cart.`book_id`,borrow_cart.`submit_time`,\r\n" + 
					"book.`isbn`,book.`book_name`,book_in_library.`book_location` \r\n" + 
					"FROM borrow_cart,reader,book_in_library,book\r\n" + 
					"WHERE borrow_cart.`book_id`=book_in_library.`book_id`\r\n" + 
					"AND reader.`reader_id`=borrow_cart.`reader_id`\r\n" + 
					"AND book.`isbn`=book_in_library.`isbn`\r\n" + 
					"AND borrow_cart.`submit_time` IS NOT NULL\r\n" + 
					"AND reader.`reader_id`="+readerId+"\r\n" + 
					"ORDER BY book_id ASC";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Cart cart=new Cart();
				cart.setBookId(rs.getInt("book_id"));
				cart.setBookName(rs.getString("book_name"));
				cart.setReaderId(readerId);
				cart.setReaderName(rs.getString("reader_name"));
				cart.setSubmitTime(rs.getTimestamp("submit_time"));
				list.add(cart);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		}catch(Exception e) {
			System.out.println("--BorrowCartDAO--,getBorrowCartByReaderId(),suffers exception");
			return null;
		}
	}
	public static void main(String[] args) {
		BorrowCartDAO borrowCartDAO=new BorrowCartDAO();
		List<Cart> list=borrowCartDAO.getBorrowCartByReaderId(1);
	}
}
