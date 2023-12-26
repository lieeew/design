package com.leikooo.design.controller;

import com.leikooo.design.pojo.Order;
import com.leikooo.design.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/creat")
    public Order creatOrder(@RequestParam String productId) {
        return orderService.creatOrder(productId);
    }

    @PostMapping("/pay")
    public Order pay(@RequestParam String orderId) {
        return orderService.pay(orderId);
    }

    @PostMapping("/send")
    public Order send(@RequestParam String orderId) {
        return orderService.send(orderId);
    }

    @PostMapping("/receive")
    public Order receive(@RequestParam String orderId) {
        return orderService.receive(orderId);
    }
}
