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
@Namespace("/manage")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminManageRecordsAction {
	private  int page;
	int steps = 10;
	@Autowired
	RecordService recordService;
	@Action(value = "showRecords",results = {@Result(name="success",type="json")})
	public String showAllCurrentRecords(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		map = recordService.getAllCurrentRecordsByPage(page, steps);
		SendToJson.send(response, map);
		return "success";
	}
	@Action(value = "showReRecords", results={@Result(name="success",type="json")})
	public String showRecentRecords(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		map = recordService.getAllRecentRecordsByPage(page, steps);
		SendToJson.send(response, map);
		return "success";
	}
	@Action(value = "showHistoryRecords", results = {@Result(name = "success", type = "json" )})
	public String showHistory(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> map = new HashMap<String, Object>();
		map = recordService.getAllRecordsByPage(page,steps);
		SendToJson.send(response, map);
		return "success";
		
	}
	@Action(value = "showShouldReturn", results={@Result(name="success",type="json")})
	public String showShouldReturn(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		map = recordService.getAllRecentShouldReturnByPage(page, steps);
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
