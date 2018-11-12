package entity;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private int bookId;
	private String ISBN;// in table Book
	private String name;// in table Book
	private double price;
	private String description;// in table Book
	private Publisher publisher;// in table Book publisher_id
	private List<Author> authors;// in table Book author_id
	private String location;
	private String status;

	public Book() {
		this.authors = new ArrayList<Author>();
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", ISBN=" + ISBN + ", name=" + name + ", price=" + price + ", description="
				+ description + ", publisher=" + publisher + ", author=" + authors + ", location=" + location
				+ ", status=" + status + "]";
	}

}
