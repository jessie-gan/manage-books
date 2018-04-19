package jessie.booksmanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jessie.booksmanage.dao.AdminUserDAO;
import jessie.booksmanage.pojo.AdminUser;
import jessie.booksmanage.service.AdminUserService;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

	@Resource
	AdminUserDAO adminUserDao;
	
	@Override
	public AdminUser getUserByName(String ad_username) {
		return this.adminUserDao.selectByAdminUsername(ad_username);
	}

}
