package com.g4pro.LogParser.service;

import com.g4pro.LogParser.entity.User;
import com.g4pro.LogParser.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<?> registerUser(User user) throws Exception {
        User user1 = userRepo.findByUsername(user.getUsername());
        if(user1 != null){
            throw new Exception("User Already Exists");
        }
        userRepo.save(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

}
