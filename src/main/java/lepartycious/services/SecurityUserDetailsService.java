package lepartycious.services;

import lepartycious.models.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface SecurityUserDetailsService extends UserDetailsService{
	
	public User loadUserByUsername(String username) throws UsernameNotFoundException;

}
