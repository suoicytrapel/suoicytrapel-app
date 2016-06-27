package lepartycious.services.implementations;

import lepartycious.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	@Autowired
	private SimpleMailMessage custommessage;

	@Override
	@Async
	public void sendMail(String mailTo, String mailFrom, String mailSubject,
			String mailContent) {
		SimpleMailMessage message = new SimpleMailMessage(custommessage);
		message.setFrom(mailFrom);
		message.setTo(mailTo);
		message.setSubject(mailSubject);
		message.setText(mailContent);
		javaMailSender.send(message);
	}
	
}
