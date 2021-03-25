package com.myweb.bookswap.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myweb.bookswap.dao.UserRepository;
import com.myweb.bookswap.entity.BookSwapUserDetails;
import com.myweb.bookswap.entity.Roles;
import com.myweb.bookswap.entity.User;


@Service
public class BookswapUserDetailService implements UserDetailsService {

	 @Autowired
	 UserRepository userrepo;
	
	@Override                             //username=email or userid
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> u=userrepo.findByUseridOrUemail(username,username);
		
		if(!u.isPresent())
			throw new UsernameNotFoundException("No user found with username: "+ username);
		
		User user=u.get();
		List<Roles> roles=userrepo.getUserRoles(user.getUserid());
		
		user.setRoles(roles);
       
		return new BookSwapUserDetails(user);
        
       
	}
	
    
	

}
