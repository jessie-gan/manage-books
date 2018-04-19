package jessie.booksmanage.test.action;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import jessie.booksmanage.action.AdminDeleteBooksAction;
import jessie.booksmanage.service.impl.BookServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class AdminDeleteBooksActionTest extends AbstractJUnit4SpringContextTests {
	@Mock
	private BookServiceImpl bookService;
	@Mock
	private ServletActionContext context;
	@Mock
	private HttpServletResponse response;
	@InjectMocks
	@Autowired
	private AdminDeleteBooksAction action;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteBook() {
		String msg = action.deleteBook();
		assertEquals("success", msg);
	}

}
