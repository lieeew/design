package com.leikooo.design.dprecated.observer;

import com.leikooo.design.dprecated.DeprecatedConstants;
import jakarta.annotation.PostConstruct;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class DeprecatedReceiveObserver extends DeprecatedAbstractObserver {
    @PostConstruct
    public void init() {
        DeprecatedConstants.OBSERVER_LIST.add(this);
    }


    @Override
    public void orderStateHandle(String orderId, String orderState) {
        // 下一个状态
        if (!"ORDER_FINISH".equals(orderState)) {
            return;
        }
        System.out.println("监听到、订单号：" + orderId + " 完成");
    }
}
