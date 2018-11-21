package entity;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * book的属性，在数据库中由表“book”与“book_in_library”同时存储
 * 
 * @author GroverZhu
 * @date 2018-11-12 23:07:00
 *
 * @author Hu Yuxi
 * @date 2018-11-14 19:53:00 Update:把authors改为String类型，sql语句直接返回以逗号分隔的作者姓名列表
 */
public class Book {
	private int id;
	private String ISBN;// in table Book
	private String name;// in table Book
	private BigDecimal price;
	private String description;// in table Book
	private Publisher publisher;// in table Book publisher_id
	private String authors;// in table Book author_id
	private String location;
	private String state; // 书的状态为"inlib"(在库), "borrowed"(借出), "reserve"(待审批)

	public Book() {
		this.authors = new String();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + id + ", ISBN=" + ISBN + ", name=" + name + ", price=" + price + ", description="
				+ description + ", publisher=" + publisher + ", author=" + authors + ", location=" + location
				+ ", status=" + state + "]";
	}

}
