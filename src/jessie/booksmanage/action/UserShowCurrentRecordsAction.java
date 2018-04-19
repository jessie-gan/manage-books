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

import jessie.booksmanage.service.RecordService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/user")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class UserShowCurrentRecordsAction {
	private String stuNum;
	@Autowired
	RecordService recordService;
	@Action(value = "showCurrentRecords",results = {@Result(name="success",type="json")})
	public String showCurrentRecords(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		//System.out.println("1");
		map = recordService.getOnesCurrentRecords(stuNum);
		//System.out.println("2");
		SendToJson.send(response, map);
		//System.out.println("3");
		return "success";
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
}
