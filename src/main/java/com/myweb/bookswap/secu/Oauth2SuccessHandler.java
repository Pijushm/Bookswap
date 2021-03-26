package com.myweb.bookswap.secu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myweb.bookswap.entity.BookSwapOauth2User;

public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		BookSwapOauth2User   oauthUser = (BookSwapOauth2User) authentication.getPrincipal();
		 
        System.out.println(oauthUser.getName());
        System.out.println(oauthUser.getEmail());
        System.out.println(oauthUser.getAttributes());
        //set database operation to save

        response.sendRedirect("/");
		
	}

}
