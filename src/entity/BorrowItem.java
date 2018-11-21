package entity;

import java.util.Date;

/**
 * 显示借阅记录 增加了readerName、borrowLibrarianName、returnLibrarianName
 * 
 * @author zengyaoNPU
 * @date 2018-11-15 20:25
 */

public class BorrowItem {
	private int borrowId;
	private int bookId;
	private String bookName;
	private String readerName;
	private int readerId;// zengyaoNPU添加于2018-11-15 17:04
	private int borrowLibrarianId;
	private String borrowLibrarianName;
	private int returnLibrarianId;
	private String returnLibrarianName;
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

	public int getReaderId() {
		return readerId;
	}

	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getBorrowLibrarianName() {
		return borrowLibrarianName;
	}

	public void setBorrowLibrarianName(String borrowLibrarianName) {
		this.borrowLibrarianName = borrowLibrarianName;
	}

	public String getReturnLibrarianName() {
		return returnLibrarianName;
	}

	public void setReturnLibrarianName(String returnLibrarianName) {
		this.returnLibrarianName = returnLibrarianName;
	}

	@Override
	public String toString() {
		return "BorrowItem [borrowId=" + borrowId + ", bookId=" + bookId + ", bookName=" + bookName + ", readerName="
				+ readerName + ", readerId=" + readerId + ", borrowLibrarianId=" + borrowLibrarianId
				+ ", borrowLibrarianName=" + borrowLibrarianName + ", returnLibrarianId=" + returnLibrarianId
				+ ", returnLibrarianName=" + returnLibrarianName + ", borrowTime=" + borrowTime + ", returnTime="
				+ returnTime + ", state=" + state + "]";
	}

}
