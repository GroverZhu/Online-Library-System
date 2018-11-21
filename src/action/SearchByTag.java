package action;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

public class SearchByTag implements SimpleTag {

	private JspContext jspContext;

	/**
	 * 为搜书功能中添加下拉框
	 */
	public void doTag() throws JspException, IOException {
		try {
			JspWriter out = jspContext.getOut();
			String outPrint = "";
			String[] color = { "Book Name", "Author", "Publisher", "ISBN" };
			outPrint += "<select id=\"input\" name=\"searchBy\"> ";
			for (int i = 0; i < color.length; i++) {
				outPrint += "<option>";
				outPrint += color[i];
				outPrint += "</option>";
			}
			outPrint += "</select>";
			out.print(outPrint);
		} catch (java.io.IOException e) {
			throw new JspTagException(e.getMessage());
		}
	}

	public JspTag getParent() {
		return null;
	}

	public void setJspBody(JspFragment arg0) {

	}

	public void setJspContext(JspContext arg0) {
		this.jspContext = arg0;
	}

	public void setParent(JspTag arg0) {

	}

}