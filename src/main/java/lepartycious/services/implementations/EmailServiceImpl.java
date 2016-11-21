package lepartycious.services.implementations;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lepartycious.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	@Override
	@Async
	public void sendMail(final String mailTo, final String mailFrom, final String mailSubject,
			final String mailContent) {
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {  
            
            public void prepare(MimeMessage mimeMessage) throws Exception {  
               mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(mailTo));  
               mimeMessage.setFrom(new InternetAddress(mailFrom));  
               mimeMessage.setSubject(mailSubject);  
               mimeMessage.setContent(mailContent, "text/html");
            }  
		};  
		javaMailSender.send(messagePreparator);  
	}
	
}
