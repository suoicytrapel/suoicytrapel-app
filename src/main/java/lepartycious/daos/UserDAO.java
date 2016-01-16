package lepartycious.daos;

import java.util.Optional;

import lepartycious.models.User;

public interface UserDAO extends BaseDAO{
	
	public User loadUserByUsername(String username);
	
	Optional<User> findOneByEmail(String email);
	
}
