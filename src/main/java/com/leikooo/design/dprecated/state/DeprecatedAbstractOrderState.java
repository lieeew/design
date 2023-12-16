package com.leikooo.design.dprecated.state;

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

    public DeprecatedOrder creatOrder(String orderId, String productId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    public DeprecatedOrder payOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    public DeprecatedOrder senderOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }

    public DeprecatedOrder receiveOrder(String orderId, DeprecatedOrderContext context) {
        throw new UnsupportedOperationException();
    }
}
