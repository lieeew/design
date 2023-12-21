package com.leikooo.design.dprecated.observer;

import com.leikooo.design.dprecated.DeprecatedConstants;
import jakarta.annotation.PostConstruct;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class DeprecatedCreateOrder extends DeprecatedAbstractObserver {
    /**
     * 初始化方法，在对象创建后立即执行
     */
    @PostConstruct
    public void init() {
        // 将当前对象添加到已弃用常量类中的观察者列表中
        DeprecatedConstants.OBSERVER_LIST.add(this);
    }


    @Override
    public void orderStateHandle(String orderId, String orderState) {
        if (!"ORDER_WAIT_PAY".equals(orderState)) {
            return;
        }
        System.out.println("监听到订单" + orderId + "创建成功，等待后续操作");
    }
}
