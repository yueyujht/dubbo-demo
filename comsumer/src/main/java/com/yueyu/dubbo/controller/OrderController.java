package com.yueyu.dubbo.controller;

import com.yueyu.dubbo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String getOrder(){
        return orderService.getOrderWithDubbo();
    }
}
