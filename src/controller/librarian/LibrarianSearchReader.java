package controller.librarian;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReaderDAO;
import entity.Reader;

/**
 * 该类用于librarian查询reader的信息
 * 接收从librarianSearchReader.jsp传来的参数，通过doPost方法
 * @author zengyaoNPU
 */
public class LibrarianSearchReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReaderDAO readerDAO;
    public LibrarianSearchReader() {
        super();
        readerDAO=new ReaderDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取参数
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String state=request.getParameter("state");
		System.out.println("id="+id+",name="+name+",state="+state);
		if(state.equals("unknown")) {
			state=null;
		}
		if(name.equals("")) {
			name=null;
		}
		if(id.equals("")) {
			id=null;
		}
		//3个值，每个值都有可能为空，因此共有8种条件组合
		if(id!=null) { //id 不为空的情况下
			Reader reader=readerDAO.getReaderById(Integer.parseInt(id));
			if(reader!=null) {
				//下面4种组合为正确查询方式
				if(name!=null&&reader.getName().equals(name)&&state==null
						||state!=null&&reader.getState().equals(state)&&name==null
						||name==null&&state==null 
						||name!=null&&reader.getName().equals(name)&&state!=null&&reader.getState().equals(state)) {
					request.setAttribute("readerEntity", reader);
					System.out.println("--LibrarianSearchReader--,Condition"+1);
					RequestDispatcher dispatcher=request.getRequestDispatcher("librarianSearchReader.jsp");
					dispatcher.forward(request, response);
				}else {
					System.out.println("--LibrarianSearchReader--,Condition"+2);
					request.setAttribute("readerEntity", reader);
					RequestDispatcher dispatcher=request.getRequestDispatcher("errorSearchReader.jsp");
					dispatcher.forward(request, response);
				}
			}
		}else{//id为空
			if(name==null&&state!=null) {
				System.out.println("--LibrarianSearchReader--,Condition"+3);
				List<Reader> list=readerDAO.getReaderByState(state);
				request.setAttribute("readerList", list);
				RequestDispatcher dispatcher=request.getRequestDispatcher("librarianSearchReader.jsp");
				dispatcher.forward(request, response);
			}else if(name!=null&&state==null){
				System.out.println("--LibrarianSearchReader--,Condition"+4);
				List<Reader> list=readerDAO.getReaderByName(name);
				for(Reader i:list) {
					System.out.println(i.toString());
				}
				request.setAttribute("readerList", list);
				RequestDispatcher dispatcher=request.getRequestDispatcher("librarianSearchReader.jsp");
				dispatcher.forward(request, response);
			}else if(name!=null&&state!=null) {
				System.out.println("--LibrarianSearchReader--,Condition"+5);
				List<Reader> list=readerDAO.getReaderByName_State(name,state);
				request.setAttribute("readerList", list);
				RequestDispatcher dispatcher=request.getRequestDispatcher("librarianSearchReader.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

}
