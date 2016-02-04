package lepartycious.services;

import java.util.Map;

import lepartycious.dtos.requestDTOs.ContactRequestDTO;

public interface EmailService {
	
	public void sendMail(ContactRequestDTO emailContent);

}
