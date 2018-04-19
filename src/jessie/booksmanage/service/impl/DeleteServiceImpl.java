package jessie.booksmanage.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jessie.booksmanage.service.BookService;
import jessie.booksmanage.service.DeleteService;
import jessie.booksmanage.service.RecordService;
import jessie.booksmanage.service.UserService;

@Service("deleteService")
public class DeleteServiceImpl implements DeleteService {
	@Autowired
	BookService bookService;
	@Autowired
	RecordService recordService;
	@Autowired
	UserService userService;

	@Override
	public boolean deleteBook(String bookNum) {
		if (recordService.getCurrenyRecordsByBookNum(bookNum)) {
			bookService.deleteBook(bookNum);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteUser(String StuNum) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map = recordService.getOnesCurrentRecords(StuNum);
		if (map.get("msg").equals("获取当前借阅记录成功")) {
			return false;
		} else {
			userService.deleteUser(StuNum);
			return true;
		}

	}

}
