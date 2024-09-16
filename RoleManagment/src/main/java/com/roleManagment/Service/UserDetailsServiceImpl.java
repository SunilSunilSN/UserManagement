package com.roleManagment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.roleManagment.Entity.MyUserDetails;
import com.roleManagment.Entity.User;
import com.roleManagment.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }
    
}
