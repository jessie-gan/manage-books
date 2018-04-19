package jessie.booksmanage.dao;

import org.springframework.stereotype.Repository;

import jessie.booksmanage.pojo.AdminUser;
@Repository
public interface AdminUserDAO {
	AdminUser selectByAdminUsername(String ad_username);
}
