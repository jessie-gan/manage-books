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

import jessie.booksmanage.dao.ReadBeanDao;
import jessie.booksmanage.pojo.ReadBeanRecord;
import jessie.booksmanage.test.base.DBInAndOut;
import jessie.booksmanage.test.base.DBUtil;
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring-mybatis.xml") 
@ContextConfiguration(locations={"file:resources/spring-mybatis.xml" } ) 
@WebAppConfiguration 
public class ReadBeanDaoTest {
	@Autowired
	ReadBeanDao readBeanDao;
	private static String TEST_DIR = "DBData/"; 
	File file = new File(TEST_DIR + "beansrecord.xml");
	String stuNum = "20121232";
	Map<String,Object> map = new HashMap<String,Object>();
	@Before
	public void setUp() throws Exception {
		Connection connection = DBUtil.getConnection(); 
		 String[] tableNames = new String[] { "beansrecord" }; 
		 DBInAndOut.exportTables(connection, tableNames, file);
	}

	@After
	public void tearDown() throws Exception {
		Connection connection = DBUtil.getConnection(); 
		 DBInAndOut.importData(file, connection);
		 DBUtil.close(connection);
	}

	@Test
	public void testSelectByStuNum() {
		int startNum=0;
		int path = 10;
		map.put("stuNum", stuNum);
		map.put("startNum", startNum);
		map.put("steps", path);
		List<ReadBeanRecord> beanReccord = this.readBeanDao.selectByStuNum(map);
		//assertTrue(beanReccord.size()<=10 && beanReccord.size()>0);
		assertEquals(8, beanReccord.size());
	}

	@Test
	public void testSelectAllBeanRecord() {
		int startNum=0;
		int path = 100;
		map.put("startNum", startNum);
		map.put("steps", path);
		List<ReadBeanRecord> beanReccord = this.readBeanDao.selectAllBeanRecord(map);
		assertEquals(15,beanReccord.size());
	}

	@Test
	public void testInsertBeanRecord() {
		Integer option = 0;
		Integer readbean = 25;
		String info="11111111";
		ReadBeanRecord record = new ReadBeanRecord();
		record.setStuNum(info);
		record.setOption(option);
		record.setReadBean(readbean);
		this.readBeanDao.insertBeanRecord(record);
		
		//从数据库中获取刚刚插入的记录
		int startNum=0;
		int path = 10;
		map.put("stuNum", info);
		map.put("startNum", startNum);
		map.put("steps", path);
		List<ReadBeanRecord> beanReccord = this.readBeanDao.selectByStuNum(map);
		
		assertEquals(info, beanReccord.get(0).getStuNum());
		assertEquals(option, beanReccord.get(0).getOption());
		assertEquals(readbean, beanReccord.get(0).getReadBean());
		
	}

}
