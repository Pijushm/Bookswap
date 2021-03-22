package com.myweb.bookswap.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = {UserExistsValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UserIdExists {
	
	String message() default "Sorry,Username Already Taken";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	

}
