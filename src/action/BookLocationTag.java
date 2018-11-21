package action;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

public class BookLocationTag implements SimpleTag {

	private JspContext jspContext;
	/**
	 * 为添加书本中的location添加下拉框
	 */
	public void doTag() throws JspException, IOException {
		try{
		    JspWriter out = jspContext.getOut();
		    String outPrint = "";
		    String[] color = {"四层-科技图书阅览区","四层-科技图书典藏区","三层-社科类图书阅览区","三层-社科类图书典藏区","二层-杂志期刊"};
		    outPrint += "<select name='Location' size='1' class=\"form-control\"> ";
		    for(int i = 0; i < color.length; i++){
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
		this.jspContext=arg0;
	}

	public void setParent(JspTag arg0) {

	}
}
