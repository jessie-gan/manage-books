package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jessie.booksmanage.pojo.User;
import jessie.booksmanage.service.UserService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/user")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class UserLoginAction {
	private String stuNum;
	private String psw;
	@Autowired
	UserService userService;

	@Action(value = "login", results = { @Result(name = "success", type = "json") })
	public String Login() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempmap = new HashMap<String, Object>();
		tempmap = this.userService.check(stuNum);
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (tempmap.get("info").equals("用户不存在")) {
			return "error";
		} else {
			User user = (User)tempmap.get("user");
			if(user.getPassword().equals(psw)){
				session.setAttribute("username", user.getUsername());
				session.setAttribute("stuNum", user.getStudent_num());
				map.put("info","登录成功");
				SendToJson.send(ServletActionContext.getResponse(), map);
				return "success";
			}else{
				map.put("info", "密码错误");
				SendToJson.send(ServletActionContext.getResponse(), map);
				return "error";
			}
			
		}
	}
	@Action(value = "getUserInfo", results = { @Result(name = "success", type = "json") })
	public String getUserInfo(){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String stuNum = (String)session.getAttribute("stuNum");
		String userName = (String)session.getAttribute("username");
		map.put("stuNum", stuNum);
		map.put("username", userName);
		map.put("info","进入成功");
		SendToJson.send(ServletActionContext.getResponse(), map);
		return "success";
	}
	@Action(value = "getUserDetailsInfo", results = { @Result(name = "success", type = "json") })
	public String getUserDetailsInfo(){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String stuNum = (String)session.getAttribute("stuNum");
		String userName = (String)session.getAttribute("username");
		User user = userService.getUserByStudentNum(stuNum);
		map.put("stuNum", stuNum);
		map.put("username", userName);
		map.put("realName", user.getReal_name());
		map.put("sex", user.getSex());
		map.put("email", user.getEmail());
		map.put("tel", user.getTel());
		map.put("gradeClass", user.getGradeClass());
		map.put("major", user.getMajor());
		SendToJson.send(ServletActionContext.getResponse(), map);
		return "success";
	}
	@Action(value = "getUserBean", results = { @Result(name = "success", type = "json") })
	public String getUserBean(){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String stuNum = (String)session.getAttribute("stuNum");
		User user = userService.getUserByStudentNum(stuNum);
		map.put("ReadBean", user.getReadBean());
		SendToJson.send(ServletActionContext.getResponse(), map);
		return "success";
	}
	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
