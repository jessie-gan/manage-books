package jessie.booksmanage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import jessie.booksmanage.service.BookService;
import jessie.booksmanage.service.RecordService;
import jessie.booksmanage.service.UserService;
import jessie.booksmanage.utils.SendToJson;
@Controller
@ParentPackage("jessie.booksmanage")
@Namespace("/personalCenter")
@Scope("request")
public class PersonalCenterPageAction {
	private String setPsw;
	private String oldPsw;
	private String setUserName;
	private String setRealName;
	private String setSex;
	private String setEmail;
	private String setMajor;
	private String setTel;
	private String setGrade;
	@Autowired
	UserService userService;
	@Autowired
	RecordService recordService;
	@Autowired
	BookService bookService;
	@Action(value = "modifyPsw", results = { @Result(name = "success", type = "json" )})
	public String modifyPsw(){
		Map<String, Object> map = new HashMap<String,Object>();
		if(setPsw != ""){
			setPsw = setPsw.trim();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String stuNum = (String)session.getAttribute("stuNum");
		User user = userService.getUserByStudentNum(stuNum);
		if(user.getPassword().equals(oldPsw)){
			userService.modifyUserPassword(setPsw);
			map.put("msg", "修改密码成功");
			SendToJson.send(ServletActionContext.getResponse(), map);
			return "success";
		}else{
			map.put("msg", "原密码错误");
			SendToJson.send(ServletActionContext.getResponse(), map);
			return "error";
		}	
	}
	@Action(value = "modifyDetails", results = { @Result(name = "success", type = "json" )})
	public String modifyDetails(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> map = new HashMap<String,Object>();
		Map<String, Object> tmap = new HashMap<String,Object>();
		//先判断填入的信息是否符合要求
		tmap = userService.modifyUserDetails(setUserName, setRealName, setSex, setEmail,setTel, setGrade, setMajor);
		//User user = (User)tmap.get("user");
		map.put("msg", "修改成功");
		SendToJson.send(response, map);
		return "success";
	}
	public String getSetTel() {
		return setTel;
	}
	public void setSetTel(String setTel) {
		this.setTel = setTel;
	}
	public String getSetPsw() {
		return setPsw;
	}
	public void setSetPsw(String setPsw) {
		this.setPsw = setPsw;
	}
	public String getSetUserName() {
		return setUserName;
	}
	public void setSetUserName(String setUserName) {
		this.setUserName = setUserName;
	}
	public String getSetRealName() {
		return setRealName;
	}
	public void setSetRealName(String setRealName) {
		this.setRealName = setRealName;
	}
	public String getSetSex() {
		return setSex;
	}
	public void setSetSex(String setSex) {
		this.setSex = setSex;
	}
	public String getSetEmail() {
		return setEmail;
	}
	public void setSetEmail(String setEmail) {
		this.setEmail = setEmail;
	}
	public String getSetMajor() {
		return setMajor;
	}
	public void setSetMajor(String setMajor) {
		this.setMajor = setMajor;
	}
	public String getSetGrade() {
		return setGrade;
	}
	public void setSetGrade(String setGrade) {
		this.setGrade = setGrade;
	}
	public String getOldPsw() {
		return oldPsw;
	}
	public void setOldPsw(String oldPsw) {
		this.oldPsw = oldPsw;
	}
}
