package com.leikooo.design.pay.face;

import com.leikooo.design.pay.strategy.AlipayStrategy;
import com.leikooo.design.pay.strategy.WechatPayStrategy;
import com.leikooo.design.pay.strategy.context.PayContext;
import com.leikooo.design.pojo.Order;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class PayFace {
    public String pay(Order order, Integer payType) {
        switch (payType) {
            case 1 -> {
                AlipayStrategy alipayStrategy = new AlipayStrategy();
                PayContext alipayContext = new PayContext(alipayStrategy);
                return alipayContext.execute(order);
            }
            case 2 -> {
                WechatPayStrategy wechatPayStrategy = new WechatPayStrategy();
                PayContext wechatPayContext = new PayContext(wechatPayStrategy);
                return wechatPayContext.execute(order);
            }
            default -> throw new UnsupportedOperationException("PayType not support!");
        }
    }
}
