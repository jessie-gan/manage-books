package jessie.booksmanage.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import jessie.booksmanage.pojo.BorrowRecord;
import jessie.booksmanage.pojo.User;
import jessie.booksmanage.service.BookService;
import jessie.booksmanage.service.ReadBeanRecordService;
import jessie.booksmanage.service.RecordService;
import jessie.booksmanage.service.UserService;
import jessie.booksmanage.service.impl.BorrowServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class BorrowServiceImplTest extends AbstractJUnit4SpringContextTests {
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
	private BorrowServiceImpl borrowService;
	Map<String, Object> tempMap = new HashMap<String, Object>();
	String bookNum="test_001";
	String stuNum="20121232";
	List<BorrowRecord> records = new ArrayList<BorrowRecord>();
	@Before
	public void setUp() throws Exception {
		for(int i=0;i<10;i++){
			BorrowRecord record = new BorrowRecord();
			records.add(record);
		}
		tempMap.put("currentRecords", records);
		User user = new User();
		user.setReadBean(5);
		Mockito.when(recordService.getOnesCurrentRecords(stuNum)).thenReturn(tempMap);
		Mockito.when(userService.getUserByStudentNum(stuNum)).thenReturn(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBorrowBook() {
		Map<String,Object> map = new HashMap<String, Object>();
		map = borrowService.borrowBook(bookNum, stuNum);
		String msg = (String)map.get("msg");
		assertEquals("借书成功，获得5个R豆", msg);
	}

}
