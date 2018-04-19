package jessie.booksmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import jessie.booksmanage.dao.UserDAO;
import jessie.booksmanage.pojo.User;
import jessie.booksmanage.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDAO userDao;
	public User getUserByStudentNum(String studentNum) {
		
		return this.userDao.selectByStudentNum(studentNum);
	}

	public int modifyUserPassword(String psw) {
		String stuNum = (String)ServletActionContext.getRequest().getSession().getAttribute("stuNum");
		User user = userDao.selectByStudentNum(stuNum);
		user.setPassword(psw);
		this.userDao.updateUser(user);
		return 0;
	}


	public int deleteUser(String studentNum) {
		this.userDao.deleteUser(studentNum);
		return 0;
	}

	@Override
	public Map<String, Object> getAllUsersByPage(int page,int steps) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> parameterMap = new HashMap<String, Object>();
		int startNum = 0;
		startNum = (page-1)*steps;
		parameterMap.put("startNum", startNum);
		parameterMap.put("steps", steps);
		List<User> users = this.userDao.selectAllUsers(parameterMap);
		int amounts = this.userDao.selectAllUserAmounts();
		int pageNum = 1;
		if(users.isEmpty()){
			map.put("msg", "当前无用户");
		}else{
			if (amounts % steps == 0) {
				pageNum = amounts / steps;
			} else {
				pageNum = (amounts / steps) + 1;
			}
			map.put("msg", "获取全部用户");
			map.put("allUsers", users);
			map.put("totalPage", pageNum);
			map.put("amounts", amounts);
		}
		return map;
	}

	@Override
	public Map<String, Object> check(String studentNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		User user = this.getUserByStudentNum(studentNum);
		if(user==null){
			map.put("info", "用户不存在");
		}else{
			map.put("info", "用户存在");
			map.put("user", user);
		}
		return map;
	}

	@Override
	public Map<String, Object> registerUser(String stuNum, String userName, String psw) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		User user = this.getUserByStudentNum(stuNum);
		if(user != null){
			map.put("msg", "用户名已存在");
		}else{
			user = new User();
			user.setStudent_num(stuNum);
			user.setUsername(userName);
			user.setPassword(psw);
			user.setReadBean(0);
			this.userDao.insertUser(user);
			this.userDao.insertUserDetails(stuNum);
			map.put("user", user);
			map.put("msg", "注册成功");
		}
		return map;
	}

	@Override
	public Map<String, Object> modifyUserDetails(String userName, String realName, String sex, String email,String setTel,
			String gradeClass, String major) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String,Object>();
		String stuNum = (String)session.getAttribute("stuNum");
		User user = userDao.selectByStudentNum(stuNum);
		user.setUsername(userName);
		user.setReal_name(realName);
		user.setSex(sex);
		user.setEmail(email);
		user.setTel(setTel);
		user.setGradeClass(gradeClass);
		user.setMajor(major);
		this.userDao.updateUserDetails(user);
		map.put("msg", "修改成功");
		map.put("user", user);
		return map;
	}

	@Override
	public int modifyUserReadBean(String stuNum, int bean) {
		Map<String, Object> map = new HashMap<String,Object>();
		User user = userDao.selectByStudentNum(stuNum);
		user.setReadBean(bean);
		this.userDao.updateUserReanBean(user);
		return 0;
	}

}
