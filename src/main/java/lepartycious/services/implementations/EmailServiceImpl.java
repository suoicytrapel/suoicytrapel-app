package lepartycious.services.implementations;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import lepartycious.dtos.requestDTOs.ContactRequestDTO;
import lepartycious.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	@Autowired
	private SimpleMailMessage custommessage;

	@Override
	public void sendMail(ContactRequestDTO emailContent) {
		SimpleMailMessage message = new SimpleMailMessage(custommessage);
		message.setFrom("no-reply@gmail.com");
		message.setTo("mohitsingla2256@gmail.com");
		message.setSubject("LePartycious : Enquiry Mail");
		message.setText(generateMailContent(emailContent));
		javaMailSender.send(message);
	}
	
	private String generateMailContent(ContactRequestDTO emailContent){
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

}
