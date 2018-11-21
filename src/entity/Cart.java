package entity;

import java.util.Date;

/**
 * 显示读者的借阅车的消息 增加了bookName、readerId、readerName;
 * 
 * @author zengyaoNPU
 * @date 2018-11-15 20:26
 */
public class Cart {

	private int bookId;
	private String bookName;
	private int readerId;
	private String readerName;
	private Date submitTime; // 当reader把借阅车中的的书提交借阅是，添加时间

	public Cart() {

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

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	@Override
	public String toString() {
		return "Cart [bookId=" + bookId + ", bookName=" + bookName + ", readerId=" + readerId + ", readerName="
				+ readerName + ", submitTime=" + submitTime + "]";
	}

}
