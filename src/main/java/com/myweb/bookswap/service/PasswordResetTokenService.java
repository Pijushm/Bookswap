package com.myweb.bookswap.service;

import com.myweb.bookswap.entity.PasswordResetToken;
import com.myweb.bookswap.entity.User;

public interface PasswordResetTokenService {

 PasswordResetToken createPasswordResetTokenForUser(User user);
 PasswordResetToken  getPasswordToken(String token);
 void SendPasswordResetToken(User user);
}
