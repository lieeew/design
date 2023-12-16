package com.leikooo.design.dprecated.state;

import com.leikooo.design.utils.RedisCommonProcessor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class DeprecatedOrderPay extends DeprecatedAbstractOrderState {
    @Resource
    private RedisCommonProcessor redisCommonProcessor;
    @Resource
    private DeprecatedSendOrder deprecatedSendOrder;
    @Override
    public DeprecatedOrder payOrder(String orderId, DeprecatedOrderContext context) {
        var order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_PAY)) {
            throw new UnsupportedOperationException("order state should be ORDER_WAIT_PAY not" + order.getState());
        }
        DeprecatedOrder updateOrder = DeprecatedOrder.builder()
                .state(ORDER_WAIT_SEND)
                .productId(order.getProductId())
                .orderId(order.getOrderId())
                .build();
        redisCommonProcessor.set(orderId, updateOrder, 500);
        context.setCurrentState(this.deprecatedSendOrder);
        return updateOrder;
    }
}
