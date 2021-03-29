package com.myweb.bookswap.dao;

import com.myweb.bookswap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myweb.bookswap.entity.PasswordResetToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long>  {

	PasswordResetToken findBytoken(String token);
	PasswordResetToken findByuser(User user);


}
