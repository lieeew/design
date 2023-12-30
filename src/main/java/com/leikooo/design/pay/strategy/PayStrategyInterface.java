package com.leikooo.design.pay.strategy;

import com.leikooo.design.pojo.Order;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public interface PayStrategyInterface {
    // 定义公共的支付方法
    String pay(Order order);
}
