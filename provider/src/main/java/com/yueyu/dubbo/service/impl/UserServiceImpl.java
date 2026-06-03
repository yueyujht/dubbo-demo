package com.yueyu.dubbo.service.impl;

import com.yueyu.dubbo.UserService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class UserServiceImpl implements UserService {
    public String getUser(){
        return "yueyu";
    }
}
