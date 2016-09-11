package lepartycious.daos;

import lepartycious.models.User;

public interface UserDAO {

	public User loadUserByUsername(String username) throws Exception;
}
