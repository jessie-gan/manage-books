package jessie.booksmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import jessie.booksmanage.dao.ReadBeanDao;
import jessie.booksmanage.pojo.ReadBeanRecord;
import jessie.booksmanage.service.ReadBeanRecordService;

@Service("readBeanRecordService")
public class ReadBeanRecordServiceImpl implements ReadBeanRecordService{

	@Resource
	ReadBeanDao readBeanDao;
	
	@Override
	public int AddReadBeanRecord(String stuNum,int option,int bean) {
		ReadBeanRecord beanRecord = new ReadBeanRecord();
		beanRecord.setStuNum(stuNum);
		beanRecord.setOption(option);
		beanRecord.setReadBean(bean);
		readBeanDao.insertBeanRecord(beanRecord);
		return 0;
	}
	@Override
	public Map<String, Object> getBeanRecordByStuNum(String stuNum,int page,int steps) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		int startNum = 0;
		startNum = (page-1)*steps;
		int totalPage=1;
		parameterMap.put("startNum", startNum);
		parameterMap.put("steps", steps);
		parameterMap.put("stuNum", stuNum);
		List<ReadBeanRecord> beanRecord = this.readBeanDao.selectByStuNum(parameterMap);
		int amounts = beanRecord.size();
		if(amounts%steps==0){
			totalPage = amounts/steps;
		}else{
			totalPage = amounts/steps + 1;
		}
		map.put("msg", "R豆记录");
		map.put("beanRecords", beanRecord);
		map.put("totalPage", totalPage);
		return map;
	}

}
