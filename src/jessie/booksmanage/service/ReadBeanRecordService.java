package jessie.booksmanage.service;

import java.util.Map;

public interface ReadBeanRecordService {
		int AddReadBeanRecord(String stuNum,int option,int bean);
		Map<String,Object> getBeanRecordByStuNum(String stuNum,int page,int steps);
}
