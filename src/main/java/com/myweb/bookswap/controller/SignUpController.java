package com.myweb.bookswap.controller;

import java.util.Arrays;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.myweb.bookswap.entity.Roles;
import com.myweb.bookswap.entity.User;
import com.myweb.bookswap.service.UserService;

@Controller
public class SignUpController {

	
	@Autowired
	UserService  userservice;
	
	
	@GetMapping("/signuppage")
	public String showsignUp(Model model)
	{
		
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUp(@ModelAttribute User user,Model model)
	{
		
		user.setUserno((int)userservice.count()+1);
		user.setEnabled(true);
		user.setRoles(Arrays.asList(new Roles("ROLE_USER")));
		
		userservice.save(user);
		
		return "signup";
	}
	
	
	
}
