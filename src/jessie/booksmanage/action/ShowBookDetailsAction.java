package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jessie.booksmanage.pojo.Book;
import jessie.booksmanage.service.BookService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/both")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class ShowBookDetailsAction {
	private String bookNum;
	@Autowired
	BookService bookService;
	@Action(value="/showDetails",results={@Result(name="success",type="json")})
	public String showBookDetails(){
		Map<String,Object> map = new HashMap<String,Object>();
		HttpServletResponse response = ServletActionContext.getResponse();
		Book book = bookService.getBookByBookNum(bookNum);
		map.put("msg", "书籍详情");
		map.put("bookDetail", book);
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
