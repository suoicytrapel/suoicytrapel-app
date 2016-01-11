package lepartycious.daos;

import lepartycious.models.User;

public interface UserDAO extends BaseDAO{
	
	public User loadUserByUsername(String username);
	
}
