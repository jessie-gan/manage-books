package jessie.booksmanage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import jessie.booksmanage.dao.BorrowListDAO;
import jessie.booksmanage.pojo.BorrowRecord;
import jessie.booksmanage.service.RecordService;

@Service("recordService")
public class RecordServiceImpl implements RecordService {

	@Resource
	private BorrowListDAO borrowListDAO;

	public int getTotalPageNum(int amounts, int steps) {
		int pageNum = 1;
		if (amounts % steps == 0) {
			pageNum = amounts / steps;
		} else {
			pageNum = (amounts / steps) + 1;
		}
		return pageNum;
	}

	public long getIntervalDays(String time1, String time2) {
		long days;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1;
		try {
			date1 = sdf.parse(time1);
			Date date2 = sdf.parse(time2);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);
			long timeS = cal.getTimeInMillis();
			cal.setTime(date2);
			long timeB = cal.getTimeInMillis();
			days = (timeS - timeB) / (1000 * 24 * 60 * 60);
			return days;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public Map<String, Object> getAllRecordsByPage(int page, int steps) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		int startNum = 0;
		startNum = (page - 1) * steps;
		parameterMap.put("startNum", startNum);
		parameterMap.put("steps", steps);
		List<BorrowRecord> borrowRecords = this.borrowListDAO.selectAllRecordByPage(parameterMap);
		List<BorrowRecord> AllhisRecords = this.borrowListDAO.selectAllRecord();
		List<BorrowRecord> Records = new ArrayList<BorrowRecord>();
		int allAmounts = AllhisRecords.size();
		int totalPage;
		if (borrowRecords.isEmpty()) {
			map.put("msg", "无历史借阅记录");
		} else {
			totalPage = this.getTotalPageNum(allAmounts, steps);
			int endNum;
			startNum = 0;
			endNum = borrowRecords.size();
			for (int i = startNum; i < endNum; i++) {
				Records.add(borrowRecords.get(i));
			}
			map.put("totalPage", totalPage);
			map.put("msg", "历史借阅记录");
			map.put("AllHistoryRecords", Records);
			map.put("amounts", allAmounts);
		}
		return map;
	}

