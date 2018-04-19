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
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import jessie.booksmanage.dao.ReadBeanDao;
import jessie.booksmanage.pojo.ReadBeanRecord;
import jessie.booksmanage.service.impl.ReadBeanRecordServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class ReadBeanRecordServiceImplTest extends AbstractJUnit4SpringContextTests{
	@Mock
	ReadBeanDao readBeanDao;
	@InjectMocks
	@Autowired
	ReadBeanRecordServiceImpl beanService;
	List<ReadBeanRecord> records = new ArrayList<ReadBeanRecord>();
	Map<String,Object> map = new HashMap<String,Object>();
	@Before
	public void setUp() throws Exception {
		for(int i = 0;i<10;i++){
			ReadBeanRecord record = new ReadBeanRecord();
			records.add(record);
		}
		Mockito.when(readBeanDao.selectByStuNum(Matchers.anyMap())).thenReturn(records);
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddReadBeanRecord() {
		assertEquals(0, beanService.AddReadBeanRecord("2012132", 0, 5));
	}

	@Test
	public void testGetBeanRecordByStuNum() {
		int pageNum=1;
		map = beanService.getBeanRecordByStuNum("20121232", pageNum, 10);
		String msg = (String)map.get("msg");
		List<ReadBeanRecord> beanRecords = (List<ReadBeanRecord>)map.get("beanRecords");
		int page =(Integer)map.get("totalPage");
		assertEquals("R豆记录", msg);
		assertEquals(records, beanRecords);
		assertEquals(pageNum, page);
	}

}
