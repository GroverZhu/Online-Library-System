package test;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import dao.BookDAO;
import entity.Book;
import util.*;

/**
 * 测试bookDAO
 * @author Huyuxi
 * @date 2018-11-18
 *
 */
public class TestbookDAO {
	

	public static void main(String[] args) {
		BookDAO bookdao = new BookDAO();
		Collection<Book> bookColle1 = new ArrayList();
		Collection<Book> bookColle2 = new ArrayList();
		Collection<Book> bookColle3 = new ArrayList();
		Collection<Book> bookColle4 = new ArrayList();
		Collection<Book> bookColle5 = new ArrayList();
		Collection<Book> bookColle6 = new ArrayList();
		Collection<Book> bookColle7 = new ArrayList();
		Collection<Book> bookColle8 = new ArrayList();
		
		Book book1=new Book();
		Book book2=new Book();

		
//		bookColle1=bookdao.getBookByAlikeTitle("鹿");
//		bookColle2=bookdao.getBookListByAlikeTitle("鹿");
//		book1=bookdao.getBookByTitle("白鹿原");
//		System.out.println(book1.toString());
//		bookColle3=bookdao.getBookListByTitle("白鹿原");
//		book2=bookdao.getBookByIsbn("9787020026906");
//		System.out.println(book2.toString());
//		bookColle4=bookdao.getBookListByIsbn("9787020026906");
//		bookColle5=bookdao.getBookByAuthor("Na");
//		bookColle6=bookdao.getBookListByAuthor("a");
//		bookColle7=bookdao.getBookListByPublisher("人民");
//		bookColle8=bookdao.getBookByPublisher("人民");
		
//		for( Book book: bookColle8) {
//		System.out.println(book.toString());
//		}
		
//		System.out.println(bookdao.updateBookStateToReserve(2));

	}

}