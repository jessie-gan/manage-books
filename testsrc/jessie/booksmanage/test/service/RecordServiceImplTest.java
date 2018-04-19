package jessie.booksmanage.test.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import jessie.booksmanage.dao.BorrowListDAO;
import jessie.booksmanage.pojo.BorrowRecord;
import jessie.booksmanage.service.impl.RecordServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RecordServiceImplTest extends AbstractJUnit4SpringContextTests {
	@Mock
	BorrowListDAO borrowDao;
	@InjectMocks
	@Autowired
	RecordServiceImpl recordService;
	Map<String, Object> map = new HashMap<String, Object>();
	List<BorrowRecord> records = new ArrayList<BorrowRecord>();

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
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
		Mockito.when(borrowDao.selectAllRecord()).thenReturn(records);
		Mockito.when(borrowDao.selectAllRecordByPage(Matchers.anyMap())).thenReturn(records);
		Mockito.when(borrowDao.selectCurrentRecords(Matchers.anyMap())).thenReturn(records);
		Mockito.when(borrowDao.selectRecordAmount()).thenReturn(10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetTotalPageNum() {
		int amounts = 20;
		int steps = 10;
		int pageNum = recordService.getTotalPageNum(amounts, steps);
		assertEquals(2, pageNum);
		amounts = 2;
		steps = 10;
		pageNum = recordService.getTotalPageNum(amounts, steps);
		assertEquals(1, pageNum);
	}

	@Test
	public void testGetIntervalDays() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse("2016-05-21");
		String time1 = sdf.format(date1);
		Date date2 = sdf.parse("2016-05-29");
		String time2 = sdf.format(date2);
		long day = recordService.getIntervalDays(time1, time2);
		assertEquals(-8, day);
	}

	@Test
	public void testGetAllRecordsByPage() {
		map = recordService.getAllRecordsByPage(1, 10);
		String msg = (String) map.get("msg");
		int pageNum = (Integer) map.get("totalPage");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("AllHistoryRecords");
		int amounts = (Integer) map.get("amounts");
		assertEquals("历史借阅记录", msg);
		assertEquals(1, pageNum);
		assertEquals(10, amounts);
		assertEquals(records, records2);
	}

	@Test
	public void testGetAllCurrentRecordsByPage() {
		Mockito.when(borrowDao.selectCurrentRecords(Matchers.anyMap())).thenReturn(new ArrayList<BorrowRecord>());
		map = recordService.getAllCurrentRecordsByPage(1, 10);
		String msg = (String) map.get("msg");
		assertEquals("当前无借阅记录", msg);
	}

	@Test
	public void testGetAllCurrentRecordsByPage2() {
		map = recordService.getAllCurrentRecordsByPage(1, 10);
		String msg = (String) map.get("msg");
		int pageNum = (Integer) map.get("totalPage");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("borrowList");
		int amounts = (Integer) map.get("amounts");
		assertEquals("当前借阅记录", msg);
		assertEquals(1, pageNum);
		assertEquals(10, amounts);
		assertEquals(records, records2);
	}

	@Test
	public void testGetAllCurrentRecords() {
		map = recordService.getAllCurrentRecords();
		String msg = (String) map.get("msg");
		int pageNum = (Integer) map.get("totalPage");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("borrowList");
		int amounts = (Integer) map.get("amounts");
		assertEquals("当前借阅记录", msg);
		assertEquals(1, pageNum);
		assertEquals(10, amounts);
		assertEquals(records, records2);
	}

	@Test
	public void testGetAllRecentRecords() {
		map = recordService.getAllRecentRecords();
		String msg = (String) map.get("msg");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("allRecentRecord");
		assertEquals("最近一周借阅", msg);
		assertEquals(records, records2);
	}

	@Test
	public void testGetAllRecentRecords2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 10; i++) {
			Date date = sdf.parse("2016-07-01");
			String time = sdf.format(date);
			Date date2 = sdf.parse("2016-05-20");
			String time2 = sdf.format(date2);
			records.get(i).setShouldReturn_time(time);
			records.get(i).setBorrow_time(time2);
		}
		map = recordService.getAllRecentRecords();
		String msg = (String) map.get("msg");
		assertEquals("最近一周无借阅记录", msg);
	}

	@Test
	public void testGetAllRecentRecordsByPage() {
		map = recordService.getAllRecentRecordsByPage(1, 10);
		String msg = (String) map.get("msg");
		int pageNum = (Integer) map.get("totalPage");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("RecentRecord");
		int amounts = (Integer) map.get("amounts");
		assertEquals("最近一周借阅", msg);
		assertEquals(1, pageNum);
		assertEquals(10, amounts);
		assertEquals(records, records2);
	}

	@Test
	public void testGetAllRecentShouldReturn() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 10; i++) {
			Date date = sdf.parse("2016-06-01");
			String time = sdf.format(date);
			Date date2 = sdf.parse("2016-05-20");
			String time2 = sdf.format(date2);
			records.get(i).setShouldReturn_time(time);
			records.get(i).setBorrow_time(time2);
		}
		map = recordService.getAllRecentShouldReturn();
		String msg = (String) map.get("msg");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("allShouldReturn");
		assertEquals("最近三天应还图书或已超期图书", msg);
		assertEquals(records, records2);
	}

	@Test
	public void testGetAllRecentShouldReturn2() throws ParseException {
		//Mockito.when(borrowDao.selectCurrentRecords(Matchers.anyMap())).thenReturn(null);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 10; i++) {
			Date date = sdf.parse("2016-07-01");
			String time = sdf.format(date);
			Date date2 = sdf.parse("2016-05-20");
			String time2 = sdf.format(date2);
			records.get(i).setShouldReturn_time(time);
			records.get(i).setBorrow_time(time2);
		}
		map = recordService.getAllRecentShouldReturn();
		String msg = (String) map.get("msg");
		assertEquals("没有最近三天需要归还的图书及已超期的图书", msg);
	}

	@Test
	public void testGetAllRecentShouldReturnByPage() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 10; i++) {
			Date date = sdf.parse("2016-06-01");
			String time = sdf.format(date);
			Date date2 = sdf.parse("2016-05-20");
			String time2 = sdf.format(date2);
			records.get(i).setShouldReturn_time(time);
			records.get(i).setBorrow_time(time2);
		}
		map = recordService.getAllRecentShouldReturnByPage(1, 10);
		String msg = (String) map.get("msg");
		int pageNum = (Integer) map.get("totalPage");
		List<BorrowRecord> records2 = (List<BorrowRecord>) map.get("ShouldReturn");
		int amounts = (Integer) map.get("amounts");
		assertEquals("最近三天应还图书或已超期图书", msg);
		assertEquals(1, pageNum);
		assertEquals(10, amounts);
		assertEquals(records, records2);
	}

	@Test
	public void testAddBorrowRecord() {
		BorrowRecord record = new BorrowRecord();
		assertEquals(0, recordService.addBorrowRecord(record));
	}

	@Test
	public void testModifyBorrowState() {
		assertEquals(0, recordService.modifyBorrowState(0));
	}

	@Test
	public void testModifyReturnTime() {
		assertEquals(0, recordService.modifyReturnTime(0));
	}

}
