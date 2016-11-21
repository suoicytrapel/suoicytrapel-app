package lepartycious.services.implementations;


import java.io.IOException;
import java.util.Date;

import lepartycious.Enums.UserTypeEnum;
import lepartycious.Enums.VendorTypeEnum;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.UserDAO;
import lepartycious.dtos.requestDTOs.ContactRequestDTO;
import lepartycious.dtos.requestDTOs.UserRequestDTO;
import lepartycious.models.User;
import lepartycious.services.CommonService;
import lepartycious.services.EmailService;
import lepartycious.services.SecurityUserDetailsService;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    @Autowired
    private CommonDAO commonDAO;

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
	public void createUser(UserRequestDTO userDTO) throws Exception {
		boolean isUsernameAvailable = true;
		String username = userDTO.getUsername();
		if(!userDTO.getIsAppUser()){
			isUsernameAvailable = isUsernameAvailable(username);
		}
		if(isUsernameAvailable){
			User user = new User();
			String rawPassword = userDTO.getPassword();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setUsername(username.toUpperCase());
			user.setPasswordDigest(encoder.encode(rawPassword));
			user.setEmail(userDTO.getEmail());
			user.setAddedOn(new Date());
			user.setName(userDTO.getName());
			user.setIsAppUser(userDTO.getIsAppUser());
			user.setUserRole(userDTO.getUserRole());
			user.setIsActive(!userDTO.getIsAppUser());
			userDAO.saveOrUpdateUser(user);
			if(userDTO.getIsAppUser())
				sendActivationLink(user);
			/*if(UserTypeEnum.VENDOR.toString().equalsIgnoreCase(userDTO.getUserRole())){
				String vendorType = userDTO.getVendorType();
				String entityName = userDTO.getEntityName();
				commonDAO.createEntity(vendorType, entityName);
			}*/
		}
	}

	private void sendActivationLink(User user) {
		String mailFrom = "no-reply@gmail.com";
		String mailSubject = "Account Activation Request";
		String mailContent = generateActivationLinkMailContent(user);
		emailService.sendMail(user.getEmail(), mailFrom, mailSubject, mailContent);
	}

	private String generateActivationLinkMailContent(User user) {
		StringBuffer sbf = new StringBuffer();
		BASE64Encoder encoder = new BASE64Encoder();
		String activationLink = encoder.encode(user.getUsername().getBytes());
		sbf.append("Hi " + user.getName() +",\n\nWe have recieved a account activation request from your end.\n");
		sbf.append("Please click <html><body><a href=www.lepartycious.com/activate?link=" + activationLink +">Here</a></html></body> to activate your account with LePartycious:-\n.");
		sbf.append("\n\nCheers,\nLepartycious Team");
		return sbf.toString();
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
		sbf.append("Hi " + user.getName() +",\n\nWe have recieved a password reset request from your end.\n");
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

	@Override
	public UserRequestDTO getLoggedInUser(String userType, Boolean isAppUser) throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user.getIsAppUser()==isAppUser && user.getUserRole()==userType){
			UserRequestDTO userRequestDTO = new UserRequestDTO();
			userRequestDTO.setName(user.getName());
			userRequestDTO.setUserRole(user.getUserRole());
			userRequestDTO.setEmail(user.getEmail());
			return userRequestDTO;
		}
		else{
			throw new Exception("User with username " + user.getUsername() + " does not exist");
		}
		
	}

	@Override
	public void activateAccount(String activationLink) throws Exception {
		String username = decodeUserString(activationLink);
		User user = userDAO.loadUserByUsername(username);
		if(user != null){
			user.setIsActive(true);
			userDAO.saveOrUpdateUser(user);
		}
		else{
			throw new Exception("User with username " + user.getUsername() + " does not exist");
		}
	}
}
