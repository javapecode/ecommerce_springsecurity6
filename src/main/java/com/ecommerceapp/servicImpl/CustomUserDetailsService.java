package com.ecommerceapp.servicImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerceapp.entity.User;
import com.ecommerceapp.resitory.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	 @Autowired
	    private UserRepository userRepo;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepo.findByEmail(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        return new CustomUserDetails(user);
	    }
}
