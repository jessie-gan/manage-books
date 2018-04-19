package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.ServletActionContext; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;

import jessie.booksmanage.service.BookService;
import jessie.booksmanage.utils.SendToJson;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@Controller
@Namespace("/admin")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminAddBooksAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bookNum;
	private String bookName;
	private String author;
	private String bookPress;
	private String summary;
	//private String picPath;
	
	@Autowired
	BookService bookService;
	@Action(value = "addBook", results={@Result(name="success",type="json")})
	public String addBook(){
		Map<String,Object> map = new HashMap<String,Object>();
		int value = bookService.Addbook(bookNum, bookName, author, bookPress, summary);
		if(value == 0){
			map.put("msg", "图书编号已存在");
			SendToJson.send(ServletActionContext.getResponse(), map);
			return "error";
		}else{
			map.put("msg", "添加图书成功");
			SendToJson.send(ServletActionContext.getResponse(), map);
			return "success";
		}	
	}
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getBookPress() {
		return bookPress;
	}
	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
