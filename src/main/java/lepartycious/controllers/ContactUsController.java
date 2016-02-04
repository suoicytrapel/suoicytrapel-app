package lepartycious.controllers;

import lepartycious.dtos.requestDTOs.ContactRequestDTO;
import lepartycious.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest/v1/contactus")
public class ContactUsController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public void sendMessage(@RequestBody ContactRequestDTO contactDetails){
		emailService.sendMail(contactDetails);
	}
	

}
