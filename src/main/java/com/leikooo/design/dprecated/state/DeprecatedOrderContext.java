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
//    private DeprecatedAbstractOrderState currentState;
    @Resource
    private DeprecatedPayOrder deprecatedPayOrder;
    @Resource
    private DeprecatedReceiveOrder deprecatedReceiveOrder;
    @Resource
    private DeprecatedSendOrder deprecatedSendOrder;

    public DeprecatedOrder creatOrder(String orderId, String productId) {
        return deprecatedCreateOrder.creatOrder(orderId, productId);
    }

    public DeprecatedOrder payOrder(String orderId) {
        return deprecatedPayOrder.payOrder(orderId);
    }
    public DeprecatedOrder sendOrder(String orderId) {
        return deprecatedSendOrder.senderOrder(orderId);
    }

    public DeprecatedOrder receiveOrder(String orderId) {
        return deprecatedReceiveOrder.receiveOrder(orderId);
    }
}
