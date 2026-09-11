package com.learnspring.learn_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnspring.learn_spring_boot.Users;
import com.learnspring.learn_spring_boot.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationManager authManager;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user){
        return userService.registerUser(user);
    }
    @PostMapping("/userlogin")
    public String loginUser(@RequestBody  Users user){
        return userService.verify(user,authManager);
    }

}
