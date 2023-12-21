package com.leikooo.design.dprecated.observer;

import com.leikooo.design.dprecated.DeprecatedConstants;
import jakarta.annotation.PostConstruct;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class DeprecatedPayObserver extends DeprecatedAbstractObserver {
    @PostConstruct
    public void init() {
        DeprecatedConstants.OBSERVER_LIST.add(this);
    }

    @Override
    public void orderStateHandle(String orderId, String orderState) {
        if (!"ORDER_WAIT_SEND".equals(orderState)) {
            return;
        }
        System.out.println("监听到订单" + orderId + "订单支付成功，等待后续操作");
    }
}
