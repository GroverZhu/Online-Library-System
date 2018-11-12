package entity;

import java.util.Date;

/**
 * 显示读者的借阅车的消息
 * 
 * @author GroverZhu
 * @date 2018-11-12 23:38:00
 */
public class Cart {

	private int bookId;
	private String bookName;
	private Date submitTime; // 当reader把借阅车中的的书提交借阅是，添加时间

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

}
