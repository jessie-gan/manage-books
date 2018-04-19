package jessie.booksmanage.test.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class BorrowListDAOTest {
	@Autowired
	BorrowListDAO borrowListDAO;
	private static String TEST_DIR = "DBData/"; 
	File file = new File(TEST_DIR + "borrowList.xml");
	Map<String,Object> map = new HashMap<String, Object>();
	String stuNum = "20121232";
	String bookNum = "MVST_0007";
	int startNum = 0;
	int path = 0;
	int amounts0 = 15;
	int amounts1 = 10;
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
	public void testSelectAllRecordByPage() {
		path = 10;
		map.put("startNum", startNum);
		map.put("steps", path);
		List<BorrowRecord> records = this.borrowListDAO.selectAllRecordByPage(map);
		assertEquals(path, records.size());
	}

	@Test
	public void testSelectAllRecord() {
		List<BorrowRecord> records = this.borrowListDAO.selectAllRecord();
		assertEquals(amounts1, records.size());
	}

	@Test
	public void testSelectCurrentRecords() {
		path = 100;
		map.put("startNum", startNum);
		map.put("steps", path);
		List<BorrowRecord> records = this.borrowListDAO.selectCurrentRecords(map);
		assertEquals(amounts0, records.size());
	}

	@Test
	public void testSelectOnesCurrentRecord() {
		List<BorrowRecord> records = this.borrowListDAO.selectOnesCurrentRecord(stuNum);
		assertTrue(records.size()<15 && records.size()>0);
		
	}

	@Test
	public void testSelectOnesHistoryRecordByPage() {
		path = 10;
		map.put("stuNum", stuNum);
		map.put("startNum", startNum);
		map.put("steps", path);
		List<BorrowRecord> records = this.borrowListDAO.selectOnesHistoryRecordByPage(map);
		assertTrue(records.size()<10 && records.size()>0);
	}

	@Test
	public void testSelectOnesHistoryRecord() {
		List<BorrowRecord> records = this.borrowListDAO.selectOnesHistoryRecord(stuNum);
		assertTrue(records.size()>0);
	}

	@Test
	public void testSelectCurrentRecordByBookNum() {
		List<BorrowRecord> records = this.borrowListDAO.selectCurrentRecordByBookNum(bookNum);
		assertTrue(records.size()>0);
	}

	@Test
	public void testSelectOneRecordById() {
		int id = 17;
		BorrowRecord records = this.borrowListDAO.selectOneRecordById(id);
		assertTrue(records!=null);
	}

	@Test
	public void testSelectRecordAmount() {
		int amounts = this.borrowListDAO.selectRecordAmount();
		assertEquals(amounts0, amounts);
	}

	@Test
	public void testSelecOnesRecordAmount() {
		int amounts = this.borrowListDAO.selecOnesRecordAmount(stuNum);
		int a = 8;
		assertEquals(a, amounts);
	}

	@Test
	public void testSelectRecordId() {
		BorrowRecord borrowRecord = this.borrowListDAO.selectOneRecordById(17);
		int id = this.borrowListDAO.selectRecordId(borrowRecord);
		assertEquals(17, id);
	}

}
