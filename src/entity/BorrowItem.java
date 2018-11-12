package entity;

import java.util.Date;

/**
 * 显示借阅记录
 * 
 * @author GroverZhu
 * @date 2018-11-12 23:43:00
 */

public class BorrowItem {
	private int borrowId;
	private int bookId;
	private int borrowLibrarianId;
	private int returnLibrarianId;
	private String bookName;
	private Date borrowTime;
	private Date returnTime;
	private String state; // 状态为已还“returned”跟未还“unreturned”
							// 根据returnLibrarianId与returnTime是否为空判断

	/**
	 * 初始化BorrowItem
	 * 
	 * @param borrowId
	 * @param bookId
	 * @param borrowLibrarianId
	 * @param returnLibrarianId
	 * @param bookName
	 * @param borrowTime
	 * @param returnTime
	 * @param state
	 */
	public BorrowItem(int borrowId, int bookId, int borrowLibrarianId, int returnLibrarianId, String bookName,
			Date borrowTime, Date returnTime, String state) {
		super();
		this.borrowId = borrowId;
		this.bookId = bookId;
		this.borrowLibrarianId = borrowLibrarianId;
		this.returnLibrarianId = returnLibrarianId;
		this.bookName = bookName;
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
		this.state = state;
	}

	public BorrowItem() {
		super();
	}

	public int getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBorrowLibrarianId() {
		return borrowLibrarianId;
	}

	public void setBorrowLibrarianId(int borrowLibrarianId) {
		this.borrowLibrarianId = borrowLibrarianId;
	}

	public int getReturnLibrarianId() {
		return returnLibrarianId;
	}

	public void setReturnLibrarianId(int returnLibrarianId) {
		this.returnLibrarianId = returnLibrarianId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
