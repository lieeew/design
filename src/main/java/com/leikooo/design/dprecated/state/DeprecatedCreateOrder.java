package com.leikooo.design.dprecated.state;

import com.leikooo.design.utils.RedisCommonProcessor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class DeprecatedCreateOrder extends DeprecatedAbstractOrderState {

    @Resource
    private RedisCommonProcessor redisCommonProcessor;
    @Resource
    private DeprecatedPayOrder deprecatedOrderPay;
    @Override
    public DeprecatedOrder creatOrder(String orderId, String productId) {
        DeprecatedOrder order = DeprecatedOrder.builder()
                .orderId(orderId)
                .productId(productId)
                .state(ORDER_WAIT_PAY)
                .build();
        // 将新订单存入到 redis 之中，15 分钟后过期
        redisCommonProcessor.set(orderId, order, 900);
        super.notifyObserver(orderId, ORDER_WAIT_PAY);
        return order;
    }
}
