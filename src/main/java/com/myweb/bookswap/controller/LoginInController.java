package com.myweb.bookswap.controller;


import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.myweb.bookswap.entity.PasswordResetToken;
import com.myweb.bookswap.entity.User;
import com.myweb.bookswap.service.PasswordResetTokenService;
import com.myweb.bookswap.service.UserService;





@Controller
public class LoginInController {

	@Autowired
	PasswordResetTokenService resetservice;

	@Autowired
	UserService userservice;


	@Autowired
	private PasswordEncoder passwordEncoder;


    @GetMapping("/signinpage")
    public String showLoginPage(Model model)
    {

        return "signin";
    }

    @GetMapping("/signin-error")//need to handle deliberate url submit
    public String showLoginPageError(Model model )
    {

    	model.addAttribute("loginerror","Invalid Username Or Password");
        return "signin";
    }


    @GetMapping("/forgotPass")
    public String ForgotPasswordPage(Model model)
    {

        return "forgotpassword";
    }


    @PostMapping("/sendresetPassword")
    public String sendPasswordResetToken(@RequestParam("userdetail")String userdetail , Model model,Authentication authentication)
    {
    	Optional<User> ouser=userservice.getByEmail(userdetail);

    	if(!ouser.isPresent())
    	{
    		model.addAttribute("tokensenderror",Boolean.TRUE);
    	    return "forgotpassword";
    	}
    	User user=ouser.get();
    	resetservice.SendPasswordResetToken(user);
    	model.addAttribute("resettokensend",Boolean.TRUE);
        return "resetpassword";
    }
    @GetMapping("resetpassword")
	public String showResetPasswordPage(@ModelAttribute("setnewpassword") String setnewpassword,Model model)
	{
        if(setnewpassword.equals("SET"))
        	model.addAttribute("showloginpage",Boolean.TRUE);
        else
        	return "redirect:/";
		return "resetpassword";
	}
    
    @GetMapping("/setPassword")
    public RedirectView GetNewPassword(@RequestParam("token") String token,RedirectAttributes attributes)
    {
    	
    	PasswordResetToken passtoken=resetservice.getPasswordToken(token);
    	if(passtoken!=null)
    	{
            User user=passtoken.getUser();
	    	
	    	Calendar cal = Calendar.getInstance();
			attributes.addFlashAttribute("token", passtoken.getToken());
	 	    if ((passtoken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	 	       // String messageValue = messages.getMessage("auth.message.expired", null, locale)
	 	       attributes.addFlashAttribute("message", "auth.message.expired");
	 	       attributes.addFlashAttribute("expired", true);

//				Invalid password reset link
//
//				The reset password link in your email seems to have expired.
				//  return "redirect:/badUser.html?lang=" + locale.getLanguage();
	 	       return new RedirectView("/");
	 	     
	 	    }
			attributes.addFlashAttribute("setnewpassword", "SET");
	    	return new RedirectView("resetpassword");
    	}
    	 else
 	    {
 	    	 attributes.addFlashAttribute("message","The link is invalid or broken!");
 	    	 return new RedirectView("error");
 	    }
    	

    	 
    	
    }


    @PostMapping("resetPassword")
	public String resetPassword(@RequestParam("token")String token,@RequestParam("password") String password,@RequestParam("confirmpassword")String confirmpassword,Model model)
	{
   //validate matching of password in javascript later

		Optional<User> ouser=resetservice.getUserByPasswordToken(token);
		if(!ouser.isPresent())
		{
			model.addAttribute("invalid link","Invalid password reset link");
			return "resetpassword";
		}
		else
		{
			if(!password.equals(confirmpassword))
			{
				model.addAttribute("reseterror","Password do not match");
				return "resetpassword";
			}

			User user=ouser.get();
			user.setPassword(passwordEncoder.encode(password));
			userservice.update(user);


			//token to be set to expired
			PasswordResetToken resetToken=resetservice.getExistingTokenofUser(user);
			Instant now = Instant.now(); //current date
			Instant before = now.minus(Duration.ofDays(1000));//1000 days before now,just random
			resetToken.setExpiryDate(Date.from(before));//set token to expired
            resetservice.update(resetToken);

			model.addAttribute("resetcomplete",Boolean.TRUE);
			model.addAttribute("resetmessage","Password reset Complete");
			return "resetpassword";

		}

	}


}
