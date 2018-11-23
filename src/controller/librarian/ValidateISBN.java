package controller.librarian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

/**
 * 该servlet用于验证ISBN的正确性，并从网上获取图书信息
 * 
 * @author zengyaoNPU
 * @date 2018-11-17 21:43
 */
public class ValidateISBN extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ValidateISBN() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String isbn = request.getParameter("ISBN");
		String uri = "https://api.douban.com/v2/book/isbn/" + isbn;
		String jsonData = "";
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		try {
			jsonData = getJsonData(uri, "");// 自定义函数
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonData != "") {
			System.out.println(jsonData);
			Map maps = (Map) JSON.parse(jsonData);
			String author = maps.get("author").toString();
			author = author.replace("[", "");
			author = author.replace("]", "");
			author = author.replace("\"", "");

			System.out.println("定价: " + maps.get("price"));
			System.out.println("封面: " + maps.get("image"));
			System.out.println("作者：  " + author);
			System.out.println("出版日期: " + maps.get("pubdate"));
			System.out.println("出版社: " + maps.get("publisher"));
			System.out.println("书名: " + maps.get("title"));
			System.out.println("摘要: " + maps.get("summary"));

			session.setAttribute("ISBN__", isbn);
			session.setAttribute("BookName__", maps.get("title"));
			session.setAttribute("BookDes__", maps.get("summary"));
			session.setAttribute("PubTime__", maps.get("pubdate"));
			session.setAttribute("Price__", maps.get("price"));
			session.setAttribute("Author__", author);
			session.setAttribute("PubName__", maps.get("publisher"));

			out.print(
					"<script language='javascript'>alert('The ISBN is valid!');window.location.href='librarianAddBook.jsp';</script>");

		} else {
			out.print(
					"<script language='javascript'>alert('Invailed ISBN!');window.location.href='librarianAddBook.jsp';</script>");
		}
	}

	private String getJsonData(String url, String param) throws Exception {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
