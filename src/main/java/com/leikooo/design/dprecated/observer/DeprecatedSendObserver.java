package com.leikooo.design.dprecated.observer;

import com.leikooo.design.dprecated.DeprecatedConstants;
import jakarta.annotation.PostConstruct;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class DeprecatedSendObserver extends DeprecatedAbstractObserver {
    @PostConstruct
    public void init() {
        DeprecatedConstants.OBSERVER_LIST.add(this);
    }

    @Override
    public void orderStateHandle(String orderId, String orderState) {
        if (!"ORDER_WAIT_RECEIVE".equalsIgnoreCase(orderState)) {
            return;
        }
        System.out.println("监听到订单 " + orderId + "发送成功");
    }
}
