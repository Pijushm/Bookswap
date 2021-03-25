package com.myweb.bookswap.controller;


import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    
    @GetMapping("/setPassword")
    public RedirectView GetNewPassword(@RequestParam("token") String resettoken,RedirectAttributes attributes)
    {
    	
    	PasswordResetToken passtoken=resetservice.getPasswordToken(resettoken);
    	if(passtoken!=null)
    	{
            User user=passtoken.getUser();
	    	
	    	Calendar cal = Calendar.getInstance();
	    
	 	    if ((passtoken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	 	       // String messageValue = messages.getMessage("auth.message.expired", null, locale)
	 	       attributes.addFlashAttribute("message", "auth.message.expired");
	 	       attributes.addFlashAttribute("expired", true);
	 	       attributes.addFlashAttribute("token", passtoken.getToken());
	 	      
	 	      //  return "redirect:/badUser.html?lang=" + locale.getLanguage();
	 	       return new RedirectView("badUser");
	 	     
	 	    } 
	    	user.setEnabled(true);
	    	userservice.update(user);
    	}
    	 else
 	    {
 	    	 attributes.addFlashAttribute("message","The link is invalid or broken!");
 	    	 return new RedirectView("error");
 	    }
    	
    	return new RedirectView("resetpassword");
    	 
    	
    }
    
    
//    @PostMapping("/resetPassword")
//    {
//    	
//    }
    
    
}
