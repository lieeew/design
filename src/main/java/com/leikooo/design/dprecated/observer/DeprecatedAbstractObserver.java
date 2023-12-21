package com.leikooo.design.dprecated.observer;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description 抽象观察者类，用于处理订单状态的变化
 */
public abstract class DeprecatedAbstractObserver {

    /**
     * 当订单状态发生改变时，调用此方法
     *
     * @param orderId    订单ID
     * @param orderState 订单状态
     */
    public abstract void orderStateHandle(String orderId, String orderState);
}
