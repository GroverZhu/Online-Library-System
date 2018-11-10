package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Reader;
import util.DatabaseUtil;

public class ReaderDAO {

	public static Reader getReaderById(int id) {
		Reader reader = new Reader();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from reader where reader_id=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("reader_name");
				String password = rs.getString("reader_password");
				String email = rs.getString("reader_email");
				reader.setId(id);
				reader.setEmail(email);
				reader.setName(name);
				reader.setPassword(password);
				List<Integer> borrowCart = getBorrowCart(id);
				reader.setBorrowCart(borrowCart);
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
	 * 根据Book_ID获取borrowCart列表
	 * 
	 * @param id
	 * @return
	 */
	public static List<Integer> getBorrowCart(int id) {
		try {
			List<Integer> list = new ArrayList<Integer>();
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from borrow_cart where reader_id=" + id;
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				list.add(bookId);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--Reader--,--getBorrowCart()--,suffers exception");
			return null;
		}
	}

	public static List<Integer> getBorrowList(int id) {
		try {
			List<Integer> list = new ArrayList<Integer>();
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from borrow_cart where reader_id=" + id;
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				list.add(bookId);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--Reader--,--getBorrowCart()--,suffers exception");
			return null;
		}
	}

	public static void main(String[] args) {
		Reader reader = getReaderById(1);
		System.out.println(reader.toString());

	}

}
