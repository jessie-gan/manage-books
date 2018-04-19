package jessie.booksmanage.service;

import java.util.Map;

import jessie.booksmanage.pojo.User;

public interface UserService {
	User getUserByStudentNum(String studentNum);
	Map<String,Object> check(String studentNum);
	int modifyUserPassword(String psw);
	Map<String, Object> modifyUserDetails(String userName,String realName,
			String sex,String email,String tel,String gradeClass,String major );
	int modifyUserReadBean(String stuNum,int bean);
	Map<String, Object> registerUser(String stuNum,String userName,String psw);
	int deleteUser(String studentNum);
	Map<String, Object> getAllUsersByPage(int page,int steps);
}
