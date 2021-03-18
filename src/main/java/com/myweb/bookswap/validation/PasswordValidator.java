package com.myweb.bookswap.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.myweb.bookswap.entity.User;

public class PasswordValidator implements ConstraintValidator<ValidatePassword,User> {

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
	
		if(user.getConfirmpassword().equals(""))
			return true;
		return user.getPassword().equals(user.getConfirmpassword());
	}

	

}
