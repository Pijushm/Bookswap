package com.myweb.bookswap.service;

import org.springframework.stereotype.Service;

import com.myweb.bookswap.entity.User;


public interface UserService {

	
	void save(User user);
	long count();
	
}
