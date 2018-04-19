package jessie.booksmanage.test.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jessie.booksmanage.dao.BookDAO;
import jessie.booksmanage.pojo.Book;
import jessie.booksmanage.test.base.DBInAndOut;
import jessie.booksmanage.test.base.DBUtil;
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring-mybatis.xml") 
@ContextConfiguration(locations={"file:resources/spring-mybatis.xml" } ) 
@WebAppConfiguration 
public class BookDAOTest {
	@Resource
	BookDAO bookDao;
		
	private static String TEST_DIR = "DBData/"; 
	File file = new File(TEST_DIR + "book.xml");
	@Before
	public void setUp() throws Exception {
		 Connection connection = DBUtil.getConnection(); 
		 String[] tableNames = new String[] { "bookitem" }; 
		 DBInAndOut.exportTables(connection, tableNames, file);
	}

	@After
	public void tearDown() throws Exception {
		Connection connection = DBUtil.getConnection(); 
		 DBInAndOut.importData(file, connection);
		 DBUtil.close(connection);
	}

	@Test
	public void testSelectBookById() {
		Integer bookId = 100;
		Book book = bookDao.selectBookById(bookId);
		assertTrue(book==null);
	}
	@Test
	public void testSelectBookById2() {
		Integer bookId = 42;
		Book book = bookDao.selectBookById(bookId);
		assertTrue(book!=null);
	}


	@Test
	public void testSelectBookByNum() {
		String bookNum = "MVST_0005";
		Book book = bookDao.selectBookByNum(bookNum);
		assertTrue(book!=null);
	}
	@Test
	public void testSelectBookByNum2() {
		String bookNum = "test_0005";
		Book book = bookDao.selectBookByNum(bookNum);
		assertTrue(book==null);
	}
	@Test
	public void testSelectBookByName() {
		Map<String,Object> map = new HashMap<String, Object>();
		String bookName = "软件测试";
		int startNum = 0;
		int path = 10;
		map.put("bookName", "%"+bookName+"%");
		map.put("startNum", startNum);
		map.put("steps", path);
		List<Book> books = bookDao.selectBookByName(map);
		for(int i=0;i<books.size();i++){
			assertTrue(books.get(i).getBook_name().contains(bookName));
		}
	}

	@Test
	public void testSelectBookAmounts() {
		Integer bookAmounts;
		bookAmounts = bookDao.selectBookAmounts();
		assertTrue(bookAmounts==37);
	}

	@Test
	public void testSelectMaxBookNum() {
		String maxNum="MVST_1000";
		String[] info = {"MVST_1000","软件测试80","jessie","清华大学出版社","简介"};
		Book book = new Book(info[0],info[1],info[2],info[3],info[4]);
		bookDao.insertBook(book);
		assertTrue(bookDao.selectMaxBookNum().equals(maxNum));
	}

	@Test
	public void testSelectAllBooks() {
		Map<String,Object> map = new HashMap<String, Object>();
		int startNum = 0;
		int path = 100;
		map.put("startNum", startNum);
		map.put("steps", path);
		List<Book> books = bookDao.selectAllBooks(map);
		int amounts = 37;
		assertEquals(amounts, books.size());
	}
	@Test
	public void testSelectAllBooks2() {
		Map<String,Object> map = new HashMap<String, Object>();
		int startNum = 0;
		int path = 100;
		map.put("startNum", startNum);
		map.put("steps", path);
		int amounts = 37;
		List<Book> books = bookDao.selectAllBooks(map);
		for(int i=0;i<amounts;i++){
			bookDao.deleteBook(books.get(i).getBook_num());
		}
		books = bookDao.selectBookByName(map);
		assertTrue(books.isEmpty());
	}
	@Test
	public void testInsertBook() {
		String[] info = {"test_0001","软件测试80","jessie","清华大学出版社","简介"};
		Book book = new Book(info[0],info[1],info[2],info[3],info[4]);
		bookDao.insertBook(book);
		Book book2 = bookDao.selectBookByNum(info[0]);
		assertTrue(book2!=null);
	}

	@Test
	public void testUpdateBook() {
		String[] info = {"test_0001","软件测试80","jessie","清华大学出版社","简介"};
		Book book = new Book(info[0],info[1],info[2],info[3],info[4]);
		bookDao.insertBook(book);
		
		String[] info2 = {"test_0002","软件测试002","jessie2","清华大学出版社2","简介2"};
		Book book2 = bookDao.selectBookByNum(book.getBook_num());
		
		book2.setBook_num(info2[0]);
		book2.setBook_name(info2[1]);
		book2.setBook_author(info2[2]);
		book2.setBook_press(info2[3]);
		book2.setBook_summary(info2[4]);
		bookDao.updateBook(book2);
		
		book = bookDao.selectBookByNum(info2[0]);
		String info3[] = {book.getBook_num(),book.getBook_name(),book.getBook_author(),
				book.getBook_press(),book.getBook_summary()};
		assertArrayEquals(info2, info3);
	}

	@Test
	public void testUpdateBookState() {
		String[] info = {"test_0001","软件测试80","jessie","清华大学出版社","简介"};
		Book book = new Book(info[0],info[1],info[2],info[3],info[4]);
		bookDao.insertBook(book);
		book.setBook_state(1);
		bookDao.updateBookState(book);
		Book book2 = bookDao.selectBookByNum(book.getBook_num());
		assertTrue(1==book2.getBook_state());
	}

	@Test
	public void testDeleteBook() {
		String[] info = {"test_0001","软件测试80","jessie","清华大学出版社","简介"};
		Book book = new Book(info[0],info[1],info[2],info[3],info[4]);
		bookDao.insertBook(book);
		bookDao.deleteBook(book.getBook_num());
		Book book2 = bookDao.selectBookByNum(book.getBook_num());
		assertTrue(book2==null);
	}

}
