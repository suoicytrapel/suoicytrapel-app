package lepartycious.daos;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDAO {

	public UserDetails loadUserByUsername(String username);
}
