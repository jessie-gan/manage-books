package jessie.booksmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jessie.booksmanage.pojo.ReadBeanRecord;
@Repository
public interface ReadBeanDao {
	List<ReadBeanRecord> selectByStuNum(Map<String,Object> map);
	List<ReadBeanRecord> selectAllBeanRecord(Map<String,Object> map);
	int insertBeanRecord(ReadBeanRecord bean);
}
