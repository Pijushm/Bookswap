package com.myweb.bookswap.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myweb.bookswap.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long>{

	
	ConfirmationToken findByConfirmationToken(String token);
}
