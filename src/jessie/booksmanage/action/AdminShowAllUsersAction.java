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

import jessie.booksmanage.service.UserService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/manage")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminShowAllUsersAction {
	private int page ;
	int steps=10;
	@Autowired
	UserService userService;
	@Action(value = "showUsers", results = { @Result(name = "success", type = "json" )})
	public String showUsers(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		map = userService.getAllUsersByPage(page,steps);
		SendToJson.send(response, map);
		return "success";
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
