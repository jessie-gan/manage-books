package jessie.booksmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jessie.booksmanage.pojo.BorrowRecord;
@Repository
public interface BorrowListDAO {
	public List<BorrowRecord> selectAllRecordByPage(Map<String,Object> map);
	public List<BorrowRecord> selectAllRecord();
	public List<BorrowRecord> selectCurrentRecords(Map<String,Object> map);
	public List<BorrowRecord> selectOnesCurrentRecord(String stuNum);
	public List<BorrowRecord> selectOnesHistoryRecordByPage(Map<String,Object> map);
	public List<BorrowRecord> selectOnesHistoryRecord(String stuNum);
	public List<BorrowRecord> selectCurrentRecordByBookNum(String bookNum);
	public BorrowRecord selectOneRecordById(int id);
	
	public int selectRecordAmount();
	public int selecOnesRecordAmount(String borrow_stuNum);
	public int insertRecord(BorrowRecord borrowRecord);
	public int selectRecordId(BorrowRecord borrowRecord);
	public int updateShouldReturnTime(Integer id);
	public int updateReturnTime(Integer id);
	public int updateBorrowState(Integer id);
	public int deleteRecord(String bookNum);
}
