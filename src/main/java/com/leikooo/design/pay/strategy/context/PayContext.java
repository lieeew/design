package com.leikooo.design.pay.strategy.context;

import com.leikooo.design.pay.strategy.PayStrategyInterface;
import com.leikooo.design.pojo.Order;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class PayContext {
    // 关联支付策略
    private PayStrategyInterface payStrategy;

    public PayContext (PayStrategyInterface payStrategy) {
        this.payStrategy = payStrategy;
    }

    public String execute(Order order) {
        return this.payStrategy.pay(order);
    }
}
