package com.myweb.bookswap.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.myweb.bookswap.entity.User;


public interface UserService {

	
	void save(User user);
	Optional<User> getCurrentUser(Authentication authentication);
	Optional<User> getCurrentUser();
	void update(User user);
	Optional<User> get(String userId);
	Optional<User> getByEmail(String email);
	long count();
	
	
	
}
