package com.yueyu.dubbo.service.impl;

import com.yueyu.dubbo.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class UserServiceImpl implements UserService {
    public String getUser(){
        return "yueyu";
    }

    // SERVER-STREAM
    @Override
    public void getUsers(String message, StreamObserver<String> response) {
        // message从客户端传来，服务端处理该信息，通过response返回结果
        if("vip".equals(message)){
            response.onNext("月屿（vip）");
        } else{
            response.onNext("张三（普通）");
            response.onNext("李四（普通）");
            response.onNext("王五（普通）");
        }

        // 结束调用
        response.onCompleted();
    }
}
