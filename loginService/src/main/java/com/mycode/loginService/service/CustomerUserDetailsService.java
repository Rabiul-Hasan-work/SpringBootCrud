package com.mycode.loginService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mycode.loginService.entity.Customer;
import com.mycode.loginService.entity.CustomerUserDetails;
import com.mycode.loginService.repository.customerRepository;

public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private customerRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = repository.findByEmail(username);
		if (customer == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomerUserDetails(customer);
	}

}
