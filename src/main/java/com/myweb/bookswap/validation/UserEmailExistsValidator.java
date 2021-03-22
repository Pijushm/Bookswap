package com.myweb.bookswap.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.myweb.bookswap.service.UserService;

public class UserEmailExistsValidator implements ConstraintValidator<UserEmailExists,String> {

	@Autowired
	UserService userservice;
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		
		
		return !userservice.getByEmail(email).isPresent();
	}
	
	

}
