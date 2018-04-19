package jessie.booksmanage.test.dao;

import static org.junit.Assert.*;

import java.io.File;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import jessie.booksmanage.dao.AdminUserDAO;
import jessie.booksmanage.pojo.AdminUser;
import jessie.booksmanage.test.base.DBInAndOut;
import jessie.booksmanage.test.base.DBUtil;
 

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring-mybatis.xml") 
@ContextConfiguration(locations={"file:resources/spring-mybatis.xml" } ) 
@WebAppConfiguration 
public class AdminUserDAOTest {
	@Resource
	AdminUserDAO adminDao;
		
	private static String TEST_DIR = "DBData/"; 
	File file = new File(TEST_DIR + "adminUser.xml");
	@Before
	public void setUp() throws Exception {
		
		 java.sql.Connection connection = DBUtil.getConnection(); 
		 String[] tableNames = new String[] {"admin_user" }; 
		 DBInAndOut.exportTables(connection, tableNames, file);
	}

	@After
	public void tearDown() throws Exception {
		java.sql.Connection connection = DBUtil.getConnection(); 
		 DBInAndOut.importData(file, connection);
		 DBUtil.close(connection);
	}

	@Test
	public void testUpdateByPrimeryKey() {
		assertTrue(true);
		
	}

	@Test
	public void testSelectByAdminUsername() {
 		String username = "admin";
 		AdminUser adminUser=  adminDao.selectByAdminUsername(username);
 		assertTrue(adminUser!=null);
		assertTrue(true);
	}

}
