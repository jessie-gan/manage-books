package jessie.booksmanage.test.action;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import jessie.booksmanage.action.AdminEditBookAction;
import jessie.booksmanage.action.AdminLoginAction;
import jessie.booksmanage.service.impl.AdminUserServiceImpl;

public class AdminLoginActionTest extends AbstractJUnit4SpringContextTests {
	@Mock
	private AdminUserServiceImpl adminUserService;
	@Mock
	private ServletActionContext context;
	@Mock
	private HttpServletResponse response;
	@InjectMocks
	@Autowired
	private AdminLoginAction action;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		String msg = action.login();
		assertEquals("success", msg);
	}

}
