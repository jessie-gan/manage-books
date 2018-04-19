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

import jessie.booksmanage.service.DeleteService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/admin")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminDeleteBooksAction {
	private String bookNum;
	@Autowired
	DeleteService deleteService;
	@Action(value="deleteBook",results={@Result(name="success",type="json")})
	public String deleteBook(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		if(deleteService.deleteBook(bookNum)){
			map.put("msg", "删除成功");
		}else{
			map.put("msg", "该书籍尚未归还，不能删除！！");
		}
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
