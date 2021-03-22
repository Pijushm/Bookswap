package com.myweb.bookswap.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class BookSwapUserDetails implements UserDetails { //implement in User class

	
	 private String username;
	 private String password;
	 private boolean active;
	 private List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
	
	 
	 
	 
	public BookSwapUserDetails(User user) {
		
		 this.username = user.getUserid();
	     this.password = user.getPassword();
	     this.active = user.isEnabled();
	     
	     for(Roles role:user.getRoles())
	     {
	    	 this.authorities.add(new SimpleGrantedAuthority(role.getRole()));
	     }
	     
	     System.out.println(this.authorities);
		 
	     
	
	}
	        
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	   return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
