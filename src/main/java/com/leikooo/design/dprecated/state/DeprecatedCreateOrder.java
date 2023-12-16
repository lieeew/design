package com.leikooo.design.dprecated.state;

import com.leikooo.design.utils.RedisCommonProcessor;
import jakarta.annotation.Resource;
import org.springframework.expression.spel.ast.OpOr;
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
    private DeprecatedOrderPay deprecatedOrderPay;
    @Override
    public DeprecatedOrder creatOrder(String orderId, String productId, DeprecatedOrderContext context) {
        DeprecatedOrder order = DeprecatedOrder.builder()
                .orderId(orderId)
                .productId(productId)
                .state(ORDER_WAIT_PAY)
                .build();
        // 将新订单存入到 redis 之中，15 分钟后过期
        redisCommonProcessor.set(orderId, order, 900);
        // 订单创建完成，设置 context 上下文角色的 CurrentState 为待支付状态
        context.setCurrentState(this.deprecatedOrderPay);
        return order;
    }
}
