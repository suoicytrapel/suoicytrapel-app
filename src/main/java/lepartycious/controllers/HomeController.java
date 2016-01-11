package lepartycious.controllers;

import lepartycious.models.User;
import lepartycious.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class HomeController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/getUser/{username}", method=RequestMethod.GET)
	public String delete(@PathVariable String username) {
		User user = userService.loadUserByUsername(username);
		return user.getName();
	}

}
