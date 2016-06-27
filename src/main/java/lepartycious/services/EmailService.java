package lepartycious.services;

public interface EmailService {
	
	public void sendMail(String mailTo, String mailFrom, String mailSubject,
			String mailContent);

}
