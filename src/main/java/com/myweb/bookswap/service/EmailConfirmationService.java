package com.myweb.bookswap.service;

import org.springframework.mail.SimpleMailMessage;

import com.myweb.bookswap.entity.ConfirmationToken;
import com.myweb.bookswap.entity.User;

public interface EmailConfirmationService {

	void sendEmail(SimpleMailMessage message);
	void confirmRegistration(User user,String subject);
	void createConfirmationTokenforUser(User user,String token);
	ConfirmationToken getConfirmationToken(String confirmationToken); 
	ConfirmationToken generateNewConfirmationTokenforUser(String token);
	
}
