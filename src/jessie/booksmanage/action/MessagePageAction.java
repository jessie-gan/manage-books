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

/**
 * @author jessie
 *
 */
@Controller
@ParentPackage("jessie.booksmanage")
@Namespace("/message")
@Scope("request")
/**
 * @author 消息模块页
 *
 */
public class MessagePageAction {
	private int page;
	int steps = 10;
	@Autowired
	RecordService recordService;
	@Action(value = "showReRecord", results={@Result(name="success",type="json")})
	public String showRecentRecords(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		map = recordService.getOnesRecentRecords();
		SendToJson.send(response, map);
		return "success";
	}
	@Action(value = "showWillOverTimeRecord", results={@Result(name="success",type="json")})
	public String showWillOverTimeRecords(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		map = recordService.getOnesWillOverTime();
		SendToJson.send(response, map);
		return "success";
	}
	@Action(value = "showOverTimeRecord", results={@Result(name="success",type="json")})
	public String showOverTimeRecords(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		map = recordService.getOnesOvertimeByPage(recordService.getOnesOverTime(),page, steps);
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
