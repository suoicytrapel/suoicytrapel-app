package lepartycious.services;

import lepartycious.models.User;

import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true)
public interface UserService{
	
	@Transactional(readOnly=false)
	public void delete(Long id);
	
	public User loadUserByUsername(String username);
}