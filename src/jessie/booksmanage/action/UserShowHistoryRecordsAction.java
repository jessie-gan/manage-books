package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jessie.booksmanage.service.RecordService;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/user")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class UserShowHistoryRecordsAction {
	private int page=1;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	int steps=10;
	@Autowired
	RecordService recordService;
	@Action(value = "showHisRecord", results = {@Result(name = "success", type = "json" )})
	public String showHistory(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> map = new HashMap<String, Object>();
		map = recordService.getOnesHistoryRecordsByPage(page, steps);
		SendToJson.send(response, map);
		return "success";
		
	}
}
