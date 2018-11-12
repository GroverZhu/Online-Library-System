package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Reader;

/**
 * Servlet implementation class BorrowCart
 */
public class BorrowCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BorrowCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int readerId=Integer.parseInt(request.getParameter("reade_id"));
		HttpSession session=request.getSession();
		Reader reader=(Reader)session.getAttribute("reader");
		System.out.println(reader.toString());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
