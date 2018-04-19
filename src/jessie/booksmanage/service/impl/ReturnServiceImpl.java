package jessie.booksmanage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import jessie.booksmanage.pojo.BorrowRecord;
import jessie.booksmanage.pojo.User;
import jessie.booksmanage.service.BookService;
import jessie.booksmanage.service.ReadBeanRecordService;
import jessie.booksmanage.service.RecordService;
import jessie.booksmanage.service.ReturnService;
import jessie.booksmanage.service.UserService;
@Service("returnService")
public class ReturnServiceImpl implements ReturnService {

	@Resource
	BookService bookService;
	@Resource
	RecordService recordService;
	@Resource
	ReadBeanRecordService readBeanRecordService;
	@Resource
	UserService userService;

	@Override
	public Map<String, Object> ReturnBook(Integer id,String bookNum, String stuNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		//HttpSession session = request.getSession();
		User user = userService.getUserByStudentNum(stuNum);
		int bean = user.getReadBean();
		recordService.modifyBorrowState(id);
		recordService.modifyReturnTime(id);
		bookService.modifyBookState(bookNum,0);
		BorrowRecord record = recordService.getOneRecordById(id);
		Date nowDate = new Date();
		String shouldReTime= record.getShouldReturn_time();
		shouldReTime = shouldReTime.substring(0,10);
		long days=0;
	//	shouldReTime = shouldReTime.replaceAll("-", "");
		try {
			Date shouldReDate = new SimpleDateFormat("yyyy-MM-dd").parse(shouldReTime);
			String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(nowDate); 
			nowDate =  new SimpleDateFormat("yyyy-MM-dd").parse(nowTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(shouldReDate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(nowDate);
			long time2 = cal.getTimeInMillis();
			days = (time1 - time2) / (1000 * 24 * 60 * 60); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(days<0){
			map.put("msg", "图书超期归还，扣除5R豆");
			bean = bean-5;
		}else{
			map.put("msg", "已还书");
		}
		userService.modifyUserReadBean(stuNum, bean);
		readBeanRecordService.AddReadBeanRecord(stuNum, 2, bean);
		return map;
	}


}
