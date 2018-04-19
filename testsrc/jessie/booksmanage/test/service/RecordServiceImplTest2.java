package jessie.booksmanage.test.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import jessie.booksmanage.dao.BorrowListDAO;
import jessie.booksmanage.pojo.BorrowRecord;
import jessie.booksmanage.service.impl.RecordServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class RecordServiceImplTest2 extends AbstractJUnit4SpringContextTests{
	@Mock
	BorrowListDAO borrowDao;
//	@Mock
//	ServletActionContext context = new MockUp<ServletActionContext>();
	@InjectMocks
	@Autowired
	RecordServiceImpl recordService;
	Map<String, Object> map = new HashMap<String, Object>();
	List<BorrowRecord> records = new ArrayList<BorrowRecord>();
	BorrowRecord record = new BorrowRecord();
	String stuNum = "20121232";
	@Before
	public void setUp() throws Exception {
		new MockHttpServletRequest().getSession().setAttribute("stuNum", "20121232");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 10; i++) {
			BorrowRecord record = new BorrowRecord();
			Date date = sdf.parse("2016-07-01");
			String time = sdf.format(date);
			Date date2 = sdf.parse("2016-06-01");
			String time2 = sdf.format(date2);
			record.setShouldReturn_time(time);
			record.setBorrow_time(time2);
			records.add(record);
		}

	//	Mockito.when(context.getRequest()).thenReturn(new MockHttpServletRequest());
		Mockito.when(borrowDao.selectOnesCurrentRecord(stuNum)).thenReturn(records);
		Mockito.when(borrowDao.selectOnesHistoryRecord(stuNum)).thenReturn(records);
		Mockito.when(borrowDao.selectOnesHistoryRecordByPage(Matchers.anyMap())).thenReturn(records);
		Mockito.when(borrowDao.selectOneRecordById(Matchers.anyInt())).thenReturn(record);
		Mockito.when(borrowDao.selectRecordId(record)).thenReturn(20);
		Mockito.when(borrowDao.selectCurrentRecordByBookNum(Matchers.anyString())).thenReturn(records);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void testGetOnesCurrentRecords() {
		map = recordService.getOnesHistoryRecords();
		String msg = (String) map.get("msg");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("currentRecords");
		assertEquals("获取当前借阅记录成功", msg);
		assertEquals(records, records2);
	}

	@Test
	public void testGetOnesHistoryRecords() {
		map = recordService.getOnesHistoryRecords();
		String msg = (String) map.get("msg");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("OnesHisRecord");
		assertEquals("获取历史记录成功", msg);
		assertEquals(records, records2);
	}

	@Test
	public void testGetOnesHistoryRecordsByPage() {
		map = recordService.getOnesHistoryRecordsByPage(1, 10);
		String msg = (String) map.get("msg");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("historyRecords");
		int pageNum = (Integer)map.get("totalPage");
		int amounts = (Integer)map.get("amounts");
		assertEquals("获取历史记录成功", msg);
		assertEquals(records, records2);
		assertEquals(10, amounts);
	}

	@Test
	public void testGetOnesRecentRecords() {
		map = recordService.getOnesRecentRecords();
		String msg = (String) map.get("msg");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("recentRecord");
		assertEquals("最近一周借阅记录", msg);
		assertEquals(records, records2);
	}

	@Test
	public void testGetOnesOverTime() {
		List<BorrowRecord> records2 = new ArrayList<BorrowRecord>();
		records2 = recordService.getOnesOverTime();
		assertTrue(records2!=null);
	}

	@Test
	public void testGetOnesOvertimeByPage() {
		map = recordService.getOnesOvertimeByPage(records,1, 10);
		String msg = (String) map.get("msg");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("overTimeRecords");
		int pageNum = (Integer)map.get("totalPage");
		int amounts = (Integer)map.get("amounts");
		assertEquals("已超期的图书", msg);
		assertEquals(records, records2);
		assertEquals(10, amounts);
	}

	@Test
	public void testGetOnesWillOverTime() {
		map = recordService.getOnesWillOverTime();
		String msg = (String) map.get("msg");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("overTimeRecord");
		assertEquals("即将超期图书", msg);
		assertEquals(records, records2);
	}

	@Test
	public void testGetOneRecordById() {
		assertEquals(record, recordService.getOneRecordById(0));
	}

	@Test
	public void testGetOneRecordId() {
		assertEquals(20, recordService.getOneRecordId(record));
	}

	@Test
	public void testGetCurrenyRecordsByBookNum() {
		assertEquals(false, recordService.getCurrenyRecordsByBookNum("test"));;
	}
	@Test
	public void testGetCurrenyRecordsByBookNum2() {
		Mockito.when(borrowDao.selectCurrentRecordByBookNum(Matchers.anyString())).thenReturn(null);
		assertEquals(true, recordService.getCurrenyRecordsByBookNum("test"));;
	}
}
