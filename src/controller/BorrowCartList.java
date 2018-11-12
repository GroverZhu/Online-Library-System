package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseUtil;

/**
 * 查看用户借阅车列表，只有librarian才访问
 * 借阅车按照提交时间顺序排列
 */
public class BorrowCartList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BorrowCartList() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			conn=DatabaseUtil.getInstance().getConnection();
			st=conn.createStatement();
			String sql="select * from borrow_cart ";
			/*
			 * 未完成
			 */
			rs=st.executeQuery(sql);
			
		}catch(Exception e) {
			System.out.println("--BorrowCartList--,--doGet()--,suffers exception");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
