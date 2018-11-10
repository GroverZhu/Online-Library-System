package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import model.Book;
import model.Reader;
import util.DatabaseUtil;

public class BorrowCartDAO {
	public static boolean addToBorrowCart(Book book, Reader reader) {
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String sql = "insert into borrow_cart(book_id,reader_id) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book.getBookId());
			pstmt.setInt(2, reader.getId());

			int rowCount = pstmt.executeUpdate();
			if (rowCount == 0) {
				System.out.println("Insert unsuccessfully!");
			}
			pstmt.close();
			conn.close();
			return rowCount == 1 ? true : false;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,--addToBorrowCart(Book,Reader)--,suffers exception");
			return false;
		}
	}

	public static boolean addToBorrowCart(int bookId, int readerId) {
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String sql = "insert into borrow_cart(book_id,reader_id) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			pstmt.setInt(2, readerId);
			int rowCount = pstmt.executeUpdate();
			System.out.println("OK");
			if (rowCount == 0) {
				System.out.println("Insert unsuccessfully!");
			}
			pstmt.close();
			conn.close();
			return rowCount == 1 ? true : false;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,--addToBorrowCart(int,int)--,suffers exception");
			return false;
		}
	}

	public static boolean deleteFromBorrowCart(int bookId, int readerId) {
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			String sql = "delete from borrow_cart where book_id=? and reader_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			pstmt.setInt(2, readerId);

			int rowCount = pstmt.executeUpdate();
			if (rowCount == 0) {
				System.out.println("Delete unsuccessfully!");
			}
			pstmt.close();
			conn.close();
			return rowCount == 1 ? true : false;
		} catch (Exception e) {
			System.out.println("--BorrowCartDAO--,--deleteFromBorrowCart()--,suffers exception");
			return false;
		}
	}

	public static void main(String[] args) {
		// addToBorrowCart(7,3);
		deleteFromBorrowCart(7, 3);
	}

}
