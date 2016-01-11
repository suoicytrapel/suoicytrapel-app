package lepartycious.services.implementations;

import lepartycious.daos.UserDAO;
import lepartycious.models.User;
import lepartycious.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void delete(Long id) {
		userDAO.delete(id);
	}

	@Override
	public User loadUserByUsername(String username) {
		return userDAO.loadUserByUsername(username);
	}
	
}