package com.my.blog.repository;

import com.my.blog.entities.User;
import com.my.blog.payloads.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
Optional<User> findByEmail(String email);
    void save(UserDto user);
}
