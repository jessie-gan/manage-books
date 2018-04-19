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

import jessie.booksmanage.pojo.AdminUser;
import jessie.booksmanage.service.AdminUserService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/admin")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminLoginAction {
	private String username;
	private String psw;
	@Autowired
	AdminUserService adminUserService;

	@Action(value = "login", results = { @Result(name = "success", type = "json") })
	public String login() {
		Map<String, Object> map = new HashMap<String, Object>();
		AdminUser adminUser = adminUserService.getUserByName(username);
		if (adminUser != null) {
			if (adminUser.getAd_password().equals(psw)) {
				map.put("msg", "登录成功");
				map.put("username", username);
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("adminName", username);
				SendToJson.send(ServletActionContext.getResponse(), map);
				return "success";
			} else {
				map.put("msg", "密码错误");
				SendToJson.send(ServletActionContext.getResponse(), map);
				return "error";
			}

		} else {
			map.put("msg", "用户不存在");
			SendToJson.send(ServletActionContext.getResponse(), map);
			return "error";
		}
	}

	@Action(value = "getAdminInfo", results = { @Result(name = "success", type = "json") })
	public String getUserInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userName = (String) session.getAttribute("adminName");
		if (userName == null) {
			return "success";
		} else {
			map.put("adminName", userName);
			map.put("msg", "欢迎进入！");
			SendToJson.send(ServletActionContext.getResponse(), map);
			return "success";
		}		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public AdminUserService getAdminUserService() {
		return adminUserService;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

}
