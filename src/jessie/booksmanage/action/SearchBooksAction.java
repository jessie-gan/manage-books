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

import jessie.booksmanage.service.BookService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/user")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class SearchBooksAction {
	private String bookName;
	private int page=1;
	int steps = 10;
	@Autowired
	BookService bookService;
	@Action(value = "showSearch", results = { @Result(name = "success", type = "json" )})
	public String showSearchResult(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> map = new HashMap<String,Object>();
		bookName = "%"+bookName+"%";
		map = bookService.getBookByBookName(bookName,page,steps);
		SendToJson.send(response, map);
		return "success";
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
