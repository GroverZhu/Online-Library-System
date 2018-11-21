package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class URLFilter
 * 
 * @author Huyuxi
 * @date 2018-11-19
 */
public class URLFilter implements Filter {

	private FilterConfig config = null;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.config = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 用于判断是否满足homepage.jsp发送的Get请求，
		boolean flag = false;
		// 用于判断是否符合资源路径，满足指定的jsp, .png, .css文件
		boolean resFlag = false;

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// 获取重定向路径(当前application的路径+init-param中的初始化的值)
		String redirectPath = httpRequest.getContextPath() + config.getInitParameter("redirectPath");
		String disableFilter = config.getInitParameter("disableFilter");
		String path = httpRequest.getRequestURI();

		// System.out.println("-*-\n" + redirectPath + "\n" + disableFilter +
		// path + "\n-*-");
		// 判断初始化参数是否让该Filter生效 "Y"为无效， "N"为有效
		if (disableFilter.toUpperCase().equals("Y")) {
			chain.doFilter(request, response);
		}
		// 当过滤器有效时，判断是不是通过get方法
		// 如果是通过homepage.jsp的操作及后续的操作是可以访问到get的方法
		// 通过header的referer的头来判断是否是通过homepage.jsp的链接访问到的
		if (disableFilter.toUpperCase().equals("N")) {
			if (httpRequest.getMethod().toUpperCase().equals("GET")) {
				if (httpRequest.getHeader("referer") != null) {
					chain.doFilter(request, response);
				} else if (httpRequest.getRequestURI().equals(redirectPath)) {
					chain.doFilter(request, response);
				} else {
					httpResponse.sendRedirect(redirectPath);
				}
			} else {
				chain.doFilter(request, response);
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}
