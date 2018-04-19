package jessie.booksmanage.test.action;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
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

import jessie.booksmanage.action.AdminAddBooksAction;
import jessie.booksmanage.service.impl.BookServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class AdminAddBooksActionTest extends AbstractJUnit4SpringContextTests {
	@Mock
	private BookServiceImpl bookService;
	@Mock
	private ServletActionContext context;
	@Mock
	private HttpServletResponse response;
	@InjectMocks
	@Autowired
	private AdminAddBooksAction action;
	Map<String,Object> map = new HashMap<String,Object>();
	private String bookNum = "test_001";
	private String bookName = "软件测试";
	private String author = "jessie";
	private String bookPress = "清华大学出版社";
	private String summary = "书籍简介";
	@Before
	public void setUp() throws Exception {
		//MockHttpServletResponse response = null ;
		Mockito.when(bookService.Addbook(bookNum, bookName, author, bookPress, summary)).thenReturn(1);
		Mockito.when(context.getResponse()).thenReturn(response);
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAddBook() {
		String msg = action.addBook();
		assertEquals("success", msg);
	}

}