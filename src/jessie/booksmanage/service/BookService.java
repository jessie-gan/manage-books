package jessie.booksmanage.service;

import java.util.Map;

import jessie.booksmanage.pojo.Book;

public interface BookService {
	public Book getBookByBookId(int book_id);
	public Book getBookByBookNum(String book_num);
	public Map<String,Object> getBookByBookName(String bookName,int page,int steps);
	public Map<String,Object> getAllBooks(int page,int steps);
	public int getBookAmounts();
	public String getMaxBookNum();
	//简介和图片
	public int Addbook(String bookNum,String bookName,String author,String bookPress,String summary);
	public int modifyBookInfo(String bookNum,String bookName,String author,String bookPress,String summary);
	public int modifyBookState(String bookNum,int state);
	public int deleteBook(String book_num);
}
