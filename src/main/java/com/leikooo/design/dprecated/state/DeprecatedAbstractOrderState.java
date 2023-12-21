package com.leikooo.design.dprecated.state;

import com.leikooo.design.dprecated.observer.DeprecatedAbstractObserver;
import java.util.List;
import java.util.Vector;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class DeprecatedAbstractOrderState {
    // 定义状态 待发货、待支付、待收获、订单完成
    public final String ORDER_WAIT_PAY = "ORDER_WAIT_PAY";
    public final String ORDER_WAIT_SEND = "ORDER_WAIT_SEND";
    public final String ORDER_WAIT_RECEIVE = "ORDER_WAIT_RECEIVE";
    public final String ORDER_FINISH = "ORDER_FINISH";
    /**
     * 观察者列表。以 list 的形式进行关联，因为需要至此观察者进行添加或则删除操作
     */
    protected final List<DeprecatedAbstractObserver> observerList = new Vector<>();
    public DeprecatedOrder creatOrder(String orderId, String productId) {
        throw new UnsupportedOperationException();
    }

    public DeprecatedOrder payOrder(String orderId) {
        throw new UnsupportedOperationException();
    }

    public DeprecatedOrder senderOrder(String orderId) {
        throw new UnsupportedOperationException();
    }

    public DeprecatedOrder receiveOrder(String orderId) {
        throw new UnsupportedOperationException();
    }

    public void addObserver(DeprecatedAbstractObserver deprecatedAbstractObserver) {
        this.observerList.add(deprecatedAbstractObserver);
    }
    public void removeObserver(DeprecatedAbstractObserver deprecatedAbstractObserver) {
        this.observerList.remove(deprecatedAbstractObserver);
    }

    /**
     * 通知观察者进行操作，并且调用 orderStateHandle 方法
     *
     * @param orderId    订单ID
     * @param orderState 订单状态
     */
    public void notifyObserver(String orderId, String orderState) {
        // 遍历观察者列表
        for (DeprecatedAbstractObserver observer : observerList) {
            // 调用观察者的订单状态处理方法
            observer.orderStateHandle(orderId, orderState);
        }
    }

}
