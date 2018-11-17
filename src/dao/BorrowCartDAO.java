package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entity.Cart;
import util.DatabaseUtil;

public class BorrowCartDAO {
	/**
	 * 根据readerID获取该reader的borrowCart
	 * @author zengyaoNPU
	 * @param readerId
	 * @return
	 */
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
	/**
	 * 
	 * 返回borrow_cart中的所有submit_time非空的條目 ，分页展示
	 * @author zengyaoNPU 
	 * @param start 起始行数
	 * @param count 每页行数
	 * @return 符合条件的列表
	 */
	public List<Cart> getAllBorrowCartOrderByTime(int start, int count){
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		List<Cart> list=new ArrayList<Cart>();
		try {
			conn = DatabaseUtil.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "SELECT reader.`reader_id`,reader.`reader_name`,\r\n" + 
            		"book.`isbn`,book.`book_name`,book_in_library.`book_location`,\r\n" + 
            		"borrow_cart.`book_id`,borrow_cart.`submit_time` \r\n" + 
            		"FROM borrow_cart,reader,book_in_library,book\r\n" + 
            		"WHERE borrow_cart.`book_id`=book_in_library.`book_id`\r\n" + 
            		"AND reader.`reader_id`=borrow_cart.`reader_id`\r\n" + 
            		"AND book.`isbn`=book_in_library.`isbn`\r\n" + 
            		"AND submit_time IS NOT NULL\r\n" + 
            		"ORDER BY submit_time ASC\r\n" + 
            		"LIMIT "+start+","+count;
            rs = st.executeQuery(sql);
            Cart cart;
            while (rs.next()) {
                cart = new Cart();
                cart.setBookId(rs.getInt("book_id"));
                cart.setBookName(rs.getString("book_name"));
                cart.setReaderId(rs.getInt("reader_id"));
                cart.setReaderName(rs.getString("reader_name"));
                cart.setSubmitTime(rs.getTimestamp("submit_time"));
                list.add(cart);
            }
            rs.close();
            st.close();
            conn.close();
            return list;
		}catch(Exception e) {
			System.out.println("--BorrowCartDAO--,getAllBorrowCartOrderByTime(),suffers exception");
			return null;
		}
		
	}
	/**
	 * 返回borrow_cart中submit_time非空的数量
	 * @author zengyaoNPU
	 * @return
	 */
	public int getTotal() {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		int total=-1;
		try {
			conn = DatabaseUtil.getInstance().getConnection();
            st = conn.createStatement();
            String sql="SELECT COUNT(*) AS total FROM borrow_cart WHERE submit_time IS NOT NULL";
            rs=st.executeQuery(sql);
            if(rs.next()) {
            	total=rs.getInt("total");
            }
            return total;
		}catch(Exception e) {
			System.out.println("--BorrowCartDAO--,getTotal(),suffers exception");
			return total;
		}
	}
	/**
	 * librarian同意将书借阅给该读者
	 * @author zengyaoNPU
	 * @param readerId 
	 * @param bookId 
	 * @param librarianId 
	 * @return
	 */
	public boolean agreeBorrowBook(int readerId,int bookId,int librarianId) {
		Connection conn=null;
		Statement st=null;
		PreparedStatement pstmt=null;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			conn.setAutoCommit(false);//开启事务
			st=conn.createStatement();
			//从borrow_cart删除该条目
			String sql="DELETE FROM borrow_cart " + 
					"WHERE book_id="+bookId+" AND reader_id="+readerId+" AND submit_time IS NOT NULL";
			st.executeUpdate(sql);
			//将book_in_library中的相应book改变状态
			sql="UPDATE book_in_library " + 
					"SET state='borrowed' " + 
					"WHERE book_id="+bookId;
			st.executeUpdate(sql);
			//往borrow_item中加入一条数据
			sql="INSERT INTO borrow_item(reader_id,book_id,borrow_librarian_id,borrow_time)\r\n" + 
					"VALUE (?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, readerId);
			pstmt.setInt(2, bookId);
			pstmt.setInt(3, librarianId);
			Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
			pstmt.setTimestamp(4, time);
			pstmt.executeUpdate();
			conn.commit();//提交事务
			pstmt.close();
			st.close();
			conn.close();
			return true;
		}catch(Exception e) {
			System.out.println("--BorrowCartDAO--,agreeBorrowBook(),suffers exception");
			return false;
			
		}
	}
	/**
	 * librarian不同意将该书借给该读者
	 * @author zengyaoNPU
	 * @param readerId
	 * @param bookId
	 * @param librarianId
	 * @return
	 */
	public boolean disagreeBorrowBook(int readerId,int bookId,int librarianId) {
		Connection conn=null;
		Statement st=null;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			conn.setAutoCommit(false);//开启事务
			st=conn.createStatement();
			//从borrow_cart删除该条目
			String sql="DELETE FROM borrow_cart " + 
					"WHERE book_id="+bookId+" AND reader_id="+readerId+" AND submit_time IS NOT NULL";
			st.executeUpdate(sql);
			//将book_in_library中的相应book改变状态
			sql="UPDATE book_in_library " + 
					"SET state='inlib' " + 
					"WHERE book_id="+bookId;
			st.executeUpdate(sql);
			conn.commit();//提交事务
			st.close();
			conn.close();
			return true;
		}catch(Exception e) {
			System.out.println("--BorrowCartDAO--,disagreeBorrowBook(),suffers exception");
			return false;
		}
		
	}
	
}
