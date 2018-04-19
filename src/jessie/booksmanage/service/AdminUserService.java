package jessie.booksmanage.service;

import jessie.booksmanage.pojo.AdminUser;

public interface AdminUserService {
	AdminUser getUserByName(String ad_username);
}
