package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SessionSimpleTag extends SimpleTagSupport {

	private HttpServletRequest request;
	private HttpServletResponse response;

	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		out.println(
				"<c:if test=\"${empty sessionScope.AdministratorEntity}\" > <jsp:forward page=\"homepage.jsp\"/> </c:if>");

	}
}
