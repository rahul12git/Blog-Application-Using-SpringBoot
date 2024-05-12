package com.my.blog.service.impl;

import com.my.blog.config.AppConstants;
import com.my.blog.entities.Role;
import com.my.blog.entities.User;
import com.my.blog.exceptions.ResourceNotFoundException;
import com.my.blog.payloads.UserDto;
import com.my.blog.repository.RoleRepo;
import com.my.blog.repository.UserRepo;
import com.my.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
List<User> users=this.userRepo.findAll();
List<UserDto> userDtos=users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
     User user= this.userRepo.findById(userId)
             .orElseThrow(() ->new ResourceNotFoundException("User", "Id", userId));

     user.setName(userDto.getName());
     user.setEmail(userDto.getEmail());
     user.setPassword(userDto.getPassword());
     user.setAbout(user.getAbout());
   User  updatedUser=this.userRepo.save(user);
   UserDto userDto1=this.userToDto(updatedUser);
        return userDto1 ;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(() ->new ResourceNotFoundException("User", "Id", userId));

      this.userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto) {

        User user= this.modelMapper.map(userDto, User.class);
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto= this.modelMapper.map(user,UserDto.class);
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {


        User  user = this.modelMapper.map(userDto,User.class);
//encode the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));


        //role

Role role =this.roleRepo.findById(AppConstants.NORMAL_USER).get();
user.getRoles().add(role);
User newUser=this.userRepo.save(user);

        return this.modelMapper.map(newUser,UserDto.class);
    }
}
