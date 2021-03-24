package com.myweb.bookswap.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myweb.bookswap.entity.ConfirmationToken;
import com.myweb.bookswap.entity.User;


public interface UserService {

	
	void save(User user);
	Optional<User> get(String userId);
	Optional<User> getByEmail(String email);
	long count();
	
	
	
}
