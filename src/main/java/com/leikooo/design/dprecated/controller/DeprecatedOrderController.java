package com.leikooo.design.dprecated.controller;

import com.leikooo.design.dprecated.service.DeprecatedOrderService;
import com.leikooo.design.dprecated.state.DeprecatedOrder;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.RasterFormatException;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@RestController
@RequestMapping("/deprecated/order")
public class DeprecatedOrderController {
    @Resource
    private DeprecatedOrderService deprecatedOrderService;

    @PostMapping("/creat")
    public DeprecatedOrder creatOrder(@RequestParam(name = "productId") String productId) {
        return deprecatedOrderService.creatOrder(productId);
    }

    @PostMapping("/pay")
    public DeprecatedOrder payOrder(@RequestParam String orderId) {
        return deprecatedOrderService.pay(orderId);
    }
    @PostMapping("/send")
    public DeprecatedOrder send(@RequestParam String orderId) {
        return deprecatedOrderService.pay(orderId);
    }

    @PostMapping("/receive")
    public DeprecatedOrder receive(@RequestParam String orderId) {
        return deprecatedOrderService.receive(orderId);
    }
}
