package com.myweb.bookswap.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.myweb.bookswap.dao.ConfirmationTokenRepository;
import com.myweb.bookswap.entity.ConfirmationToken;
import com.myweb.bookswap.entity.User;

@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {

	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailConfirmationService confirmservice;
	
	@Autowired
	ConfirmationTokenRepository tokenrepo;
	
	public void sendEmail(SimpleMailMessage message)
	{
		mailSender.send(message);
	}
	
	
	public void confirmRegistration(User user,String subject)
	{
		String token = UUID.randomUUID().toString();
		confirmservice.createConfirmationTokenforUser(user, token);
        
        String recipientAddress = user.getUemail();
        

        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("To confirm your account, please click here : "
                +"http://localhost:8081/registrationConfirm?token="+token);
        //change the message later(welcome message + clickable link)
        
        mailSender.send(email);
	}


	@Override
	public void createConfirmationTokenforUser(User user, String token) {
		  final ConfirmationToken userToken = new ConfirmationToken(token, user);
	       tokenrepo.save(userToken);
		
	}
	
	


	@Override
	public ConfirmationToken getConfirmationToken(String confirmationToken) {
		return tokenrepo.findByConfirmationToken(confirmationToken);
		
	}


	@Override
	public ConfirmationToken generateNewConfirmationTokenforUser(String token) {
		ConfirmationToken eToken=tokenrepo.findByConfirmationToken(token);
		
		eToken.setConfirmationToken(UUID.randomUUID()
	            .toString());
		return tokenrepo.save(eToken);
	}



	
}
