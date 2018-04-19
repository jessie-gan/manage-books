package jessie.booksmanage.test.service;

import static org.junit.Assert.*;

import org.apache.tools.ant.taskdefs.Length.When;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import jessie.booksmanage.dao.AdminUserDAO;
import jessie.booksmanage.pojo.AdminUser;
import jessie.booksmanage.service.impl.AdminUserServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class AdminUserServiceImplTest extends AbstractJUnit4SpringContextTests {
	@Mock
	private AdminUserDAO adminUserDao;
	@InjectMocks
	@Autowired
	private AdminUserServiceImpl adminService;
	
	private AdminUser user;
	@Before
	public void setUp() throws Exception {
		user = new AdminUser();
		user.setAd_username("admin");
		user.setAd_password("123");
		String userName="admin";
		Mockito.when(adminUserDao.selectByAdminUsername(userName)).thenReturn(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUserByName() {
		String userName="admin";
		AdminUser user = adminService.getUserByName(userName);
		assertEquals("admin", user.getAd_username());
		assertEquals("123", user.getAd_password());
	}

}
