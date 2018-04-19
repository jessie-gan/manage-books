package jessie.booksmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jessie.booksmanage.pojo.User;
@Repository
public interface UserDAO {
	public User selectByStudentNum(String student_num);
	public List<User> selectAllUsers(Map<String,Object> map);
	public int selectAllUserAmounts();
	
	public int insertUser(User user);
	public int insertUserDetails(String stuNum);
	
	public int updateUserDetails(User user);
	
	public int updateUserReanBean(User user);
	public int updateUser(User user);

	
	public int deleteUser(String student_num);
}
