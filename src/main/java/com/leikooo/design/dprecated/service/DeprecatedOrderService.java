package com.leikooo.design.dprecated.service;

import com.leikooo.design.dprecated.state.DeprecatedOrder;
import com.leikooo.design.dprecated.state.DeprecatedOrderContext;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Service
public class DeprecatedOrderService {
    @Resource
    private DeprecatedOrderContext orderContext;

    public DeprecatedOrder creatOrder(String productId) {
        // 订单 ID
        // 数据库、随机 UUID 、Redis 生成全局 ID 、雪花算法、Zookeeper ......
        String orderId = "OID" + productId;
        return orderContext.creatOrder(orderId, productId);
    }

    public DeprecatedOrder pay(String orderId) {
        return orderContext.payOrder(orderId);
    }

    public DeprecatedOrder send(String orderId) {
        return orderContext.sendOrder(orderId);
    }

    public DeprecatedOrder receive(String orderId) {
        return orderContext.receiveOrder(orderId);
    }

}
