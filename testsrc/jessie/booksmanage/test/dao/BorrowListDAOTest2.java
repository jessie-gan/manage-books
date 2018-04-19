package jessie.booksmanage.test.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jessie.booksmanage.dao.BorrowListDAO;
import jessie.booksmanage.pojo.BorrowRecord;
import jessie.booksmanage.test.base.DBInAndOut;
import jessie.booksmanage.test.base.DBUtil;
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring-mybatis.xml") 
@ContextConfiguration(locations={"file:resources/spring-mybatis.xml" } ) 
@WebAppConfiguration 
public class BorrowListDAOTest2 {
	@Autowired
	BorrowListDAO borrowListDAO;
	private static String TEST_DIR = "DBData/"; 
	File file = new File(TEST_DIR + "borrowList.xml");
	String info[] = {"test_0001","111111"};
	@Before
	public void setUp() throws Exception {
		Connection connection = DBUtil.getConnection(); 
		 String[] tableNames = new String[] { "borrowlist" }; 
		 DBInAndOut.exportTables(connection, tableNames, file);
	}

	@After
	public void tearDown() throws Exception {
		Connection connection = DBUtil.getConnection(); 
		 DBInAndOut.importData(file, connection);
		 DBUtil.close(connection);
	}

	@Test
	public void testInsertRecord() {
		BorrowRecord record = new BorrowRecord();
		record.setBorrow_bookNum(info[0]);
		record.setBorrow_stuNum(info[1]);
		this.borrowListDAO.insertRecord(record);
		int id = this.borrowListDAO.selectRecordId(record);
		this.borrowListDAO.updateShouldReturnTime(id);
		List<BorrowRecord> record2 = this.borrowListDAO.selectCurrentRecordByBookNum(info[0]);
		assertEquals(1, record2.size());
		assertTrue(record2.get(0).getBorrow_bookNum().equals(info[0]));
		assertTrue(record2.get(0).getBorrow_stuNum().equals(info[1]));
		
	}

	@Test
	public void testUpdateShouldReturnTime() {
		BorrowRecord record = new BorrowRecord();
		record.setBorrow_bookNum(info[0]);
		record.setBorrow_stuNum(info[1]);
		this.borrowListDAO.insertRecord(record);
		int id = this.borrowListDAO.selectRecordId(record);
		this.borrowListDAO.updateShouldReturnTime(id);
		BorrowRecord record3 = this.borrowListDAO.selectOneRecordById(id);
		String stime = record3.getShouldReturn_time().substring(0, 10);
		String btime = record3.getBorrow_time().substring(0, 10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date sstime = sdf.parse(stime);
			Date bbtime = sdf.parse(btime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sstime);
			long timeS = cal.getTimeInMillis();
			cal.setTime(bbtime);
			long timeB = cal.getTimeInMillis();
			System.out.println((timeS-timeB)/(1000*24*60*60));
			assertEquals(30, (timeS-timeB)/(1000*24*60*60));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateReturnTime() {
		BorrowRecord record = new BorrowRecord();
		record.setBorrow_bookNum(info[0]);
		record.setBorrow_stuNum(info[1]);
		this.borrowListDAO.insertRecord(record);
		int id = this.borrowListDAO.selectRecordId(record);
		this.borrowListDAO.updateShouldReturnTime(id);
		this.borrowListDAO.updateReturnTime(id);
		BorrowRecord record3 = this.borrowListDAO.selectOneRecordById(id);
		assertEquals(record3.getBorrow_time(), record3.getReturn_time());
		
	}

	@Test
	public void testUpdateBorrowState() {
		BorrowRecord record = new BorrowRecord();
		record.setBorrow_bookNum(info[0]);
		record.setBorrow_stuNum(info[1]);
		this.borrowListDAO.insertRecord(record);
		int id = this.borrowListDAO.selectRecordId(record);
		this.borrowListDAO.updateShouldReturnTime(id);
		List<BorrowRecord> record2 = this.borrowListDAO.selectCurrentRecordByBookNum(info[0]);
		int state = record2.get(0).getBorrow_state();
		assertEquals(0, state);
		}

	@Test
	public void testDeleteRecord() {
		BorrowRecord record = new BorrowRecord();
		record.setBorrow_bookNum(info[0]);
		record.setBorrow_stuNum(info[1]);
		this.borrowListDAO.insertRecord(record);
		int id = this.borrowListDAO.selectRecordId(record);
		this.borrowListDAO.updateShouldReturnTime(id);
		List<BorrowRecord> record2 = this.borrowListDAO.selectCurrentRecordByBookNum(info[0]);
		this.borrowListDAO.deleteRecord(record2.get(0).getBorrow_bookNum());
		BorrowRecord record3 = this.borrowListDAO.selectOneRecordById(id);
		assertTrue(record3==null);
	}

}
