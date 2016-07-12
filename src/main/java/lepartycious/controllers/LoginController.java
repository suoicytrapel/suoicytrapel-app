package lepartycious.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest/v1")
public class LoginController {
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String getUser(){
		return "Mohit";
	}

}
