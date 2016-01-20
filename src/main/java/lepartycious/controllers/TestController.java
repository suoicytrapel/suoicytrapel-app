package lepartycious.controllers;

import java.util.List;

import lepartycious.models.Caterer;
import lepartycious.models.Venue;
import lepartycious.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/rest/v1/test")
public class TestController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(method=RequestMethod.GET, value="/venue")
	public String getVenue() {
		System.out.println("User Saved");
		List<Venue> ls = userService.getVenue();
		Venue v = ls.get(0);
		System.out.println("size is:" + v.getAttachments());
		return "save";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/caterer")
	public String getCaterer() {
		List<Caterer> ls = userService.getCaterer();
		return "save";
	}
	
}
