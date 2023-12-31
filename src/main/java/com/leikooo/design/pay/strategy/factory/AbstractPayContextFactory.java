package com.leikooo.design.pay.strategy.factory;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public abstract class AbstractPayContextFactory<T> {
    public abstract T getContext(Integer payType);
}
