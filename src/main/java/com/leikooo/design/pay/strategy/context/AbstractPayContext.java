package com.leikooo.design.pay.strategy.context;

import com.leikooo.design.pojo.Order;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public abstract class AbstractPayContext {
    public abstract String execute(Order order);
}
