package lepartycious.daos;

import lepartycious.models.User;

public interface UserDAO {

	public User loadUserByUsername(String username) throws Exception;
	
	public void resetPassword(User user);
	
	public void saveOrUpdateUser(User user);

	User loadInactiveUser(String username) throws Exception;
}
