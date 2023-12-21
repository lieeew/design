package com.leikooo.design.dprecated.state;

import com.leikooo.design.utils.RedisCommonProcessor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class DeprecatedReceiveOrder extends DeprecatedAbstractOrderState {
    @Resource
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    public DeprecatedOrder receiveOrder(String orderId) {
        var order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_RECEIVE)) {
            throw new UnsupportedOperationException("order state should be ORDER_WAIT_RECEIVE not" + order.getState());
        }
        DeprecatedOrder updateOrder = DeprecatedOrder.builder()
                .orderId(orderId)
                .state(ORDER_FINISH)
                .build();
        super.notifyObserver(orderId, ORDER_FINISH);
        redisCommonProcessor.remove(orderId);
        return updateOrder;
    }
}
