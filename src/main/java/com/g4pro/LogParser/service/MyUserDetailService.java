package com.g4pro.LogParser.service;

import com.g4pro.LogParser.entity.User;
import com.g4pro.LogParser.model.UserPrincipal;
import com.g4pro.LogParser.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            System.out.println("User 404");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}
