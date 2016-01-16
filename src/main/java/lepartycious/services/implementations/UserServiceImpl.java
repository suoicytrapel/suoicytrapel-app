package lepartycious.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lepartycious.daos.UserDAO;
import lepartycious.models.User;
import lepartycious.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
