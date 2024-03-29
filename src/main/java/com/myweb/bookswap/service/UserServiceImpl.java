package com.myweb.bookswap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myweb.bookswap.dao.UserRepository;
import com.myweb.bookswap.entity.Roles;
import com.myweb.bookswap.entity.User;


@Service
public class UserServiceImpl implements UserService {

	    @Autowired
	    UserRepository userrepo;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;

		@Override
		public void save(User user){//check if you can add throw useralready exists exception here and add it with binding result
			//rather than while validating
			
			//use custom query to save roles
			//user.setPassword("{noop}"+user.getPassword());


			user.setPassword(passwordEncoder.encode(user.getPassword()));
			List<Roles> roles=new ArrayList<>();
		    Roles role=new Roles("ROLE_USER");
		    role.setBuser(user);
		    roles.add(role);
			user.setRoles(roles);
			user.setLoginuserid(user.getLoginuserid());
			userrepo.save(user);
			
		}

	@Override
	public void saveAuthUser(User user) {
		user.setPassword("NotGiven");
		List<Roles> roles=new ArrayList<>();
		Roles role=new Roles("ROLE_USER");
		role.setBuser(user);
		roles.add(role);
		user.setRoles(roles);
		userrepo.save(user);

	}


	@Override
		public void update(User user) {
		   userrepo.save(user);
		}

		
	
    
		@Override
		public long count() {
			// TODO Auto-generated method stub
			return userrepo.count();
		}



		@Override
		public Optional<User> get(String userId) {
			return userrepo.findById(userId);
			
			
		}



		@Override
		public Optional<User> getByEmail(String email) {
			
			return userrepo.findByUemail(email.toLowerCase());
			
		}



		@Override
		public Optional<User> getCurrentUser(Authentication authentication) {
		
			String currentUserName="";
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
			     currentUserName = authentication.getName();
			    
			}
			
			return userrepo.findById(currentUserName);
			
		}



		@Override
		public Optional<User> getCurrentUser() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return getCurrentUser(authentication);
		}


        

	


		

		
		
       

}
