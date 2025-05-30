package com.g4pro.LogParser.repository;

import com.g4pro.LogParser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
