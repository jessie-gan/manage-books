package jessie.booksmanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jessie.booksmanage.pojo.BorrowRecord;
import jessie.booksmanage.pojo.User;
import jessie.booksmanage.service.BookService;
import jessie.booksmanage.service.BorrowService;
import jessie.booksmanage.service.ReadBeanRecordService;
import jessie.booksmanage.service.RecordService;
import jessie.booksmanage.service.UserService;

@Service("borrowService")
public class BorrowServiceImpl implements BorrowService {
	@Resource
	BookService bookService;
	@Resource
	RecordService recordService;
	@Resource
	ReadBeanRecordService readBeanRecordService;
	@Resource
	UserService userService;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> borrowBook(String bookNum, String stuNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List<BorrowRecord> records = new ArrayList<BorrowRecord>();
		int amounts;
		tempMap = recordService.getOnesCurrentRecords(stuNum);
		records = (List<BorrowRecord>)tempMap.get("currentRecords");
		amounts = records.size();
		if (amounts > 15) {
			map.put("msg", "已达最大借阅量（15本）,请归还后再借阅！");
		} else {
			bookService.modifyBookState(bookNum, 1);
			BorrowRecord borrowRecord = new BorrowRecord();
			borrowRecord.setBorrow_bookNum(bookNum);
			borrowRecord.setBorrow_stuNum(stuNum);
			recordService.addBorrowRecord(borrowRecord);
			User user = userService.getUserByStudentNum(stuNum);
			int bean = user.getReadBean();
			userService.modifyUserReadBean(stuNum, bean + 5);
			readBeanRecordService.AddReadBeanRecord(stuNum, 0, user.getReadBean());
			map.put("msg", "借书成功，获得5个R豆");
		}
		return map;
	}

}
