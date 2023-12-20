package com.leikooo.design.dprecated.observer;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public abstract class DeprecatedAbstractObserver {
    // 状态发生改变调用此方法
    public abstract void orderStateHandle(String orderId, String orderState);
}
