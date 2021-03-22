package com.myweb.bookswap.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValidatePassword {

	String message() default "Passwords do not match";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	
}
