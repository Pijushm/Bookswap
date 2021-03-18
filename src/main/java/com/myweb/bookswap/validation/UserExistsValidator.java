package com.myweb.bookswap.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.myweb.bookswap.service.UserService;

public class UserExistsValidator implements ConstraintValidator<UserExists,String> {

	
	@Autowired
	UserService userservice;
	
	@Override
	public boolean isValid(String userIdgiven, ConstraintValidatorContext context) {
		
		
		return !userservice.get(userIdgiven).isPresent();
		
		
	}

}
