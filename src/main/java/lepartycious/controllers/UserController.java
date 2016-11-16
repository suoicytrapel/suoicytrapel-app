package lepartycious.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.UserRequestDTO;
import lepartycious.services.SecurityUserDetailsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value="/secured/v1/getUserDetails", method=RequestMethod.GET)
	public UserRequestDTO getLoggedInUser(@RequestParam String userType, @RequestParam Boolean isAppUser) throws Exception{
    	UserRequestDTO user = securityuserDetailService.getLoggedInUser(userType, isAppUser);
		return user;
	}
    
    @RequestMapping(value="/v1/user/forgotPassword/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void forgotPassword(@PathVariable String username) throws Exception{
    	securityuserDetailService.forgotPassword(username);
    }
    
    @RequestMapping(value="/v1/user/resetPassword", method = RequestMethod.POST)
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
    
    @RequestMapping(value="/v1/user/decodeString/{username}", method = RequestMethod.GET)
    public String decodeUserString(@PathVariable  String username) throws Exception{
    	return securityuserDetailService.decodeUserString(username);
    }
    
    @RequestMapping(value="/v1/user/activate", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void activateAccount(@RequestParam  String activationLink) throws Exception{
    	securityuserDetailService.activateAccount(activationLink);
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
