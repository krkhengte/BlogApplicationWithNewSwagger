package jfs.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jfs.backend.entity.User;
import jfs.backend.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	
	@Autowired
	private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= this.userRepo.findByEmail(username).orElseThrow(()-> new RuntimeException("invalid username....!"));
        
        return user;
    }
}