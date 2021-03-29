package com.myweb.bookswap.controller;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.myweb.bookswap.entity.ConfirmationToken;
import com.myweb.bookswap.entity.User;
import com.myweb.bookswap.service.EmailConfirmationService;
import com.myweb.bookswap.service.UserService;


@Controller
public class SignUpController {

	private static String authorizationRequestBaseUri = "oauth2/authorization";
	Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
	
	@Autowired
	UserService  userservice;
	
	@Autowired
	EmailConfirmationService confirmservice;
	
	
	


	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	
	@GetMapping("/signuppage")
	public String showsignUp(Model model)
	{
		
		Iterable<ClientRegistration> clientRegistrations = null;
		ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);
		// check if there is client configured
		if (type != ResolvableType.NONE && // after getting resolve type check if they are ClientRegistration type
				ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
			clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
		}

		clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(),
				authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
		
		model.addAttribute("facebookloginurl",oauth2AuthenticationUrls.get("Facebook"));
		model.addAttribute("googleloginurl", oauth2AuthenticationUrls.get("Google"));
		model.addAttribute("githubloginurl", oauth2AuthenticationUrls.get("GitHub"));
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUp(@ModelAttribute @Valid  User user,BindingResult result,Model model)
	{
	
		if(result.hasErrors())
		{
			return "signup";
		}
		
		user.setUserno((int)userservice.count()+1);
	//	user.setEnabled(true);
		
		userservice.save(user);
		confirmservice.confirmRegistration(user,"Registration Confirmation");
		
		
		model.addAttribute("emailId",user.getUemail());
	
		
		//return "home";
		return "RegSuccess";//choose business idea for this after visiting few sites
	}
	
	
	@GetMapping("/registrationConfirm")
	public RedirectView confirmRegistration(@RequestParam("token")String confirmationToken,RedirectAttributes attributes){
		
		
		 ConfirmationToken confToken = confirmservice.getConfirmationToken(confirmationToken);
	    if (confToken != null) {
	      //  String message = messages.getMessage("auth.message.invalidToken", null, locale);
	    	User user=confToken.getUser();
	    	
	    	Calendar cal = Calendar.getInstance();
	    	System.out.println(confToken.getExpiryDate().getTime());
	    	System.out.println(cal.getTime());
	    	System.out.println(cal.getTime().getTime());
	 	    if ((confToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	 	       // String messageValue = messages.getMessage("auth.message.expired", null, locale)
	 	       attributes.addFlashAttribute("message", "auth.message.expired");
	 	       attributes.addFlashAttribute("expired", true);
	 	       attributes.addFlashAttribute("token", confToken.getConfirmationToken());
	 	      
	 	      //  return "redirect:/badUser.html?lang=" + locale.getLanguage();
	 	       return new RedirectView("badUser");
	 	     // https://www.baeldung.com/spring-redirect-and-forward
	 	    } 
	    	user.setEnabled(true);
	    	userservice.update(user);
	    	String message="Account Verified";
	        attributes.addFlashAttribute("message", message);
	        return new RedirectView("/");
	    }
	    else
	    {
	    	 attributes.addFlashAttribute("message","The link is invalid or broken!");
	    	 return new RedirectView("error");
	    }
	   
	    
	}
	
	@GetMapping("/user/resendRegistrationToken")
	public String resendRegistrationToken(@RequestParam("token")String existingToken,Model model) {
		
		//
		 //use exceptionhandler
		//https://www.baeldung.com/spring-security-registration-verification-email
		ConfirmationToken newToken = confirmservice.generateNewConfirmationTokenforUser(existingToken);
		
		User user=newToken.getUser();
		confirmservice.confirmRegistration(user,"Resend Registration Confirmation");
		
		model.addAttribute("message", "Confirmation message sent again");
		return "signin";
		
	}
	
	@GetMapping("/badUser")
	public String UserConfirmFail(Model model) {
		
		model.addAttribute("expired", model.getAttribute("expired"));
		model.addAttribute("token", model.getAttribute("token"));
		return "badUser";
	}
	
	
	@GetMapping("/continueauthRegistration")
	public RedirectView ContinueAuthRegistration(HttpServletRequest request,Model model) {
		
		return new RedirectView("signupcontinue");
		
		
	}
	
	@GetMapping("/processauthRegistration")
	public RedirectView ProcessAuthRegistration(HttpServletRequest request,Model model) {
		
		return new RedirectView("signupcontinue");
		
		
	}
	
}
