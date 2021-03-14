package com.myweb.bookswap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.myweb.bookswap.dao.UserRepository;
import com.myweb.bookswap.entity.Roles;
import com.myweb.bookswap.entity.User;


@Service
public class UserServiceImpl implements UserService {

	    @Autowired
	    UserRepository userrepo;

		@Override
		public void save(User user) {
			
			//use custom query to save roles
			user.setPassword("{noop}"+user.getPassword());
			List<Roles> roles=new ArrayList<>();
		    Roles role=new Roles("ROLE_USER");
		    role.setBuser(user);
		    roles.add(role);
			user.setRoles(roles);
			userrepo.save(user);
			
		}
		
	
    
		@Override
		public long count() {
			// TODO Auto-generated method stub
			return userrepo.count();
		}

       

}
