package com.leikooo.design.ordermanagement.listener;

import com.leikooo.design.ordermanagement.comand.OrderCommand;
import com.leikooo.design.ordermanagement.comand.invoke.OrderCommandInvoke;
import com.leikooo.design.ordermanagement.state.OrderState;
import com.leikooo.design.ordermanagement.state.OrderStateChangeAction;
import com.leikooo.design.pojo.Order;
import com.leikooo.design.utils.RedisCommonProcessor;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description 添加用于监听
 */
@Component
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListener {
    @Resource
    private RedisCommonProcessor redisCommonProcessor;

    @Resource
    private OrderCommand orderCommand;

    @OnStateChanged(source = "ORDER_WAIT_PAY", target = "ORDER_WAIT_SEND")
    public boolean payToSend(Message<OrderStateChangeAction> message) {
        // 从 redis 之中拿到了订单，判断是状态
        Order order = (Order) message.getHeaders().get("order");
        assert order != null : "不存在订单";
        if (order.getOrderState() != OrderState.ORDER_WAIT_PAY) {
            throw new UnsupportedOperationException("order state error !");
        }
        // 更新用户状态，并且更新 redis 数据
        Order updateOrder = Order.builder()
                .orderId(order.getOrderId())
                .orderState(OrderState.ORDER_WAIT_SEND)
                .productId(order.getProductId())
                .price(order.getPrice())
                .build();
        redisCommonProcessor.set(order.getOrderId(), updateOrder, 900);
        OrderCommandInvoke invoke = new OrderCommandInvoke();
        invoke.invoke(orderCommand, updateOrder);
        return true;
    }

    @OnStateChanged(source = "ORDER_WAIT_SEND", target = "ORDER_WAIT_RECEIVE")
    public boolean sendToReceive(Message<OrderStateChangeAction> message) {
        // 从 redis 之中拿到了订单，判断是状态
        Order order = (Order) message.getHeaders().get("order");
        assert order != null : "不存在订单";
        if (order.getOrderState() != OrderState.ORDER_WAIT_SEND) {
            throw new UnsupportedOperationException("order state error !");
        }
        // 更新用户状态，并且更新 redis 数据
        Order updateOrder = Order.builder()
                .orderId(order.getOrderId())
                .orderState(OrderState.ORDER_WAIT_RECEIVE)
                .productId(order.getProductId())
                .price(order.getPrice())
                .build();
        redisCommonProcessor.set(order.getOrderId(), updateOrder, 900);
        OrderCommandInvoke invoke = new OrderCommandInvoke();
        invoke.invoke(orderCommand, updateOrder);
        return true;
    }

    @OnStateChanged(source = "ORDER_WAIT_RECEIVE", target = "ORDER_FINISH")
    public boolean receiveToFinish(Message<OrderStateChangeAction> message) {
        // 从 redis 之中拿到了订单，判断是状态
        Order order = (Order) message.getHeaders().get("order");
        assert order != null : "不存在订单";
        if (order.getOrderState() != OrderState.ORDER_WAIT_RECEIVE) {
            throw new UnsupportedOperationException("order state error !");
        }
        // 删除 order 订单
        redisCommonProcessor.remove(order.getOrderId());
        // 删除 stateMachine 状态机
        redisCommonProcessor.remove(order.getOrderId() + "STATE");
        Order updateOrder = new Order();
        BeanUtils.copyProperties(order, updateOrder);
        updateOrder.setOrderState(OrderState.ORDER_FINISH);
        OrderCommandInvoke invoke = new OrderCommandInvoke();
        invoke.invoke(orderCommand, updateOrder);
        return true;
    }

}