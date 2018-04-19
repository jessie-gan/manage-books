package jessie.booksmanage.service;

import java.util.List;
import java.util.Map;

import jessie.booksmanage.pojo.BorrowRecord;

public interface RecordService {
	public Map<String,Object> getAllRecordsByPage(int page,int steps);
	public Map<String,Object> getAllCurrentRecordsByPage(int page,int steps);
	public Map<String,Object> getAllCurrentRecords();
	public Map<String,Object> getAllRecentRecords();
	public Map<String,Object> getAllRecentRecordsByPage(int page,int steps);
	public Map<String,Object> getAllRecentShouldReturn();
	public Map<String,Object> getAllRecentShouldReturnByPage(int page,int steps);
	public Map<String,Object> getOnesCurrentRecords(String stuNum);
	public Map<String,Object> getOnesHistoryRecords();
	public Map<String,Object> getOnesHistoryRecordsByPage(int page,int steps);
	public Map<String,Object> getOnesRecentRecords();
	public Map<String,Object> getOnesWillOverTime();
	public List<BorrowRecord> getOnesOverTime();
	public Map<String,Object> getOnesOvertimeByPage(List<BorrowRecord> overTimeRecord,int page,int steps);
	public BorrowRecord getOneRecordById(Integer id);
	public boolean getCurrenyRecordsByBookNum(String bookNum);
	public int getOneRecordId(BorrowRecord borrowRecord);
	public int addBorrowRecord(BorrowRecord borrowRecord);
	public int modifyBorrowState(Integer id);
	public int modifyReturnTime(Integer id);
}
