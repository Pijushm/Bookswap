package com.myweb.bookswap.service;

import com.myweb.bookswap.entity.PasswordResetToken;
import com.myweb.bookswap.entity.User;

import java.util.Optional;

public interface PasswordResetTokenService {

 PasswordResetToken createPasswordResetTokenForUser(User user);
 PasswordResetToken  getPasswordToken(String token);
 PasswordResetToken  getExistingTokenofUser(User user);
 Optional<User>   getUserByPasswordToken(String token);
 void SendPasswordResetToken(User user);
 void update(PasswordResetToken token);

}
