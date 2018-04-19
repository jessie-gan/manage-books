package jessie.booksmanage.test.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

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

import jessie.booksmanage.service.BookService;
import jessie.booksmanage.service.RecordService;
import jessie.booksmanage.service.UserService;
import jessie.booksmanage.service.impl.DeleteServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class DeleteServiceImplTest extends AbstractJUnit4SpringContextTests {
	@Mock
	private BookService bookService;
	@Mock
	private RecordService recordService;
	@Mock
	private UserService userService;
	String bookNum = "test_0001";
	String stuNum = "20121232";
	Map<String, Object> map = new HashMap<String, Object>();
	@InjectMocks
	@Autowired
	private DeleteServiceImpl deleteService;
	@Before
	public void setUp() throws Exception {
		map.put("msg", "获取当前借阅记录成功");
		Mockito.when(bookService.deleteBook(bookNum)).thenReturn(1);
		Mockito.when(userService.deleteUser(stuNum)).thenReturn(1);
		Mockito.when(recordService.getCurrenyRecordsByBookNum(bookNum)).thenReturn(true);
		Mockito.when(recordService.getOnesCurrentRecords(stuNum)).thenReturn(map);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteBook() {
		assertTrue(deleteService.deleteBook(bookNum));
	}
	@Test
	public void testDeleteBook2() {
		Mockito.when(recordService.getCurrenyRecordsByBookNum(bookNum)).thenReturn(false);
		assertTrue(!deleteService.deleteBook(bookNum));
	}                      
	@Test
	public void testDeleteUser() {
		assertTrue(!deleteService.deleteUser(stuNum));
	}
	@Test
	public void testDeleteUser2() {
		map.put("msg", "获取当前不借阅记录成功");
		assertTrue(deleteService.deleteUser(stuNum));
	}
}
