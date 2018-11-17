package test;

import dao.BookDAO;
import entity.Book;

public class Test {
	public static void main(String[] args) {
		BookDAO b = new BookDAO();
		Book book = b.searchByID(2);
		System.out.println(2);

	}
}
