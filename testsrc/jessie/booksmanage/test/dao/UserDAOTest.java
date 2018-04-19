package jessie.booksmanage.test.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jessie.booksmanage.dao.UserDAO;
import jessie.booksmanage.pojo.User;
import jessie.booksmanage.test.base.DBInAndOut;
import jessie.booksmanage.test.base.DBUtil;
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring-mybatis.xml") 
@ContextConfiguration(locations={"file:resources/spring-mybatis.xml" } ) 
@WebAppConfiguration 
public class UserDAOTest {
	@Autowired
	UserDAO userDao;
	private static String TEST_DIR = "DBData/"; 
	File file = new File(TEST_DIR + "user.xml");
	@Before
	public void setUp() throws Exception {
		Connection connection = DBUtil.getConnection(); 
		 String[] tableNames = new String[] { "user","user_details" }; 
		 DBInAndOut.exportTables(connection, tableNames, file);
	}

	@After
	public void tearDown() throws Exception {
		Connection connection = DBUtil.getConnection(); 
		DBInAndOut.importData(file, connection);
		 DBUtil.close(connection);
	}

	@Test
	public void testSelectByStudentNum() {
		String stuName = "20121232";
		User user = userDao.selectByStudentNum(stuName);
		assertTrue(user!=null);
	}

	@Test
	public void testSelectAllUsers() {
		Map<String,Object> map = new HashMap<String, Object>();
		int startNum = 0;
		int path = 100;
		map.put("startNum", startNum);
		map.put("steps", path);
		int amounts = 21;
		List<User>allUsers = this.userDao.selectAllUsers(map);
		assertEquals(amounts, allUsers.size());
	}

	@Test
	public void testSelectAllUserAmounts() {
		int amounts = 21;
		assertEquals(amounts, this.userDao.selectAllUserAmounts());
	}

	@Test
	public void testInsertUser() {
		User user=new User("11111111","test","123456");
		userDao.insertUser(user);
		userDao.insertUserDetails("11111111");
		User users = userDao.selectByStudentNum("11111111");
		assertTrue(users!=null);
	}

	@Test
	public void testInsertUserDetails() {
		User user=new User("11111111","test","123456");
		userDao.insertUser(user);
		userDao.insertUserDetails("11111111");
		User users = userDao.selectByStudentNum("11111111");
		assertTrue(users!=null);
	}

	@Test
	public void testUpdateUserDetails() {
		User user=new User("11111111","test","123456");
		userDao.insertUser(user);
		userDao.insertUserDetails("11111111");
		String stuNum = "11111111";
		String[] info = {"name","test3","女","18381668387","软件1203班","软件工程","123456789@qq.com"};
		User user2 = userDao.selectByStudentNum(stuNum);
		user2.setUsername(info[0]);
		user2.setReal_name(info[1]);
		user2.setSex(info[2]);
		user2.setTel(info[3]);
		user2.setGradeClass(info[4]);
		user2.setMajor(info[5]);
		user2.setEmail(info[6]);
		userDao.updateUserDetails(user2);
		user = userDao.selectByStudentNum(stuNum);
		assertEquals(user.getUsername(), info[0]);
		assertEquals(user.getReal_name(), info[1]);
		assertEquals(user.getSex(), info[2]);
		assertEquals(user.getTel(), info[3]);
		assertEquals(user.getGradeClass(), info[4]);
		assertEquals(user.getMajor(), info[5]);
		assertEquals(user.getEmail(), info[6]);
	}

	@Test
	public void testUpdateUserReanBean() {
		User user=new User("11111111","test","123456");
		userDao.insertUser(user);
		userDao.insertUserDetails(user.getStudent_num());
		user.setReadBean(100);
		userDao.updateUserReanBean(user);
		User user2 = userDao.selectByStudentNum(user.getStudent_num());
		assertEquals(user.getReadBean(), user2.getReadBean());
	}

	@Test
	public void testUpdateUser() {
		User user=new User("11111111","test","123456");
		userDao.insertUser(user);
		userDao.insertUserDetails(user.getStudent_num());
		String newPsw = "123123";
		user = userDao.selectByStudentNum(user.getStudent_num());
		user.setPassword(newPsw);
		userDao.updateUser(user);
		
		User user2 = userDao.selectByStudentNum(user.getStudent_num());
		assertEquals(newPsw, user2.getPassword());
	}

	@Test
	public void testDeleteUser() {
		User user=new User("11111111","test","123456");
		userDao.insertUser(user);
		userDao.insertUserDetails(user.getStudent_num());
		userDao.deleteUser(user.getStudent_num());
		user = userDao.selectByStudentNum(user.getStudent_num());
		assertTrue(user==null);
	}

}
