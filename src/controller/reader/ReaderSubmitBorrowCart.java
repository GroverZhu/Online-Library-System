package controller.reader;
/**
 * @author LiuZhuocheng
 * 用来展示readerborrowcart
 */


import dao.BorrowCartDAO;
import dao.BorrowItemDAO;
import entity.BorrowItem;
import entity.Cart;
import entity.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReaderSubmitBorrowCart extends HttpServlet {
    public ReaderSubmitBorrowCart() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");
        int bookid = (int)session.getAttribute("bookid");
        Reader reader = (Reader)session.getAttribute("ReaderEntity");
        int userid = Integer.MAX_VALUE;
        if (reader != null) {
            userid = reader.getId();
        }
        BorrowCartDAO borrowCartDAO = new BorrowCartDAO();
        borrowCartDAO.updateBorrowCart(bookid,userid);
//        List<Cart>carts = borrowCartDAO.getBorrowCartByReaderId(userid);
//        for(Cart cart : carts){
//            if(cart.getBookId()==bookid && cart.getReaderId()==userid && )
//        }
        //写时还没有更改状态的方法。无法进行判断
        response.sendRedirect("readerSuccessSubmitBorrowCart.jsp");
    }

}
