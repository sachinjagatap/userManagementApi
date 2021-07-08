package com.userManagementApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.userManagementApi.entities.User;
import com.userManagementApi.repository.UserRepo;


public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	         
		  User user = userRepo.findByuserName(username)
				      .orElseThrow(()-> new UsernameNotFoundException("User does not exists ..."));

		  return new UserDetailsImpl(user);
	}

}
