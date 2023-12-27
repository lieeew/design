package com.leikooo.design.ordermanagement.comand.receiver;

import com.leikooo.design.ordermanagement.state.OrderState;
import com.leikooo.design.pojo.Order;
import org.springframework.stereotype.Component;

import java.util.SortedMap;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class OrderCommandReceiver {
    // 接收命令后长执行
    public void action(Order order) {
        assert order != null : "order 不符合规范";
        OrderState orderState = order.getOrderState();
        switch (orderState) {
            case ORDER_WAIT_PAY -> {
                System.out.println("创建订单");
                System.out.println("存入到 DB");
            }
            case ORDER_WAIT_SEND -> {
                System.out.println("订单 id " + order.getOrderId());
                System.out.println("存入 DB");
                System.out.println("通过 queue 通知财务部门");
                System.out.println("通过 queue 通知发货部门");
            }
            case ORDER_WAIT_RECEIVE -> {
                System.out.println("订单 id " + order.getOrderId());
                System.out.println("存入 DB");
            }
            default -> throw new UnsupportedOperationException("order state error!");
        }
    }
}
