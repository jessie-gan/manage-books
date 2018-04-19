package jessie.booksmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jessie.booksmanage.pojo.Book;
@Repository
public interface BookDAO {
	public Book selectBookById(int book_id);
	public Book selectBookByNum(String book_num);
	public List<Book> selectBookByName(Map<String,Object> map);
	public int selectBookAmounts();
	public String selectMaxBookNum();
	public List<Book> selectAllBooks(Map<String,Object> map);
	public int insertBook(Book book);
	public int updateBook(Book book);
	public int updateBookState(Book book);
	public int deleteBook(String book_num);
}