	@Override
	public Map<String, Object> getAllCurrentRecordsByPage(int page, int steps) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		int startNum = 0;
		startNum = (page - 1) * steps;
		parameterMap.put("startNum", startNum);
		parameterMap.put("steps", steps);
		List<BorrowRecord> borrowRecord = this.borrowListDAO.selectCurrentRecords(parameterMap);
		int amounts = this.borrowListDAO.selectRecordAmount();
		int totalPage;
		if (borrowRecord.isEmpty()) {
			map.put("msg", "当前无借阅记录");
		} else {
			map.put("msg", "当前借阅记录");
			map.put("borrowList", borrowRecord);
			totalPage = this.getTotalPageNum(amounts, steps);
			map.put("totalPage", totalPage);
			map.put("amounts", amounts);
		}
		return map;
	}

	@Override
	public Map<String, Object> getAllCurrentRecords() {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		int amounts = borrowListDAO.selectRecordAmount();
		tempMap = this.getAllCurrentRecordsByPage(1, amounts);
		return tempMap;
	}

	@Override
	public Map<String, Object> getAllRecentRecords() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List<BorrowRecord> recentRecord = new ArrayList<BorrowRecord>();
		tempMap = this.getAllCurrentRecords();
		String msg = (String) tempMap.get("msg");
		if (msg.equals("当前无借阅记录")) {
			map.put("msg", msg);
		} else {
			@SuppressWarnings("unchecked")
			List<BorrowRecord> record = (List<BorrowRecord>) tempMap.get("borrowList");
			for (int i = 0; i < record.size(); i++) {
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String borrowTime = record.get(i).getBorrow_time();
				borrowTime = borrowTime.substring(0, 10);
				String nowTime = sdf.format(nowDate);
				long days;
				days = this.getIntervalDays(nowTime, borrowTime);
				if (days <= 7) {
					recentRecord.add(record.get(i));
				}

			}
			if (recentRecord.isEmpty()) {
				map.put("msg", "最近一周无借阅记录");
			} else {
				map.put("msg", "最近一周借阅");
				map.put("allRecentRecord", recentRecord);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAllRecentRecordsByPage(int page, int steps) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List<BorrowRecord> recentRecord = new ArrayList<BorrowRecord>();
		List<BorrowRecord> recentRecord2 = new ArrayList<BorrowRecord>();
		tempMap = this.getAllRecentRecords();
		String msg = (String) tempMap.get("msg");
		if (msg.equals("最近一周无借阅记录") || msg.equals("当前无借阅记录")) {
			map.put("msg", msg);
			return map;
		}
		recentRecord = (List<BorrowRecord>) tempMap.get("allRecentRecord");
		int amounts = recentRecord.size();
		int totalPage;
		int startNum = 0;
		int endNum;
		startNum = (page - 1) * 10;
		endNum = startNum + 10;
		if (endNum > amounts) {
			endNum = amounts;
		}
		for (int i = startNum; i < endNum; i++) {
			recentRecord2.add(recentRecord.get(i));
		}
		map.put("msg", "最近一周借阅");
		map.put("RecentRecord", recentRecord2);
		totalPage = this.getTotalPageNum(amounts, steps);
		map.put("totalPage", totalPage);
		map.put("amounts", amounts);
		return map;
	}

	@Override
	public Map<String, Object> getAllRecentShouldReturn() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List<BorrowRecord> shouldReturn = new ArrayList<BorrowRecord>();
		tempMap = this.getAllCurrentRecords();
		String msg = (String) tempMap.get("msg");
		if (msg.equals("当前无借阅记录")) {
			map.put("msg", msg);
		} else {
			@SuppressWarnings("unchecked")
			List<BorrowRecord> record = (List<BorrowRecord>) tempMap.get("borrowList");
			for (int i = 0; i < record.size(); i++) {
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String shouldReTime = record.get(i).getShouldReturn_time();
				shouldReTime = shouldReTime.substring(0, 10);
				String nowTime = sdf.format(nowDate);
				long days = this.getIntervalDays(shouldReTime, nowTime);
				if (days <= 3) {
					shouldReturn.add(record.get(i));
				}
			}
			if (shouldReturn.isEmpty()) {
				map.put("msg", "没有最近三天需要归还的图书及已超期的图书");
			} else {
				map.put("msg", "最近三天应还图书或已超期图书");
				map.put("allShouldReturn", shouldReturn);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAllRecentShouldReturnByPage(int page, int steps) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List<BorrowRecord> recentRecord = new ArrayList<BorrowRecord>();
		List<BorrowRecord> recentRecord2 = new ArrayList<BorrowRecord>();
		tempMap = this.getAllRecentShouldReturn();
		String msg = (String) tempMap.get("msg");
		if (msg.equals("没有最近三天需要归还的图书及已超期的图书") || msg.equals("当前无借阅记录")) {
			map.put("msg", msg);
			return map;
		}
		recentRecord = (List<BorrowRecord>) tempMap.get("allShouldReturn");
		int amounts = recentRecord.size();
		int totalPage;
		int startNum = 0;
		int endNum;
		startNum = (page - 1) * 10;
		endNum = startNum + 10;
		if (endNum > amounts) {
			endNum = amounts;
		}
		for (int i = startNum; i < endNum; i++) {
			recentRecord2.add(recentRecord.get(i));
		}
		map.put("msg", "最近三天应还图书或已超期图书");
		map.put("ShouldReturn", recentRecord2);
		totalPage = this.getTotalPageNum(amounts, steps);
		map.put("totalPage", totalPage);
		map.put("amounts", amounts);
		return map;

	}

	@Override
	public Map<String, Object> getOnesCurrentRecords(String stuNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<BorrowRecord> currentRecord = this.borrowListDAO.selectOnesCurrentRecord(stuNum);
		if (currentRecord.isEmpty()) {
			map.put("msg", "当前无借阅记录");
		} else {
			map.put("msg", "获取当前借阅记录成功");
			map.put("currentRecords", currentRecord);
		}
		return map;
	}

	@Override
	public Map<String, Object> getOnesHistoryRecords() {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String stuNum = (String) session.getAttribute("stuNum");
		List<BorrowRecord> historyRecord = this.borrowListDAO.selectOnesHistoryRecord(stuNum);
		if (historyRecord.isEmpty()) {
			map.put("msg", "无历史记录");
		} else {
			map.put("msg", "获取历史记录成功");
			map.put("OnesHisRecord", historyRecord);
		}
		return map;
	}

	@Override
	public Map<String, Object> getOnesHistoryRecordsByPage(int page, int steps) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String stuNum = (String) session.getAttribute("stuNum");
		List<BorrowRecord> records = this.borrowListDAO.selectOnesHistoryRecord(stuNum);
		int amounts = records.size();
		if (amounts == 0) {
			map.put("msg", "无历史借阅记录");
			return map;
		}
		int startNum = (page - 1) * steps;
		int totalPage = 1;
		if (amounts % steps == 0) {
			totalPage = amounts / steps;
		} else {
			totalPage = amounts / steps + 1;
		}
		parameterMap.put("stuNum", stuNum);
		parameterMap.put("startNum", startNum);
		parameterMap.put("steps", steps);
		List<BorrowRecord> historyRecords = this.borrowListDAO.selectOnesHistoryRecordByPage(parameterMap);
		map.put("totalPage", totalPage);
		map.put("historyRecords", historyRecords);
		map.put("msg", "获取历史记录成功！");
		map.put("amounts", amounts);
		return map;
	}

	@Override
	public Map<String, Object> getOnesRecentRecords() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List<BorrowRecord> recentRecord = new ArrayList<BorrowRecord>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String stuNum = (String) session.getAttribute("stuNum");
		tempMap = this.getOnesCurrentRecords(stuNum);
		String msg = (String) tempMap.get("msg");
		if (msg.equals("当前无借阅记录")) {
			map.put("msg", msg);
		} else {
			@SuppressWarnings("unchecked")
			List<BorrowRecord> record = (List<BorrowRecord>) tempMap.get("currentRecords");
			for (int i = 0; i < record.size(); i++) {
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String borrowTime = record.get(i).getBorrow_time();
				borrowTime = borrowTime.substring(0, 10);
				String nowTime = sdf.format(nowDate);
				long days = this.getIntervalDays(nowTime, borrowTime);
				if (days <= 7) {
					recentRecord.add(record.get(i));
				}
			}
		}
		if (recentRecord.isEmpty()) {
			map.put("msg", "最近一周无借阅记录");
		} else {
			map.put("msg", "最近一周借阅记录");
			map.put("recentRecord", recentRecord);
		}
		return map;
	}

	@Override
	public List<BorrowRecord> getOnesOverTime() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		Map<String, Object> tempMap2 = new HashMap<String, Object>();
		List<BorrowRecord> overTimeRecord = new ArrayList<BorrowRecord>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String stuNum = (String) session.getAttribute("stuNum");
		tempMap = this.getOnesCurrentRecords(stuNum);
		String msg = (String) tempMap.get("msg");
		tempMap2 = this.getOnesHistoryRecords();
		String msg2 = (String) tempMap.get("msg");
		if (msg.equals("当前无借阅记录")) {
			map.put("msg", msg);
		} else {
			@SuppressWarnings("unchecked")
			List<BorrowRecord> record = (List<BorrowRecord>) tempMap.get("currentRecords");
			for (int i = 0; i < record.size(); i++) {
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String shouldReturnTime = record.get(i).getShouldReturn_time();
				shouldReturnTime = shouldReturnTime.substring(0, 10);
				String nowTime = sdf.format(nowDate);
				long days = this.getIntervalDays(shouldReturnTime, nowTime);
				if (days < 0) {
					record.get(i).setBorrow_state(0);
					overTimeRecord.add(record.get(i));
				}
			}
		}
		if (!msg2.equals("无历史记录")) {
			@SuppressWarnings("unchecked")
			List<BorrowRecord> record = (List<BorrowRecord>) tempMap2.get("OnesHisRecord");
			for (int i = 0; i < record.size(); i++) {
				String returnTime = record.get(i).getReturn_time();
				returnTime = returnTime.substring(0, 10);
				String shouldReturnTime = record.get(i).getShouldReturn_time();
				shouldReturnTime = shouldReturnTime.substring(0, 10);
				long days = this.getIntervalDays(returnTime, shouldReturnTime);
				if (days > 0) {
					record.get(i).setBorrow_state(1);
					overTimeRecord.add(record.get(i));
				}
			}
		}
		return overTimeRecord;
	}

	@Override
	public Map<String, Object> getOnesOvertimeByPage(List<BorrowRecord> overTimeRecord, int page, int steps) {
		int amounts = overTimeRecord.size();
		Map<String, Object> map = new HashMap<String, Object>();
		List<BorrowRecord> records = new ArrayList<BorrowRecord>();
		int totalPage;
		int startNum = 0;
		int endNum;
		startNum = (page - 1) * steps;
		endNum = startNum + steps;
		if (endNum > amounts) {
			endNum = amounts;
		}
		if (overTimeRecord.isEmpty()) {
			map.put("msg", "当前无已超期记录");
		} else {
			totalPage = this.getTotalPageNum(amounts, steps);
			for (int i = startNum; i < endNum; i++) {
				records.add(overTimeRecord.get(i));
			}
			map.put("msg", "已超期的图书");
			map.put("overTimeRecords", records);
			map.put("totalPage", totalPage);
			map.put("amounts", amounts);
		}
		return map;
	}

	@Override
	public int addBorrowRecord(BorrowRecord borrowRecord) {
		this.borrowListDAO.insertRecord(borrowRecord);
		borrowRecord.setBorrow_state(0);
		int id = this.borrowListDAO.selectRecordId(borrowRecord);
		this.borrowListDAO.updateShouldReturnTime(id);
		return 0;
	}

	@Override
	public Map<String, Object> getOnesWillOverTime() {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List<BorrowRecord> overTimeRecord = new ArrayList<BorrowRecord>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String stuNum = (String) session.getAttribute("stuNum");
		tempMap = this.getOnesCurrentRecords(stuNum);
		String msg = (String) tempMap.get("msg");
		if (msg.equals("当前无借阅记录")) {
			map.put("msg", msg);
		} else {
			@SuppressWarnings("unchecked")
			List<BorrowRecord> record = (List<BorrowRecord>) tempMap.get("currentRecords");
			for (int i = 0; i < record.size(); i++) {
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String shouldReturnTime = record.get(i).getShouldReturn_time();
				shouldReturnTime = shouldReturnTime.substring(0, 10);
				String nowTime = sdf.format(nowDate);
				long days = this.getIntervalDays(shouldReturnTime, nowTime);
				if (days <= 3 && days >= 0) {
					overTimeRecord.add(record.get(i));
				}
			}
			if (overTimeRecord.isEmpty()) {
				map.put("mag", "没有即将超期的图书");
			} else {
				map.put("msg", "即将超期图书");
				map.put("overTimeRecord", overTimeRecord);
			}
		}
		return map;
	}

	@Override
	public int modifyBorrowState(Integer id) {
		this.borrowListDAO.updateBorrowState(id);
		return 0;
	}

	@Override
	public BorrowRecord getOneRecordById(Integer id) {

		return this.borrowListDAO.selectOneRecordById(id);
	}

	@Override
	public int modifyReturnTime(Integer id) {
		borrowListDAO.updateReturnTime(id);
		return 0;
	}

	@Override
	public int getOneRecordId(BorrowRecord borrowRecord) {
		return borrowListDAO.selectRecordId(borrowRecord);
	}

	@Override
	public boolean getCurrenyRecordsByBookNum(String bookNum) {
		if (this.borrowListDAO.selectCurrentRecordByBookNum(bookNum).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
