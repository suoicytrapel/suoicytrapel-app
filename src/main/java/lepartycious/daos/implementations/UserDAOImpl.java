package lepartycious.daos.implementations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import lepartycious.daos.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public UserDetails loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
