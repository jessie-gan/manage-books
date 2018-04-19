package jessie.booksmanage.test.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import jessie.booksmanage.pojo.BorrowRecord;
import jessie.booksmanage.pojo.User;
import jessie.booksmanage.service.BookService;
import jessie.booksmanage.service.ReadBeanRecordService;
import jessie.booksmanage.service.RecordService;
import jessie.booksmanage.service.UserService;
import jessie.booksmanage.service.impl.ReturnServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class ReturnServiceImplTest extends AbstractJUnit4SpringContextTests{
	@Mock
	BookService bookService;
	@Mock
	RecordService recordService;
	@Mock
	UserService userService;
	@Mock
	ReadBeanRecordService readBeanRecordService;
	@InjectMocks
	@Autowired
	ReturnServiceImpl returnService;
	String stuNum = "20121232";
	User user = new User();
	BorrowRecord record  = new BorrowRecord();
	Map<String, Object> map = new HashMap<String, Object>();
	@Before
	public void setUp() throws Exception {
		user.setReadBean(5);
		Mockito.when(userService.getUserByStudentNum(stuNum)).thenReturn(user);
		Mockito.when(recordService.getOneRecordById(Matchers.anyInt())).thenReturn(record);
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testReturnBook() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2016-05-25");
		String time = sdf.format(date);
		record.setShouldReturn_time(time);
		map = returnService.ReturnBook(1, "test_0001", stuNum);
		String msg = (String)map.get("msg");
		assertEquals("图书超期归还，扣除5R豆", msg);
	}
	@Test
	public void testReturnBook2() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2016-06-25");
		String time = sdf.format(date);
		record.setShouldReturn_time(time);
		map = returnService.ReturnBook(1, "test_0001", stuNum);
		String msg = (String)map.get("msg");
		assertEquals("已还书", msg);
	}
}
