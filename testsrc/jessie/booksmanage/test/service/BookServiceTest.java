package jessie.booksmanage.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import jessie.booksmanage.dao.BookDAO;
import jessie.booksmanage.pojo.Book;
import jessie.booksmanage.service.BookService;
import jessie.booksmanage.service.impl.BookServiceImpl;




@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest extends AbstractJUnit4SpringContextTests{
	@Mock
	private BookDAO bookDao;
	
	@InjectMocks
	@Autowired
	private BookServiceImpl bookService;
	Map<String,Object> map = new HashMap<String,Object>();
	private List<Book> books = new ArrayList<Book>();
	String bookNum = "test_0001";
	String info[] = {"test_0001","软件测试测试","jessietest","清华大学出版社","简介test"};
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this); 
		//bookService = new BookServiceImpl();
		int startNum = 0;
		int paths = 10;
		map.put("bookName", "%软件测试%");
		map.put("startNum", startNum);
		map.put("steps", paths);
		String info[] = {"test_0001","软件测试测试","jessietest","清华大学出版社","简介test"};
		for(int i = 0;i<5;i++){
			Book book = new Book();
			book.setBook_id(i);
			book.setBook_num(info[0]);
			book.setBook_name(info[1]);
			book.setBook_author(info[2]);
			book.setBook_press(info[3]);
			book.setBook_summary(info[4]);
			book.setBook_state(0);
			books.add(book);
		}
		Mockito.when(bookDao.selectBookByName(Matchers.anyMap())).thenReturn(books);
		Mockito.when(bookDao.selectAllBooks(Matchers.anyMap())).thenReturn(books);
		Mockito.when(bookDao.selectBookByNum(bookNum)).thenReturn(books.get(0));
		Mockito.when(bookDao.updateBook(books.get(0))).thenReturn(1);
		Mockito.when(bookDao.updateBookState(books.get(0))).thenReturn(1);
		Mockito.when(bookDao.selectBookAmounts()).thenReturn(5);
	}

	@After
	public void tearDown() throws Exception {
	}



	@SuppressWarnings("unchecked")
	@Test
	public void testGetBookByBookName() {
		Map<String, Object> map = new HashMap<String, Object>();
		String bookName = "test_0001";
		int page = 1;
		int steps = 10;
		map = bookService.getBookByBookName(bookName, page, steps);
		assertEquals("获取图书成功", map.get("msg"));
		assertEquals(1, map.get("pageNum"));
		assertEquals(books, (List<Book>)(map.get("searchResult")));
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetBookByBookName2() {
		Map<String, Object> map = new HashMap<String, Object>();
		Mockito.when(bookDao.selectBookByName(Matchers.anyMap())).thenReturn(new ArrayList<Book>());
		String bookName = "test_0001";
		int page = 1;
		int steps = 10;
		map = bookService.getBookByBookName(bookName, page, steps);
		map.put("msg", "当前没有符合条件的图书");
		assertEquals(null, map.get("pageNum"));
		assertEquals(null, map.get("searchResult"));
	}
	@Test
	public void testGetAllBooks() {
		Map<String, Object> map = new HashMap<String, Object>();
		int page = 1;
		int steps = 10;
		map = bookService.getAllBooks(page, steps);
		assertEquals("获取所有书籍成功", map.get("msg"));
		assertEquals(1, map.get("totalPage"));
		assertEquals(5, map.get("amounts"));
		assertEquals(books, map.get("bookList"));
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllBooks2() {
		Map<String, Object> map = new HashMap<String, Object>();
		Mockito.when(bookDao.selectAllBooks(Matchers.anyMap())).thenReturn(new ArrayList<Book>());
		Mockito.when(bookDao.selectBookAmounts()).thenReturn(0);
		int page = 1;
		int steps = 10;
		map = bookService.getAllBooks(page, steps);
		assertEquals("当前没有图书", map.get("msg"));
		assertEquals(null, map.get("totalPage"));
		assertEquals(null, map.get("amounts"));
		assertEquals(null, map.get("bookList"));
	}
	@Test
	public void testAddbook() {
		assertEquals(0, bookService.Addbook(info[0], info[1], info[2], info[3], info[4]));
	}
	@Test
	public void testAddbook2() {
		Mockito.when(bookDao.selectBookByNum(bookNum)).thenReturn(null);
		assertEquals(1, bookService.Addbook(info[0], info[1], info[2], info[3], info[4]));
	}
	@Test
	public void testModifyBookInfo() {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("startNum", 0);
		parameterMap.put("steps", 10);
		parameterMap.put("bookName", "test_0001");
		assertEquals(books, bookDao.selectBookByName(parameterMap));
	}

	@Test
	public void testModifyBookState() {
		assertEquals(1, bookService.modifyBookState(bookNum, 1));
	}

}
