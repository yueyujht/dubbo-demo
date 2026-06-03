package com.yueyu.dubbo.controller;

import com.yueyu.dubbo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user")
    public String getUser(){
        return userService.getUser();
    }
}
