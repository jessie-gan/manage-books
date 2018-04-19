package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;

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
@Namespace("/user")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class UserRegisterAction {
	private String userName;
	private String password;
	private String stuNum;

	@Autowired
	UserService userService;
	@Action(value = "register", results = { @Result(name = "success", type = "json" )})
	public String register(){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> tempmap = new HashMap<String, Object>();
		tempmap = userService.registerUser(stuNum, userName, password);
		SendToJson.send(ServletActionContext.getResponse(), map);
		return "success";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	
}
