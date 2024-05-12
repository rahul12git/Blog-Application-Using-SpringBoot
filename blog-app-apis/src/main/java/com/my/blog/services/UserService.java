package com.my.blog.services;

import com.my.blog.entities.User;
import com.my.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto user);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById ( Integer userId);

List<UserDto> getAllUser();
    void deleteUser(Integer userid);
}

