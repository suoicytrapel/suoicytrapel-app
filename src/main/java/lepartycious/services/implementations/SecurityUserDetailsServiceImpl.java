package lepartycious.services.implementations;


import java.io.IOException;
import java.util.Date;

import lepartycious.daos.UserDAO;
import lepartycious.dtos.requestDTOs.ContactRequestDTO;
import lepartycious.dtos.requestDTOs.UserRequestDTO;
import lepartycious.models.User;
import lepartycious.services.EmailService;
import lepartycious.services.SecurityUserDetailsService;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * UserDetails service that reads the user credentials from the database, using a JPA repository.
 *
 */
@Service
public class SecurityUserDetailsServiceImpl implements SecurityUserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(SecurityUserDetailsServiceImpl.class);
    private static final String DEFAULT_PASSWORD = "Dummy";

    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private EmailService emailService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user;
        try{
        	user = userDAO.loadUserByUsername(username);
        }
        catch(Exception exception){
        	LOGGER.error("User not found" + username, exception);
        	throw new UsernameNotFoundException("User not found" + username, exception);
        }
        return user;
    }

	@Override
	public void forgotPassword(String username) throws Exception {
		User user = userDAO.loadUserByUsername(username);
		if(user != null){
			String mailFrom = "no-reply@gmail.com";
			String mailSubject = "Password Reset Request";
			String mailContent = generateMailContent(user);
			emailService.sendMail(user.getEmail(), mailFrom, mailSubject, mailContent);
		}
		else{
			throw new Exception("Invalid Username");
		}
		
	}

	@Override
	public void resetPassword(UserRequestDTO userDTO) throws Exception {
		User user = userDAO.loadUserByUsername(userDTO.getEmail());
		if(user != null){
			String rawPassword = userDTO.getPassword();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String password = encoder.encode(rawPassword);
			user.setPasswordDigest(password);
			userDAO.saveOrUpdateUser(user);
		}
		else{
			throw new Exception("Invalid Username");
		}
		
	}

	@Override
	public void createUser(UserRequestDTO userDTO) {
		String username = StringUtils.isEmpty(userDTO.getUsername()) ? userDTO.getEmail() : userDTO.getUsername();
		User user = new User();
		String rawPassword = userDTO.getIsAppuser() ? userDTO.getPassword() : DEFAULT_PASSWORD;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setUsername(username.toUpperCase());
		user.setPasswordDigest(encoder.encode(rawPassword));
		user.setEmail(userDTO.getEmail());
		user.setAddedOn(new Date());
		user.setName(userDTO.getName());
		user.setIsAppUser(userDTO.getIsAppuser());
		user.setUserRole(userDTO.getUserRole());
		userDAO.saveOrUpdateUser(user);
	}

	@Override
	public boolean isUsernameAvailable(String username) throws Exception {
		User user = userDAO.loadUserByUsername(username);
		if(user != null){
			return Boolean.FALSE;
		}
		else{
			return Boolean.TRUE;
		}
	}
	
	private String generateMailContent(User user){
		StringBuffer sbf = new StringBuffer();
		BASE64Encoder encoder = new BASE64Encoder();
		String resetToken = encoder.encode(user.getUsername().getBytes());
		sbf.append("Hi" + user.getName() +",\n\nWe have recieved a password reset request from your end.\n");
		sbf.append("Please click <a href=www.lepartycious.com/resetPasswprd?reset_token=" + resetToken +">Here</a> to go to the password reset page:-\n.");
		sbf.append("\n\nCheers,\nLepartycious Team");
		return sbf.toString();
	}

	@Override
	public String decodeUserString(String username) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedUsername = decoder.decodeBuffer(username);
		String actualUsername = new String(decodedUsername);
		return actualUsername;
	}
}
