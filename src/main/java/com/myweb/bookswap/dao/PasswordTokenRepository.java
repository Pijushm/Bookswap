package com.myweb.bookswap.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myweb.bookswap.entity.PasswordResetToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long>  {

	PasswordResetToken findBytoken(String token);

}
