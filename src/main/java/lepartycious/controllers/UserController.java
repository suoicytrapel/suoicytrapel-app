package lepartycious.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.UserRequestDTO;
import lepartycious.services.SecurityUserDetailsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *  REST service for users.
 *
 */

@RestController
@RequestMapping("/api/rest")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    
    @Autowired
    private SecurityUserDetailsService securityuserDetailService;
    
    /*@Autowired
    private TokenStore tokenStore;
*/
    @RequestMapping(value="/secured/v1/getUserDetails", method=RequestMethod.GET)
	public UserRequestDTO getLoggedInUser(@RequestParam String userType, @RequestParam Boolean isAppUser) throws Exception{
    	UserRequestDTO user = securityuserDetailService.getLoggedInUser(userType, isAppUser);
		return user;
	}
    
    @RequestMapping(value="/v1/user/forgotPassword", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void forgotPassword(@RequestParam String username) throws Exception{
    	securityuserDetailService.forgotPassword(username);
    }
    
    @RequestMapping(value="/v1/user/resetPassword", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void resetForgotPassword(@RequestBody UserRequestDTO user) throws Exception{
    	securityuserDetailService.resetPassword(user);
    }
    
    @RequestMapping(value="/secured/v1/user/resetPassword", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@RequestBody UserRequestDTO user) throws Exception{
    	securityuserDetailService.resetPassword(user);
    }
    
    @RequestMapping(value="/v1/user/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody UserRequestDTO user) throws Exception{
    	securityuserDetailService.createUser(user);
    }
    
    @RequestMapping(value="/v1/user/checkAvailability/{username}", method = RequestMethod.GET)
    public Boolean isUsernameAvailable(@PathVariable  String username) throws Exception{
    	return securityuserDetailService.isUsernameAvailable(username);
    }
    
    @RequestMapping(value="/v1/user/decodeString", method = RequestMethod.GET)
    public UserRequestDTO decodeUserString(@RequestParam  String username) throws Exception{
    	UserRequestDTO userRequestDTO = new UserRequestDTO();
    	String decodedName =  securityuserDetailService.decodeUserString(username);
    	userRequestDTO.setUsername(decodedName);
    	return userRequestDTO;
    }
    
    @RequestMapping(value="/v1/user/activate", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void activateAccount(@RequestParam  String activationLink) throws Exception{
    	securityuserDetailService.activateAccount(activationLink);
    }
    
    @RequestMapping(value = "/secured/v1/logout", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
           new SecurityContextLogoutHandler().logout(request, response, auth);
        }
      SecurityContextHolder.getContext().setAuthentication(null);
    }

    @ExceptionHandler(Exception.class)
	public Error handleGenericError(HttpServletRequest req, HttpServletResponse response, Exception exception){
		Error error = new Error();
		error.setErrorMessage("Please contact your system administrator");
		error.setErrorCode(response.SC_BAD_REQUEST);
		response.setStatus(response.SC_BAD_REQUEST);
		return error;
	}

}
