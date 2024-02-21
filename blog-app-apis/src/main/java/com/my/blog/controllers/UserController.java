package com.my.blog.controllers;

import com.my.blog.entities.User;
import com.my.blog.payloads.ApiResponse;
import com.my.blog.payloads.UserDto;
import com.my.blog.repository.UserRepo;
import com.my.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUsers")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto) {
        UserDto createUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateById/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto updatedUserDto, @PathVariable("userId") Integer uid) {
        UserDto updateUser = userService.updateUser(updatedUserDto, uid);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/deleteBy/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
        userService.deleteUser(uid);
        return new ResponseEntity<>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/getBy/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
