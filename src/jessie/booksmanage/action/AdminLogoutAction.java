package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/admin")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminLogoutAction {

	@Action(value = "logout", results = { @Result(name = "success", type = "json") })
	public String Logout() {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("adminName");
		map.put("msg", "已退出登录");
		SendToJson.send(ServletActionContext.getResponse(), map);
		return "success";
	}

}
