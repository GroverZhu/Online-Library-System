package controller.librarian;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.PublisherDAO;
import entity.Book;

/**
 * 该servlet用于librarian添加书籍
 * 
 * @author zengyaoNPU
 * @date 2018-11-17 21:43
 *
 */
public class LibrarianAddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LibrarianAddBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Book newbook = new Book();
		BookDAO bookdao = new BookDAO();
		// 获取表单数据
		int number = Integer.parseInt(request.getParameter("Number"));
		String ISBN = request.getParameter("ISBN");
		String name = request.getParameter("Book Name");
		String description = request.getParameter("Book Description");
		String author = request.getParameter("Author");
		String location = request.getParameter("Location");
		String publisher = request.getParameter("Publisher Name");
		BigDecimal price = new BigDecimal(request.getParameter("Price"));
		String state = request.getParameter("State");
		// 封装实体
		newbook.setISBN(ISBN);
		newbook.setName(name);
		newbook.setDescription(description);
		newbook.setAuthors(author);
		// 添加一个author到author表中
		AuthorDAO authorDAO = new AuthorDAO();
		authorDAO.addAuthor(author, "default description");
		newbook.setLocation(location);
		// 添加一个publisher到publisher表中
		PublisherDAO publisherDAO = new PublisherDAO();
		publisherDAO.addPublisher(publisher, "default description");
		newbook.setPublisher(publisherDAO.searchPublisherByName(publisher));
		newbook.setState(state);
		// 添加book实例到book表中
		bookdao.addNewBook(ISBN, price, name, description, publisher, author);
		// 添加book实例到book_in_library表中
		List<Integer> bookIdList = bookdao.addBookInLib(ISBN, location, number);
		// 设置request属性
		request.setAttribute("bookIdList", bookIdList);
		request.setAttribute("newBookName", name);
		request.setAttribute("newAuthor", author);
		request.setAttribute("newISBN", ISBN);
		request.setAttribute("newLocation", location);
		request.setAttribute("newPublisher", publisher);
		// 转发到展示新书页面
		request.getRequestDispatcher("showNewBook.jsp").forward(request, response);
	}

}
