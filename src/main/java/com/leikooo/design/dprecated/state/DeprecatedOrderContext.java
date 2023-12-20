package com.leikooo.design.dprecated.state;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class DeprecatedOrderContext {
    // 新建订单的初始状态
    @Resource
    private DeprecatedCreateOrder deprecatedCreateOrder;
    private DeprecatedAbstractOrderState currentState;


    public DeprecatedOrder creatOrder(String orderId, String productId) {
        this.currentState = this.deprecatedCreateOrder;
        return currentState.creatOrder(orderId, productId, this);
    }

    public DeprecatedOrder payOrder(String orderId) {
        return currentState.payOrder(orderId, this);
    }
    public DeprecatedOrder sendOrder(String orderId) {
        return currentState.senderOrder(orderId, this);
    }

    public DeprecatedOrder receiveOrder(String orderId) {
        return currentState.receiveOrder(orderId, this);
    }
}
