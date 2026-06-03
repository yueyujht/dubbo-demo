package com.yueyu.dubbo.service;

import com.yueyu.dubbo.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @DubboReference
    private UserService userService;

    public String getOrderWithRestTemplate(){
        String orderId = UUID.randomUUID().toString();
        String name = restTemplate.getForObject("http://localhost:8080/user",String.class);
        return "name: " + name + "  orderId: " + orderId;
    }

    public String getOrderWithDubbo(){
        String orderId = UUID.randomUUID().toString();
        String name = userService.getUser();
        return "name: " + name + "  orderId: " + orderId;
    }
}
