package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jessie.booksmanage.service.ReturnService;
import jessie.booksmanage.utils.SendToJson;
@Controller
@Namespace("/manage")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminReturnBookAction {


	private String bookNum;
	private String id;
	private String stuNum;
	@Autowired
	ReturnService returnService;
	@Action(value = "returnBook", results = { @Result(name = "success", type = "json" )})
	public String returnBook(){
		Map<String,Object> map = new HashMap<String,Object>();
		HttpServletResponse response = ServletActionContext.getResponse();
		Integer Id = Integer.parseInt(id);
		map = returnService.ReturnBook(Id,bookNum, stuNum);
		SendToJson.send(response, map);
		return "success";
	}
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public ReturnService getReturnService() {
		return returnService;
	}
	public void setReturnService(ReturnService returnService) {
		this.returnService = returnService;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
}
