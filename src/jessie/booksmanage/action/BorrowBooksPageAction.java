package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jessie.booksmanage.service.BorrowService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@ParentPackage("jessie.booksmanage")
@Namespace("/user")
@Scope("request")
public class BorrowBooksPageAction {
	private String bookNum;
	@Autowired
	BorrowService borrowService;
	@Action(value = "borrowBook", results = { @Result(name = "success", type = "json" )})
	public String borrowBook(){
		Map<String,Object> map = new HashMap<String,Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String stuNum = (String)request.getSession().getAttribute("stuNum");	
		map = borrowService.borrowBook(bookNum, stuNum);
		SendToJson.send(response, map);
		return "success";
		
	}
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	
}
