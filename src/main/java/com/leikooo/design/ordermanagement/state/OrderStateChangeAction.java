package com.leikooo.design.ordermanagement.state;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public enum OrderStateChangeAction {
    PAY_ORDER, // 支付操作
    SEND_ORDER, // 发货操作
    RECEIVE_ORDER; // 收获操作
}
