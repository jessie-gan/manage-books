package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import jessie.booksmanage.service.ReadBeanRecordService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/user")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class UserShowReadBeanRecordAction {
	private int page;
	int steps = 10;
	@Autowired
	ReadBeanRecordService beanRecordService;
	@Action(value = "readBeanRecord", results = { @Result(name = "success", type = "json" )})
	public String showReadBeanRecords(){
		Map<String,Object> map = new HashMap<String,Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String stuNum = (String)request.getSession().getAttribute("stuNum");
		map = beanRecordService.getBeanRecordByStuNum(stuNum,page,steps);
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
