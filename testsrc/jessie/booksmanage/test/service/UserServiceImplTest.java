package jessie.booksmanage.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import jessie.booksmanage.dao.UserDAO;
import jessie.booksmanage.pojo.User;
import jessie.booksmanage.service.impl.UserServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends AbstractJUnit4SpringContextTests {
	@Mock
	private UserDAO userDao;
	@InjectMocks
	@Autowired
	private UserServiceImpl userService;
	String psw="test123";
	String stuNum="20121232";
	String info[]={"20121232","test","123456","女","软件1202","软件工程","18381668387","123456@qq.com"};;
	Map<String,Object> map = new HashMap<String,Object>();
	User user = new User();
	List<User>users = new ArrayList<User>();
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		for(int i=0;i<10;i++){
			User user = new User();
			user.setUsername("test");
			users.add(user);
		}
		user.setUsername("test");
		Mockito.when(userDao.selectByStudentNum(stuNum)).thenReturn(user);
		Mockito.when(userDao.updateUser(user)).thenReturn(1);
		Mockito.when(userDao.deleteUser(stuNum)).thenReturn(1);
		Mockito.when(userDao.selectAllUsers(Matchers.anyMap())).thenReturn(users);
		Mockito.when(userDao.selectAllUserAmounts()).thenReturn(5);
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUserByStudentNum() {
		User user = userService.getUserByStudentNum(stuNum);
		assertTrue(user!=null);
	}

//	@Test
//	public void testModifyUserPassword() {
//		userService.modifyUserPassword("111111");
//		System.out.println(user.getPassword());
//	}

	@Test
	public void testDeleteUser() {
		assertEquals(0, userService.deleteUser("stuNum"));
	}

	@Test
	public void testGetAllUsersByPage() {
		map = userService.getAllUsersByPage(1, 10);
		String msg = (String)map.get("msg");
		List<User> users = (List<User>)map.get("allUsers");
		int totalPage = (Integer)map.get("totalPage");
		int amounts = (Integer)map.get("amounts");
		assertEquals( "获取全部用户", msg);
		assertTrue(!users.isEmpty());
		assertEquals(1, totalPage);
		assertEquals(5, amounts);
	}

	@Test
	public void testCheck() {
		map = userService.check("20121232");
		String msg = (String)map.get("info");
		assertEquals("用户存在", msg);
	}
	@Test
	public void testCheck2() {
		Mockito.when(userDao.selectByStudentNum(stuNum)).thenReturn(null);
		map = userService.check("20121232");
		String msg = (String)map.get("info");
		assertEquals("用户不存在", msg);
	}
	@Test
	public void testRegisterUser() {
		Mockito.when(userDao.selectByStudentNum(stuNum)).thenReturn(null);
		map = userService.registerUser(stuNum, "test", "111111");
		String msg = (String)map.get("msg");
		assertEquals("注册成功", msg);
	}
	@Test
	public void testRegisterUser2() {
		map = userService.registerUser(stuNum, "test", "111111");
		String msg = (String)map.get("msg");
		assertEquals("用户名已存在", msg);
	}
//	@Test
//	public void testModifyUserDetails() {
//		map = userService.modifyUserDetails("test", "test", "test", "test", "test", "test", "test");
//		String msg =(String)map.get("msg");
//		assertEquals("修改成功", msg);
//	}

	@Test
	public void testModifyUserReadBean() {
		assertEquals(0, userService.modifyUserReadBean(stuNum, 5));
	}

}
