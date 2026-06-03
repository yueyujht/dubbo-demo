package com.yueyu.dubbo.service.impl;

import com.yueyu.dubbo.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@DubboService
public class UserServiceImpl implements UserService {
    public String getUser(){
        return "yueyu";
    }

    private static Map<String,List<String>> userMap = Map.of(
            "vip",List.of("望断南飞雁","秋霜染叶红","剑起花碎雪"),
            "normal", List.of("张三","李四","王五")
    );

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

    // CLIENT_STREAM
    @Override
    public StreamObserver<String> getUsernameByGrade(StreamObserver<String> response) {
         return new StreamObserver<String>() {
             @Override
             public void onNext(String data) {
                 System.out.println("服务端接收数据：" + data);
                 for(String user : userMap.get(data)){
                     System.out.println("服务端发送数据：" + user);
                     response.onNext(user);
                 }
             }

             @Override
             public void onError(Throwable throwable) {

             }

             @Override
             public void onCompleted() {
                 System.out.println("服务端结束发送");
                 response.onCompleted();
             }
         };
    }
}
