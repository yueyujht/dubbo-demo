package com.yueyu.dubbo.service;

import com.yueyu.dubbo.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @DubboReference
    private UserService userService;

    // unary
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

    public List<String> getOrderBySERVERSTREAM(){
        List<String> messages = new ArrayList<>();
        userService.getUsers("vip", new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                String orderId = UUID.randomUUID().toString();
                messages.add("name: " + data + "  orderId: " + orderId);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                messages.add("调用结束！");
            }
        });
        return messages;
    }

    public List<String> getOrdersByGrade() {
        List<String> orders = new ArrayList<>();
        CompletableFuture<Void> future = new CompletableFuture<>();
        StreamObserver<String> sink = userService.getUsernameByGrade(new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("客户端接收数据：" + data);
                String orderId = UUID.randomUUID().toString();
                orders.add("name: " + data + "  orderId: " + orderId);
                System.out.println(orders);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                future.complete(null);
            }
        });
        // 发送信息给服务端
        sink.onNext("vip");
        sink.onCompleted();
        future.join();
        return orders;
    }
}
