package com.leikooo.design.pay.face;

import com.leikooo.design.pay.strategy.context.PayContext;
import com.leikooo.design.pay.strategy.factory.PayContextFactory;
import com.leikooo.design.pojo.Order;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class PayFace {
    @Resource
    private PayContextFactory contextFactory;

    public String pay(Order order, Integer payType) {
        PayContext context = contextFactory.getContext(payType);
        return context.execute(order);
    }
}
