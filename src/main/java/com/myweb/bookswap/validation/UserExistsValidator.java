package com.myweb.bookswap.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.myweb.bookswap.entity.User;
import com.myweb.bookswap.service.UserService;

public class UserExistsValidator implements ConstraintValidator<UserIdExists,String> {

	
	@Autowired
	UserService userservice;
	
	String message;
	
	@Override
	public boolean isValid(String userIdgiven, ConstraintValidatorContext context) {
		
		
//        context.disableDefaultConstraintViolation();
//		
//		if(userservice.get(user.getUserid()).isPresent())
//		   message="Match UserId";
//		else
//		   message="Match Email"
//	    context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
//		
//		return !userservice.get(userIdgiven).isPresent();
		
		return !userservice.get(userIdgiven.trim()).isPresent();
		
		
	}
	

}
