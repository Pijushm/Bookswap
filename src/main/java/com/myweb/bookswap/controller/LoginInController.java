package com.myweb.bookswap.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class LoginInController {

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
    
}
