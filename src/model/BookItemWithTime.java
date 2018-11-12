package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 * 用于整合Book和借阅/归还时间
 * 展示给reader，在BorrowHistory和BorrowCurrent中调用。
 * @author zengyaoNPU
 *
 */
public class BookItemWithTime {
	private Book book;
	private String borrowTime;
	private String returnTime;
	private String status;
	public BookItemWithTime() {
		
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Timestamp borrowTime) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.borrowTime = sdf.format(borrowTime);
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Timestamp returnTime) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.returnTime = sdf.format(returnTime);
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "BookItemWithTime [book=" + book + ", borrowTime=" + borrowTime + ", returnTime=" + returnTime
				+ ", status=" + status + "]";
	}
	

}
