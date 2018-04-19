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
@Namespace("/manage")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminShowMaxNumAction {
	@Autowired
	BookService bookService;
	@Action(value = "showMaxNum", results = { @Result(name = "success", type = "json" )})
	//需要查询数据库中最大编号值
	public String showMaxNum(){
		HttpServletResponse response = ServletActionContext.getResponse();
		String maxBookNum = this.bookService.getMaxBookNum();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("msg", "最大编号");
		map.put("maxBookNum", maxBookNum);
		SendToJson.send(response, map);
		return "success";
	}
}
