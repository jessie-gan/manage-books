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

import jessie.booksmanage.service.DeleteService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/admin")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminDeleteUserAction {
	private String stuNum;
	@Autowired
	DeleteService deleteService;
	@Action(value="deleteUser",results={@Result(name="success",type="json")})
	public String deleteUser(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		if(deleteService.deleteUser(stuNum)){
			map.put("msg", "删除成功");
		}else{
			map.put("msg", "该用户尚未归还所有书籍，不能删除！");
		}
		SendToJson.send(response, map);
		return "success";
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
}
