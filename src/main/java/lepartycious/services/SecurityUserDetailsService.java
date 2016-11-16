package lepartycious.services;

import java.io.IOException;

import lepartycious.dtos.requestDTOs.UserRequestDTO;
import lepartycious.models.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional(readOnly=true)
public interface SecurityUserDetailsService extends UserDetailsService{
	
	public User loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public void forgotPassword(String username) throws Exception;
	
	@Transactional(readOnly=false)
	public void resetPassword(UserRequestDTO user) throws Exception;
	
	@Transactional(readOnly=false)
	public void createUser(UserRequestDTO user) throws Exception;
	
	public boolean isUsernameAvailable(String username) throws Exception;
	
	public String decodeUserString(String username) throws IOException;

	public UserRequestDTO getLoggedInUser(String userType, Boolean isAppUser) throws Exception;
	
	@Transactional(readOnly=false)
	public void activateAccount(String activationLink) throws Exception;
}