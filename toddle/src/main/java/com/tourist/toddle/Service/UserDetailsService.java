package com.tourist.toddle.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tourist.toddle.Models.User;
import com.tourist.toddle.Repository.UserRepo;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        if(user == null) throw new UsernameNotFoundException("No User with Email: " + username);
        
        List<GrantedAuthority> auths = new ArrayList();
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass(), auths);
    }

}
