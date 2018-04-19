package jessie.booksmanage.service;

import java.util.Map;

public interface BorrowService {
	Map<String,Object> borrowBook(String bookNum,String stuNum);
}
