package com.yueyu.dubbo;

import org.apache.dubbo.common.stream.StreamObserver;

public interface UserService {
    // 调用模式-UNRAY  客户端->服务端  服务端->客户端
    public String getUser();

    // 调用模式-SERVER-STREAM  客户端->服务端  服务端=>客户端
    default void getUsers(String message, StreamObserver<String> response){};

    // 调用模式-CLIENT-STREAM  /  BI-STREAM  客户端=>服务端 服务端=>客户端
    default StreamObserver<String> getOrders(StreamObserver<String> response){
        return response;
    }
}
