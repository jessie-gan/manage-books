package jessie.booksmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import jessie.booksmanage.dao.BookDAO;
import jessie.booksmanage.pojo.Book;
import jessie.booksmanage.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Resource
	private BookDAO bookDao;

	@Override
	public Book getBookByBookNum(String book_num) {
		// TODO Auto-generated method stub
		return this.bookDao.selectBookByNum(book_num);
	}

	@Override
	public Book getBookByBookId(int book_id) {

		return this.bookDao.selectBookById(book_id);
	}

	@Override
	public int getBookAmounts() {

		return this.bookDao.selectBookAmounts();
	}

	@Override
	public Map<String, Object> getAllBooks(int page, int steps) {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		int startNum = 0;
		startNum = (page - 1) * 10;
		parameterMap.put("startNum", startNum);
		parameterMap.put("steps", steps);
		List<Book> bookList = this.bookDao.selectAllBooks(parameterMap);
		Integer bookAmounts = this.bookDao.selectBookAmounts();
		int pageNum = 1;
		if (bookList.isEmpty()) {
			map.put("msg", "当前没有图书");
		} else {
			if (bookAmounts % steps == 0) {
				pageNum = bookAmounts / steps;
			} else {
				pageNum = (bookAmounts / steps) + 1;
			}
			map.put("msg", "获取所有书籍成功");
			map.put("bookList", bookList);
			map.put("totalPage", pageNum);
			map.put("amounts", bookAmounts);
		}
		return map;
	}

	@Override
	public int Addbook(String bookNum, String bookName, String author, String bookPress, String summary) {
		Book book = this.bookDao.selectBookByNum(bookNum);
		if (book != null) {
			return 0;
		} else {
			book = new Book();
			book.setBook_num(bookNum);
			book.setBook_name(bookName);
			book.setBook_author(author);
			book.setBook_press(bookPress);
			book.setBook_summary(summary);
			this.bookDao.insertBook(book);
			return 1;
		}
	}

	@Override
	public int modifyBookInfo(String bookNum, String bookName, String author, String bookPress, String summary) {
		Book book = this.bookDao.selectBookByNum(bookNum);
		book.setBook_name(bookName);
		book.setBook_author(author);
		book.setBook_press(bookPress);
		book.setBook_summary(summary);
		this.bookDao.updateBook(book);
		return 1;
	}

	@Override
	public int modifyBookState(String bookNum, int state) {
		Book book = this.getBookByBookNum(bookNum);
		book.setBook_state(state);
		return this.bookDao.updateBookState(book);
	}

	@Override
	public int deleteBook(String book_num) {
		// TODO Auto-generated method stub
		return this.bookDao.deleteBook(book_num);
	}

	@Override
	public String getMaxBookNum() {
		// TODO Auto-generated method stub
		return this.bookDao.selectMaxBookNum();
	}

	@Override
	public Map<String, Object> getBookByBookName(String bookName, int page, int steps) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Map<String, Object> parameterMap2 = new HashMap<String, Object>();
		int startNum = 0;
		int pageNum=1;
		startNum = (page - 1) * 10;
		parameterMap.put("startNum", startNum);
		parameterMap.put("steps", steps);
		parameterMap.put("bookName", bookName);
		parameterMap2.put("startNum", 0);
		parameterMap2.put("steps", this.bookDao.selectBookAmounts());
		parameterMap2.put("bookName", bookName);
	
		List<Book> searchResult = this.bookDao.selectBookByName(parameterMap);
		List<Book> allResult = this.bookDao.selectBookByName(parameterMap2);
		int amounts = allResult.size();
		if (searchResult.isEmpty()) {
			map.put("msg", "当前没有符合条件的图书");
			return map;
		} else {
			if (amounts % steps == 0) {
				pageNum = amounts / steps;
			} else {
				pageNum = (amounts / steps) + 1;
			}
		}
		map.put("msg", "获取图书成功");
		map.put("pageNum", pageNum);
		map.put("searchResult", searchResult);
		return map;
	}

}
