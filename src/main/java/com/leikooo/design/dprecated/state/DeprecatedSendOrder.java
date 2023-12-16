package com.leikooo.design.dprecated.state;

import com.leikooo.design.utils.RedisCommonProcessor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class DeprecatedSendOrder extends DeprecatedAbstractOrderState {
    @Resource
    private RedisCommonProcessor redisCommonProcessor;
    @Resource
    private DeprecatedReceiveOrder deprecatedReceiveOrder;
    @Override
    public DeprecatedOrder senderOrder(String orderId, DeprecatedOrderContext context) {
        var order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_SEND)) {
            throw new UnsupportedOperationException("order state should be ORDER_WAIT_SEND not " + order.getState());
        }
        DeprecatedOrder updateOrder = DeprecatedOrder.builder()
                .orderId(orderId)
                .state(ORDER_WAIT_RECEIVE)
                .productId(order.getProductId())
                .build();
        context.setCurrentState(this.deprecatedReceiveOrder);
        redisCommonProcessor.set(orderId, updateOrder, 500);
        return updateOrder;
    }
}
