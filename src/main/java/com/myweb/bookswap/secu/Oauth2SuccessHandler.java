package com.myweb.bookswap.secu;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myweb.bookswap.entity.BookSwapOauth2User;
import com.myweb.bookswap.entity.User;
import com.myweb.bookswap.service.UserService;

public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserService userservice;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		BookSwapOauth2User oauthUser = (BookSwapOauth2User) authentication.getPrincipal();

//        System.out.println(oauthUser.getName());
//        System.out.println(oauthUser.getEmail());
//        System.out.println(oauthUser.getAttributes());
//        
		String user_fullname = oauthUser.getName();
		String user_email = oauthUser.getEmail();

		// set database operation to save

		Optional<User> user = userservice.getByEmail(user_email);

		
		if(!user.isPresent())
		{   HttpSession session=request.getSession();
		    session.setAttribute("newusername", user_fullname);
		    session.setAttribute("newuemail", user_email);
			
		    request.getRequestDispatcher("/continueauthRegistration").forward(request, response);
			
		}
		else {
			
			response.sendRedirect("/");
		}
		
		
//		request.setAttribute("username", user_fullname);
//		request.setAttribute("uemail", user_email);
//		request.getRequestDispatcher("/processauthRegistration").forward(request, response);
		// response.sendRedirect(location);

		// check if exists
		// if exists ---> login
		// if not exists
		// get username
		// get district
		// save

		// login
		// show welcome message

		//

	}

}
