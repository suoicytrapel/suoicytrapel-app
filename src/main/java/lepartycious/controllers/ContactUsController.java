package lepartycious.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.ContactRequestDTO;
import lepartycious.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
		String mailContent = generateEnquiryMailContent(contactDetails);
		String mailTo = "lepartycious@gmail.com";
		String mailFrom = "no-reply@gmail.com";
		String mailSubject = "LePartycious : Enquiry Mail";
		emailService.sendMail(mailTo, mailFrom, mailSubject, mailContent);
	}
	
	private String generateEnquiryMailContent(ContactRequestDTO emailContent){
		StringBuffer sbf = new StringBuffer();
		sbf.append("Hi LePartycious Team,\n\nAn enquiry has been posted. Please find the details below :-\n");
		sbf.append("\nRequestor Name : " + emailContent.getName());
		sbf.append("\nRequestor Mobile : " + emailContent.getPhoneNumber());
		sbf.append("\nRequestor Email Address : " + emailContent.getEmail());
		sbf.append("\nRequestor Enquiry:" + emailContent.getMessage());
		sbf.append("\n\nThis is a system generated mail, no one is tracking this mail address.");
		sbf.append("\n\nCheers,\nLepartycious Team");
		return sbf.toString();
	}
	
	@ExceptionHandler(Exception.class)
	public Error handleGenericError(HttpServletRequest req, HttpServletResponse response, Exception exception){
		Error error = new Error();
		error.setErrorMessage("Please contact your system administrator");
		error.setErrorCode(response.SC_BAD_REQUEST);
		return error;
	}
}
