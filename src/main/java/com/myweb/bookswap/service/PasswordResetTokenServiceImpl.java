package com.myweb.bookswap.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.myweb.bookswap.dao.PasswordTokenRepository;
import com.myweb.bookswap.entity.PasswordResetToken;
import com.myweb.bookswap.entity.User;


@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {


    @Autowired
    PasswordTokenRepository tokenrepo;


    @Autowired
    private JavaMailSender mailSender;


    @Override
    public PasswordResetToken createPasswordResetTokenForUser(User user) {
        String generatedToken = UUID.randomUUID().toString();
        PasswordResetToken passtoken = getExistingTokenofUser(user);

        if (passtoken == null)
            passtoken = new PasswordResetToken(generatedToken, user);
        else
            passtoken.setToken(generatedToken);
        tokenrepo.save(passtoken);
        return passtoken;
    }


    @Override
    public PasswordResetToken getPasswordToken(String token) {
        return tokenrepo.findBytoken(token);
    }


    @Override
    public void SendPasswordResetToken(User user) {


        PasswordResetToken passtoken = createPasswordResetTokenForUser(user);

        String recipientAddress = user.getUemail();
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject("Password Reset");
        email.setText("To Reset you password, please click here : "
                + "http://localhost:8081/setPassword?token=" + passtoken.getToken());
        //change the message later(welcome message + clickable link)

        mailSender.send(email);

    }


    @Override
    public Optional<User> getUserByPasswordToken(String token) {
        return Optional.ofNullable(tokenrepo.findBytoken(token).getUser());
    }


    @Override
    public PasswordResetToken getExistingTokenofUser(User user) {
        return tokenrepo.findByuser(user);
    }

    @Override
    public void update(PasswordResetToken token) {
        tokenrepo.save(token);
    }
}
