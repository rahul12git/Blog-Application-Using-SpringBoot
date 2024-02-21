package com.my.blog.repository;

import com.my.blog.entities.User;
import com.my.blog.payloads.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    void save(UserDto user);
}
