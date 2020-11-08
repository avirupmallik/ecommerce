package com.vestigershop.Registration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vestigershop.Registration.Data.User;
import com.vestigershop.Registration.exception.RegistrationException;

public interface UserDetailsServiceCustom {

	public List<User> getAllUser() throws RegistrationException;
	
	public void createUser(User details) throws RegistrationException;
	
	//public UserDetails loadByUSerName(String username) throws UsernameNotFoundException;
	
}
