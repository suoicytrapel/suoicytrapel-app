package lepartycious.services.implementations;


import java.io.IOException;
import java.util.Date;

import lepartycious.Enums.UserTypeEnum;
import lepartycious.Enums.VendorTypeEnum;
import lepartycious.daos.CommonDAO;
import lepartycious.daos.UserDAO;
import lepartycious.daos.VenueDAO;
import lepartycious.daos.implementations.VenueDAOImpl;
import lepartycious.dtos.requestDTOs.ContactRequestDTO;
import lepartycious.dtos.requestDTOs.UserRequestDTO;
import lepartycious.models.User;
import lepartycious.models.Venue;
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
    private CommonService commonService;
    
    @Autowired
    private VenueDAO venueDAO;

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
			String mailSubject = "Forgot Password Request";
			String mailContent = generateMailContent(user);
			emailService.sendMail(user.getEmail(), mailFrom, mailSubject, mailContent);
		}
		else{
			throw new Exception("Invalid Username");
		}
		
	}

	@Override
	public void resetPassword(UserRequestDTO userDTO) throws Exception {
		User user = userDAO.loadUserByUsername(userDTO.getUsername());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean matched = false;
		if(StringUtils.isNotEmpty(userDTO.getOldPassword())){
			matched = encoder.matches(userDTO.getOldPassword(), user.getPasswordDigest());
			if(!matched){
				throw new Exception("Old Password did not match");
			}
		}
		if(user != null || matched){
			String rawPassword = userDTO.getPassword();
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
		isUsernameAvailable = isUsernameAvailable(username);
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
			if(UserTypeEnum.VENDOR.toString().equalsIgnoreCase(userDTO.getUserRole())){
				/*String vendorType = userDTO.getVendorType();
				String entityName = userDTO.getEntityName();
				commonService.createEntity(vendorType, entityName);*/
				user.setVendorName(userDTO.getEntityName());
				user.setVendorType(userDTO.getVendorType());
			}
			userDAO.saveOrUpdateUser(user);
			if(userDTO.getIsAppUser())
				sendActivationLink(user);
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
		sbf.append("Hi " + user.getName() +",<br><br>We have recieved a account activation request from your end.<br>");
		sbf.append("Please click <a href=http://localhost:9001/activate?activateLink=" + activationLink +">Here</a> to activate your account with LePartycious.<br>");
		sbf.append("<br>Cheers,<br>Lepartycious Team");
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
		sbf.append("Hi " + user.getName() +",<br>\nWe have recieved a forgot password request from your end.<br>");
		sbf.append("Please click <a href=www.lepartycious.com/resetPassword?reset_token=" + resetToken +">Here</a> to go to the password reset page.<br>");
		sbf.append("<br><br>Cheers,<br>Lepartycious Team");
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
		Venue venue = venueDAO.loadVenueByUserId(user.getUserId());
		String vendorName = venue != null ? venue.getName() : user.getVendorName();
		if(user.getIsAppUser()==isAppUser && user.getUserRole().equalsIgnoreCase(userType)){
			UserRequestDTO userRequestDTO = new UserRequestDTO();
			userRequestDTO.setName(user.getName());
			userRequestDTO.setUserRole(user.getUserRole());
			userRequestDTO.setEmail(user.getEmail());
			userRequestDTO.setVendorType(user.getVendorType());
			userRequestDTO.setEntityName(vendorName);
			return userRequestDTO;
		}
		else{
			throw new Exception("User with username " + user.getUsername() + " does not exist");
		}
		
	}

	@Override
	public void activateAccount(String activationLink) throws Exception {
		String username = decodeUserString(activationLink);
		User user = userDAO.loadInactiveUser(username);
		if(user != null){
			user.setIsActive(true);
			userDAO.saveOrUpdateUser(user);
		}
		else{
			throw new Exception("User with username " + user.getUsername() + " does not exist");
		}
	}
}
