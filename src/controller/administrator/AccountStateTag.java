package controller.administrator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class AccountStateTag extends TagSupport {
	/**
	 * 将librarian或reader的账号状态做成Tag标签
	 * 
	 * @author GroverZhu
	 */
	public int doStartTag() throws JspException {

		try {
			JspWriter out = pageContext.getOut();
			String outPrint = "";
			String[] states = { "unlock", "blockade" };
			outPrint += "<select id='State' name='state' size='1'>";
			for (String state : states) {
				outPrint += "<option>";
				outPrint += state;
				outPrint += "</option>";
			}
			outPrint += "</select>";
			out.print(outPrint);
		} catch (java.io.IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}

}
