package com.vestigershop.Registration.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vestigershop.Registration.Data.User;
import com.vestigershop.Registration.exception.RegistrationException;
import com.vestigershop.Registration.repository.UserDetailsRepo;
import com.vestigershop.Registration.service.UserDetailsServiceCustom;
import com.vestigershop.Registration.utils.PasswordUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsServiceCustom,UserDetailsService {

	@Autowired
	private UserDetailsRepo userDetails; 
	@Override
	public List<User> getAllUser() throws RegistrationException{
		return userDetails.findAll();
	}
	@Override
	public void createUser(User details) throws RegistrationException{
		
		 String myPassword = details.getPassword();
	        
	        // Generate Salt. The generated value can be stored in DB. 
	        String salt = PasswordUtils.getSalt(30);
	        
	        // Protect user's password. The generated value can be stored in DB.
	        String mySecurePassword = PasswordUtils.generateSecurePassword(myPassword, salt);
	        
	        // Print out protected password 
	        System.out.println("My secure password = " + mySecurePassword);
	        System.out.println("Salt value = " + salt);
	        details.setPassword(mySecurePassword);
		  userDetails.save(details);
		
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDetails.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

}
